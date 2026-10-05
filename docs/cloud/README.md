# Cloud Kubernetes deployment

Cloud setup has one first-time Kubernetes foundation followed by cloud-specific deployment and automation.

## First-time setup

Follow [Kubernetes first-time setup](kubernetes/README.md) from beginning to end. It creates the cluster namespaces and secrets, deploys stateful services, builds and deploys application images, installs ingress, applies network policies, and starts the preview runner and proxy.

Do not skip the first-time guide on a new cluster. The cloud pages below assume that the namespaces, PVCs, Services, deployments, Config Server, and Docker Hub images already exist.

## Cloud-specific follow-up

After the first-time setup:

- Store values from `k8s/.env.example` in the provider's secret manager or Kubernetes Secret mechanism. Never commit `k8s/.env`, tunnel tokens, Docker Hub tokens, or cloud credentials.
- Use the Jib commands in the first-time guide when rebuilding Java images. Push the image tag before restarting the related deployment.
- Use provider-supported storage, ingress-nginx, load-balancer, DNS, and cluster-credential setup. Do not use the Kind ingress manifest on cloud unless the provider supports it.
- Use [GitHub Actions](github-actions.md) only for the workflows that are actually checked in.

For a failed cloud deployment, check Docker Hub image visibility, cloud authentication, Kubernetes context, Config Server credentials, pod events, and rollout status:

```powershell
kubectl config current-context
kubectl get pods -n appcatalyst-core
kubectl get events -A --sort-by=.lastTimestamp
kubectl rollout status deployment/<deployment-name> -n appcatalyst-core
```

The checked-in GitHub Actions workflows currently deploy only `config-service`, `api-gateway`, and `account-service`. They do not replace the complete first-time setup or deploy every AppCatalyst workload.
