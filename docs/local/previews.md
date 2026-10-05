# Local generated-project previews

When an AI-generated project is deployed, the workspace service starts it in a runner pod and returns a preview URL. Host-based Spring Boot development needs two additional steps to make that URL reachable from Windows:

1. Forward the Kubernetes reverse proxy to a Windows port.
2. Add the generated preview hostname to the Windows hosts file.

## Preview traffic flow

```text
Browser
  -> Windows hosts file resolves project-<id>-localhost to 127.0.0.1
  -> localhost:8090
  -> Kubernetes appcatalyst-proxy or acs-proxy
  -> Redis route:<hostname>
  -> runner pod on port 5173
```

The local configuration uses preview domain localhost and proxy port 8090. The workspace deployment code generates a hostname in the form:

```text
project-<project-id>-localhost
```

The response URL is therefore normally:

```text
http://project-<project-id>-localhost:8090
```

Use the exact URL returned by the deploy API; do not guess the project ID or hostname.

## 1. Start the runner and proxy

The runner pool and reverse proxy must already exist in Kubernetes. For an existing acs-apps environment:

```powershell
kubectl get pods -n acs-apps
kubectl get services -n acs-apps
kubectl scale deployment/acs-proxy --replicas=1 -n acs-apps
kubectl scale deployment/runner-pool --replicas=2 -n acs-apps
```

For the repository's canonical Kubernetes resources:

```powershell
kubectl get pods -n appcatalyst-previews --show-labels
kubectl get services -n appcatalyst-core
kubectl scale deployment/appcatalyst-proxy --replicas=1 -n appcatalyst-core
kubectl scale deployment/runner-pool --replicas=2 -n appcatalyst-previews
```

If the workloads do not exist, follow the [Kubernetes first-time setup](../cloud/kubernetes/README.md). The local host-development guide does not create the full runner system.

## 2. Forward the reverse proxy

Keep this command running in a separate PowerShell window.

For the acs-apps environment:

```powershell
kubectl port-forward -n acs-apps service/acs-proxy 8090:80
```

For the repository's canonical resources:

```powershell
kubectl port-forward -n appcatalyst-core service/appcatalyst-proxy 8090:80
```

The local preview configuration expects port 8090. Do not forward directly to the runner pod; the reverse proxy is responsible for resolving the hostname through Redis and forwarding HTTP/WebSocket traffic to the correct runner.

## 3. Add the hostname to Windows hosts

The hosts file is:

```text
C:\Windows\System32\drivers\etc\hosts
```

Open Notepad or another editor as Administrator, then add one entry for the hostname returned by the deployment response:

```text
127.0.0.1 project-8-localhost
```

Replace project-8-localhost with the actual hostname. Do not add the port to the hosts file; ports belong in the browser URL.

If Windows or the browser cached an earlier lookup, flush the DNS cache:

```powershell
ipconfig /flushdns
```

## 4. Open the preview

If the deployment response returns:

```text
http://project-8-localhost:8090
```

open that exact URL in the browser. The hosts entry resolves the hostname to the local machine, port 8090 reaches the reverse proxy, and the proxy uses the Redis route created by workspace-service.

A hosts entry alone is not enough. The reverse-proxy port-forward must remain running, the Redis route must exist, and the runner's Vite server must be listening on port 5173.

## Troubleshooting

- **Browser cannot resolve the hostname:** check the hosts entry and run ipconfig /flushdns.
- **Connection refused:** the reverse-proxy port-forward is not running or uses a different port.
- **404 Preview not found:** Redis has no route for the exact Host header; check workspace-service and proxy logs.
- **502 Preview server unavailable:** the runner is not ready or Vite has not started on port 5173.
- **HMR/WebSocket failure:** keep the proxy running and verify the proxy's WebSocket support.
