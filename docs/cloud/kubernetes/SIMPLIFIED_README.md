# Kubernetes Deployment Steps

## Create New Kind Kubernetes Cluster

```bash
kind create cluster --name appcatalyst-ai
```

## Create New Namespaces and ConfigMap Under Kind Kubernetes Running Cluster

```text
kubectl apply -f .\k8s\infra\namespaces.yaml
```

## Create Secrets for `appcatalyst-previews` Namespace via `.env` from Local

```text
kubectl create secret generic app-secrets --from-env-file=.env -n appcatalyst-previews
```

## Create Secrets for `appcatalyst-core` Namespace via `.env` from Local

```text
kubectl create secret generic app-secrets --from-env-file=.env -n appcatalyst-core
```

## Create Stateful Pods

```text
kubectl apply -f .\stateful\
```

## Check Created Stateful Pods

To check created stateful pods run `kubectl get pods` followed by namespace name `-n appcatalyst-core`

```bash
kubectl get pods -n appcatalyst-core
```

## Pull Config-Service Docker Image

To pull the config-service Docker image, run:

```bash
kubectl apply -f .\services\config-service.yaml
```

To check the pulled pod:

```bash
kubectl get pods -n appcatalyst-core
```

## Check Config-Service Logs

```bash
kubectl logs config-service-75d47b4ff4-62vrf -n appcatalyst-core
```

## Pull API-Gateway Docker Image

To pull the api-gateway Docker image, run:

```bash
kubectl apply -f .\services\api-gateway.yaml
```

```text
deployment.apps/api-gateway created
service/api-gateway created
```

## Pull All Service Images

Pull all service images:

- account
- intelligence
- workspace

## Install NGINX Ingress Controller for Kind

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
```

## Apply `ingress.yaml`

```bash
kubectl apply -f .\infra\ingress.yaml
```

```text
ingress.networking.k8s.io/appcatalyst-main-ingress created
```

## Create Cloudflare Tunnel and Copy Token Secret

Pass the Cloudflare tunnel token to namespace `ingress-nginx`:

```powershell
kubectl create secret generic cloudflare-tunnel-token `
  --from-literal=token="YOUR_ACTUAL_TUNNEL_TOKEN" `
  -n ingress-nginx
```

## Create Cloudflared Pod

```bash
kubectl apply -f .\k8s\infra\cloudflare-tunnel.yaml
```

## Apply Core Network Policies

Rules to communicate within cluster:

```bash
kubectl apply -f .\infra\core-network-policies.yaml
```

```text
networkpolicy.networking.k8s.io/allow-internal-only created
networkpolicy.networking.k8s.io/allow-previews-to-minio created
networkpolicy.networking.k8s.io/allow-nginx-ingress created
```

## Apply Preview Network Policies

`preview-network-policies.yaml` sandboxes runner pods in `appcatalyst-previews`:

1. **Ingress:** Only allows incoming web traffic on port `5173` from `appcatalyst-proxy` (blocking any direct public or unauthorized internal access).

2. **Egress:** Restricts outbound connections strictly to cluster DNS (port `53`), MinIO storage in `appcatalyst-core` (port `9000`), and public internet (for `npm install`) while explicitly blocking access to all internal private network CIDRs.

```bash
kubectl apply -f .\infra\preview-network-policies.yaml
```

```text
networkpolicy.networking.k8s.io/strict-preview-sandbox created
```

## Apply Runner Pool

`runner-pool.yaml` maintains a warm pool of idle runner pods in the `appcatalyst-previews` namespace for instant preview execution:

1. **Dual-Container Pod:** Combines a Node.js runner (executes Vite/dev server on port `5173`) and a MinIO syncer (`mc` client) sharing an `emptyDir` workspace volume.

2. **Instant Provisioning:** Allows workspace-service to immediately claim a pre-warmed pod, pull project code from MinIO via the syncer, and run the preview without waiting for image pulls or pod spin-up delays.

```bash
kubectl apply -f .\infra\runner-pool.yaml
```

```text
deployment.apps/runner-pool created
```

## Build Docker Image of Reverse Proxy and Push to Docker Hub

```bash
docker build --platform linux/amd64 -t manishjoshiii/appcatalyst-proxy:latest .
```

```bash
docker push manishjoshiii/appcatalyst-proxy:latest
```

## Deploy Reverse Proxy

```bash
kubectl apply -f .\proxy-deployment.yaml
```

```text
deployment.apps/appcatalyst-proxy created
service/appcatalyst-proxy created
```