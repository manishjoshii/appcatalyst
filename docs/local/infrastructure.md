# Local infrastructure

This page prepares dependencies for host-based Spring Boot development. It separates Docker Desktop dependencies from Kubernetes-managed AppCatalyst services.

## Target local topology

```text
Spring Boot services on Windows
        |
        | localhost:9010  PostgreSQL/pgvector
        | localhost:29092 Kafka
        | localhost:9000  MinIO
        | localhost:6379  Redis through Kubernetes port-forward
        |
Docker Desktop + AppCatalyst Kubernetes cluster
        |
        +-- Redis
        +-- appcatalyst-proxy
        +-- runner-pool
```

| Dependency | Address used by host services | Where it runs |
| --- | --- | --- |
| PostgreSQL/pgvector | localhost:9010 | Docker Desktop |
| Kafka | localhost:29092 | Docker Desktop |
| MinIO API | http://localhost:9000 | Docker Desktop |
| MinIO console | http://localhost:9001 | Docker Desktop |
| Redis | localhost:6379 through port-forward | Kubernetes |
| Eureka | http://localhost:8761/eureka/ | discovery-service on the host |

The local PostgreSQL databases are appcatalyst-account-db, appcatalyst-intelligence-db, and appcatalyst-workspace-db.

> PostgreSQL and MinIO are different services and cannot run in one Docker container. Use separate containers or deploy both with the Kubernetes manifests.

## 1. Check existing runtimes

Do not recreate a cluster or delete PVCs until you have checked the current state:

```powershell
docker ps -a
docker ps

kind get clusters
kubectl config current-context
kubectl get namespaces
kubectl get pods -A
kubectl get pvc -A
```

## 2. Docker Desktop dependencies

Docker Desktop is used here for PostgreSQL/pgvector, Kafka, and MinIO. Redis is not started as an external Docker container; it runs inside the AppCatalyst Kubernetes cluster.

### Existing containers

Start the Docker-managed containers if they already exist:

```powershell
docker start kafka-appcatalyst pgvector-db-appcatalyst minio-appcatalyst
docker ps
```

Use the exact names shown by docker ps -a if your names differ.

Expected host mappings:

| Container | Host mapping |
| --- | --- |
| PostgreSQL/pgvector | 9010:5432 |
| Kafka | 29092:29092 |
| MinIO API | 9000:9000 |
| MinIO console | 9001:9001 |

### First-time Docker setup

Pull the repository dependency images:

```powershell
docker pull pgvector/pgvector:pg16
docker pull confluentinc/confluent-local:7.5.0
docker pull minio/minio:latest
```

Pulling images does not configure containers. Use Docker Desktop's Run form or your approved local Docker setup to expose the mappings above, initialize the three PostgreSQL databases, configure Kafka on host port 29092, and start MinIO with the private local credentials.

This repository has no docker-compose.yml or canonical host-local docker run file. Do not assume image pulling alone creates a usable dependency.

## 3. Kubernetes services for local development

Local Spring Boot development does not need the full Kubernetes application stack. Docker Desktop provides PostgreSQL/pgvector, Kafka, and MinIO. Kubernetes only needs to provide Redis because workspace-service uses it for route storage.

The preview proxy and runner-pool are optional. Start them only when you are testing generated-project previews.

### Check the existing cluster

Do not apply the cloud/Kubernetes manifests from this page. First check whether the cluster and workloads already exist:

```powershell
kind get clusters
kubectl config current-context
kubectl get namespaces
kubectl get pods -A
kubectl get services -A
```

The repository's Kubernetes manifests use appcatalyst-core for Redis and appcatalyst-previews for runner pods. Your existing cluster output may use a different namespace such as acs-apps and different workload names such as redis-server or acs-proxy. Use the namespace and resource names returned by kubectl for that existing environment.

### If the cluster does not exist

Create the local cluster and namespace. This only creates the Kubernetes runtime; it does not deploy the full AppCatalyst platform:

```powershell
kind create cluster --name appcatalyst-ai
kubectl create namespace acs-apps
```

If you are using the repository's canonical Kubernetes manifests instead of the existing acs-apps environment, use the full [Kubernetes first-time setup](../cloud/kubernetes/README.md). It creates appcatalyst-core and appcatalyst-previews and deploys the complete workloads.

### Start existing Kubernetes workloads

If the resources already exist but are scaled to zero, start only the workloads needed for local development. Replace acs-apps and the resource names with the values from your cluster:

```powershell
kubectl scale statefulset/redis-server --replicas=1 -n acs-apps
```

If Redis is a Deployment rather than a StatefulSet, use:

```powershell
kubectl scale deployment/redis-server --replicas=1 -n acs-apps
```

For optional local preview testing, start the proxy and runner pool if those resources already exist:

```powershell
kubectl scale deployment/acs-proxy --replicas=1 -n acs-apps
kubectl scale deployment/runner-pool --replicas=2 -n acs-apps
```

Check readiness:

```powershell
kubectl get pods -n acs-apps
kubectl get services -n acs-apps
```

Do not invent a runner-pool deployment with kubectl create deployment. The repository runner pool is a two-container pod with a shared workspace and MinIO sync configuration; create it from the dedicated Kubernetes manifests when you need the canonical implementation.

### Port-forward Redis for host Spring Boot services

The local workspace configuration uses Redis at localhost:6379. If Redis runs in Kubernetes and the Java services run on Windows, keep this command running in a separate terminal:

```powershell
kubectl port-forward service/redis-server 6379:6379 -n acs-apps
```

If your cluster uses the repository's canonical resource name, use:

```powershell
kubectl port-forward service/redis 6379:6379 -n appcatalyst-core
```

Do not port-forward Redis when workspace-service also runs inside Kubernetes. In that case, use the Kubernetes Redis DNS address from the k8s configuration.

### If a service is missing or failing

Inspect the workload before restarting or deleting anything:

```powershell
kubectl get pods -n acs-apps
kubectl describe pod <pod-name> -n acs-apps
kubectl get events -n acs-apps --sort-by=.lastTimestamp
kubectl logs <pod-name> -n acs-apps -c <container-name>
```

For ImagePullBackOff or ErrImagePull, verify the image name, registry access, and node internet access. The runner pool has runner and syncer containers, so inspect the container that failed.

## 4. Startup order

For host-based development:

```text
1. Start or verify the existing Kubernetes cluster
2. Start Redis in Kubernetes
3. Start Docker Desktop PostgreSQL, Kafka, and MinIO
4. Port-forward Redis to localhost:6379
5. Start Config Server
6. Configure MinIO and Stripe
7. Build/install common-lib
8. Start the API gateway and application services
```

Start the optional proxy and runner-pool only when testing previews. For the complete Kubernetes path, follow [Kubernetes first-time setup](../cloud/kubernetes/README.md).

Continue with [MinIO setup](minio.md) after Redis and the Docker dependencies are ready.
