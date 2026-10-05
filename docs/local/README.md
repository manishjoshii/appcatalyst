# Local Development

This is the authoritative host-based development path. It does not deploy the Spring Boot services into Kubernetes.

## Order of operations

1. [Configure the private Config Server](../config-server.md)
2. [Start local infrastructure](infrastructure.md)
3. [Configure MinIO and the starter project](minio.md)
4. [Configure Stripe webhook forwarding](stripe.md)
5. Build/install common-lib
6. [Start the Spring Boot services](services.md)
7. [Verify the application](troubleshooting.md)
8. [Optionally test generated-project previews](previews.md)

## Step 5 — Build/install common-lib

Run from the repository root:

```powershell
Push-Location .\common-lib
.\mvnw.cmd clean install -DskipTests
Pop-Location
```

The account, workspace, and intelligence services declare common-lib as a Maven dependency. Install it before building or running those services.

## What local means

The private local profile uses host addresses for PostgreSQL, Kafka, and MinIO. Redis runs in the AppCatalyst Kubernetes cluster and is exposed to host services through a port-forward. Eureka runs locally when enabled by the private local configuration.

Generated-project previews require the additional reverse-proxy and Windows hosts-file steps in [Local generated-project previews](previews.md).

