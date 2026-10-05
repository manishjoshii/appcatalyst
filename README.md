# AppCatalyst

AppCatalyst is a cloud-native platform for AI-assisted web application generation and deployment. This repository contains Spring Boot microservices, a shared Java library, Kubernetes manifests, and the Node.js preview proxy.

## Key features

- Authentication, accounts, subscriptions, and Stripe billing
- AI chat and code-generation workflows
- Project/workspace and file management
- MinIO object storage and Kafka event processing
- Kubernetes preview runners with Redis-backed wildcard routing

## Stack

Java 21, Spring Boot 3.4.4, Spring Cloud, Maven wrappers, Spring Security/JWT, PostgreSQL 16 with pgvector, Confluent Local Kafka 7.5.0, Redis 7, MinIO, Kubernetes/Kind, NGINX Ingress, Cloudflare Tunnel, Node.js 20, and Jib.

## Structure

```text
account-service/          Accounts, authentication, billing
api-gateway/              External API gateway
common-lib/               Shared DTOs, events, security, errors
config-service/           Spring Cloud Config Server
discovery-service/        Eureka server for non-Kubernetes discovery
intelligence-service/     AI chat and generation
workspace-service/        Projects, files, storage, previews
k8s/                      Kubernetes manifests and preview proxy
docs/                     Developer, deployment, service, and troubleshooting docs
```

## Prerequisites

Install Git, Docker, Kind, kubectl, Java 21, and use the checked-in Maven wrappers. Access to the private configuration service and its credentials is required. AI and Stripe features require provider credentials.

## Setup

Choose the environment you want to run.

### Option A — Local Development

Use this when developing Spring Boot services directly on the host machine.

1. Configure the private Git-backed Config Server
2. Start local infrastructure
3. Configure MinIO and the starter project
4. Configure Stripe webhook forwarding
5. Build/install `common-lib`
6. Start AppCatalyst services
7. Verify the application

→ [Detailed local development guide](docs/local/README.md)

### Option B — Kubernetes

Use the same detailed flow for Kind, Docker Desktop Kubernetes, GKE, or another Kubernetes provider. Only cluster access, storage, ingress exposure, DNS, and optional Cloudflare routing change by environment.

→ [Detailed Kubernetes first-time setup](docs/cloud/kubernetes/README.md)

## Documentation

- [Documentation map](docs/README.md)
- [Service and runtime architecture](docs/local/services.md)
- [Local development](docs/local/README.md)
- [Kubernetes first-time setup](docs/cloud/kubernetes/README.md)
- [Cloud deployment workflow](docs/cloud/README.md)
