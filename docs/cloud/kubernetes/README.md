# Kubernetes deployment

This is the shared deployment flow for Kind, Docker Desktop Kubernetes, GKE, and other Kubernetes clusters. The manifests use the same namespaces and workload names in each environment. Only cluster access, storage, ingress exposure, DNS, and optional Cloudflare routing change.

For host-based Spring Boot development, use [Local development](../local/README.md) instead.

## Before you start

Install and verify:

```powershell
docker version
kubectl version --client
kind version
```

Configure the private Git-backed Config Server using the [common Config Server guide](../config-server.md). Do not put its private URL or credentials in this document.

The repository's Docker Hub image owner is `manishjoshiii`. If you use another account, replace the owner in both the build commands and Kubernetes manifests.

## 1. Create or select the cluster

If the cluster already exists, do not recreate it or delete its PVCs:

```powershell
kind get clusters
kubectl config current-context
kubectl get nodes
kubectl get namespaces
```

For a new local Kind cluster:

```powershell
kind create cluster --name appcatalyst-ai
```

Create the AppCatalyst namespaces and shared ConfigMap:

```powershell
kubectl apply -f .\k8s\infra\namespaces.yaml
```

This creates:

- `appcatalyst-core`: stateful services, Config Server, application services, frontend, API gateway, and preview proxy;
- `appcatalyst-previews`: preview runner pool;
- `ingress-nginx`: NGINX and optional Cloudflare Tunnel.

## 2. Create secrets on the first setup

Create the local secret file from the template and fill in environment-specific values. Never commit it:

```powershell
Copy-Item .\k8s\.env.example .\k8s\.env
notepad .\k8s\.env
```

Create `app-secrets` in both namespaces. Secrets are namespace-scoped:

```powershell
kubectl create secret generic app-secrets `
  --from-env-file=.\k8s\.env `
  -n appcatalyst-core `
  --dry-run=client -o yaml | kubectl apply -f -

kubectl create secret generic app-secrets `
  --from-env-file=.\k8s\.env `
  -n appcatalyst-previews `
  --dry-run=client -o yaml | kubectl apply -f -
```

The template contains database, JWT, MinIO, AI, Stripe, private Git, and Cloudflare values. Do not display Secret values in logs. Existing PostgreSQL and MinIO PVCs are not reinitialized when credentials change.

## 3. Deploy stateful services

This deploys PostgreSQL/pgvector, Kafka, MinIO, and Redis in `appcatalyst-core`:

```powershell
kubectl apply -f .\k8s\stateful\
kubectl get pods -n appcatalyst-core
kubectl get pvc -n appcatalyst-core
```

Wait for the StatefulSets before continuing:

```powershell
kubectl wait --for=condition=ready pod -l app=redis -n appcatalyst-core --timeout=180s
kubectl wait --for=condition=ready pod -l app=minio -n appcatalyst-core --timeout=180s
kubectl wait --for=condition=ready pod -l app=pgvector -n appcatalyst-core --timeout=180s
kubectl wait --for=condition=ready pod -l app=kafka -n appcatalyst-core --timeout=180s
```

PostgreSQL creates the application databases only when its data directory is initialized for the first time.

## 4. Build and push application images

Java services use Jib; build `common-lib` first:

```powershell
Push-Location .\common-lib
.\mvnw.cmd clean install -DskipTests
Pop-Location
docker login
```

Build and push the Java images used by the manifests:

```powershell
$services = @(
  "config-service",
  "api-gateway",
  "account-service",
  "intelligence-service",
  "workspace-service"
)

foreach ($service in $services) {
  Push-Location ".\$service"
  .\mvnw.cmd compile jib:build "-Djib.to.image=docker.io/manishjoshiii/appcatalyst-$service:latest"
  Pop-Location
}
```

The repository has no frontend source/build directory. `k8s/services/frontend.yaml` expects `manishjoshiii/appcatalyst-frontend:latest` to already exist in Docker Hub.

## 5. Deploy application services

Apply Config Server first, then the application services:

```powershell
kubectl apply -f .\k8s\services\config-service.yaml
kubectl rollout status deployment/config-service -n appcatalyst-core --timeout=180s

kubectl apply -f .\k8s\services\account-service.yaml
kubectl apply -f .\k8s\services\intelligence-service.yaml
kubectl apply -f .\k8s\services\workspace-service.yaml
kubectl rollout status deployment/account-service -n appcatalyst-core --timeout=180s
kubectl rollout status deployment/intelligence-service -n appcatalyst-core --timeout=180s
kubectl rollout status deployment/workspace-service -n appcatalyst-core --timeout=180s

kubectl apply -f .\k8s\services\api-gateway.yaml
kubectl rollout status deployment/api-gateway -n appcatalyst-core --timeout=180s

kubectl apply -f .\k8s\services\frontend.yaml
kubectl rollout status deployment/appcatalyst-frontend -n appcatalyst-core --timeout=180s
```

Optional Stripe CLI:

```powershell
kubectl apply -f .\k8s\services\stripe-cli.yaml
```

Check services and Config Server logs:

```powershell
kubectl get pods -n appcatalyst-core
kubectl get services -n appcatalyst-core
kubectl logs deployment/config-service -n appcatalyst-core
```

## 6. Install NGINX and apply Ingress

For Kind, install the NGINX controller:

```powershell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
kubectl wait --namespace ingress-nginx `
  --for=condition=ready pod `
  --selector=app.kubernetes.io/component=controller `
  --timeout=180s
```

For a cloud cluster, install ingress-nginx using the provider-supported method and confirm the IngressClass is `nginx`. Do not use the Kind manifest unless appropriate for that provider.

Apply the repository routes:

```powershell
kubectl apply -f .\k8s\infra\ingress.yaml
kubectl get ingress -n appcatalyst-core
```

The Ingress routes the frontend, API gateway, and wildcard preview hosts. DNS or a local hosts-file entry must resolve those hosts to the ingress entry point.

## 7. Apply network policies

Apply the core policies:

```powershell
kubectl apply -f .\k8s\infra\core-network-policies.yaml
```

Apply the preview sandbox policy:

```powershell
kubectl apply -f .\k8s\infra\preview-network-policies.yaml
```

The preview policy allows runner traffic from `appcatalyst-proxy` on port `5173`, DNS, MinIO on port `9000`, and public package downloads. It blocks the listed private network ranges.

## 8. Build and deploy the preview runner and proxy

Build and push the proxy image:

```powershell
Push-Location .\k8s\proxy
docker build --platform linux/amd64 -t manishjoshiii/appcatalyst-proxy:latest .
docker push manishjoshiii/appcatalyst-proxy:latest
Pop-Location
```

Start the warm runner pool, then the reverse proxy:

```powershell
kubectl apply -f .\k8s\infra\runner-pool.yaml
kubectl rollout status deployment/runner-pool -n appcatalyst-previews --timeout=180s

kubectl apply -f .\k8s\proxy\proxy-deployment.yaml
kubectl rollout status deployment/appcatalyst-proxy -n appcatalyst-core --timeout=180s
```

Each runner pod contains a Node.js runner on port `5173` and a MinIO syncer sharing an `emptyDir` workspace. The proxy uses Redis to route preview hostnames to runners. Do not expose runner pods directly.

## 9. Optional Cloudflare Tunnel

Use Cloudflare when a local cluster must be reachable through a public domain. Cloud traffic is:

```text
Browser -> Cloudflare -> cloudflared -> NGINX Ingress -> AppCatalyst
```

Create the token Secret and deploy `cloudflared`:

```powershell
kubectl create secret generic cloudflare-tunnel-token `
  --from-literal=token="YOUR_TUNNEL_TOKEN" `
  -n ingress-nginx `
  --dry-run=client -o yaml | kubectl apply -f -

kubectl apply -f .\k8s\infra\cloudflare-tunnel.yaml
kubectl rollout status deployment/cloudflared -n ingress-nginx --timeout=180s
kubectl logs deployment/cloudflared -n ingress-nginx -f
```

Configure Cloudflare hostname routes for the frontend, API, and wildcard preview domains listed in `k8s/infra/ingress.yaml`. Keep the ConfigMap `PREVIEW_DOMAIN` value aligned with the preview wildcard. Never commit the tunnel token.

## 10. Verify and troubleshoot

```powershell
kubectl get nodes
kubectl get pods -n appcatalyst-core
kubectl get pods -n appcatalyst-previews --show-labels
kubectl get services -n appcatalyst-core
kubectl get ingress -n appcatalyst-core
kubectl get pvc -n appcatalyst-core
kubectl get events -A --sort-by=.lastTimestamp
```

For a failed pod:

```powershell
kubectl describe pod <pod-name> -n <namespace>
kubectl logs <pod-name> -n <namespace> --all-containers
```

- `ImagePullBackOff`/`ErrImagePull`: check the exact Docker Hub image, tag, visibility, and node connectivity.
- Config Server failure: check private Git credentials and Config Server logs.
- Database failure: inspect the pod and PVC; initialization scripts run only for a new data directory.
- Ingress 404/502: check the hostname, IngressClass, Service endpoints, and NGINX logs.
- Preview 404/502: check Redis routes, proxy logs, runner readiness, and port `5173`.
