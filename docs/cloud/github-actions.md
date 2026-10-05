# GitHub Actions

The current workflows trigger on `workflow-dispatch` (manual trigger) when their service path changes.

Each workflow:

1. Checks out the repository.
2. Installs Java 21.
3. Builds/pushes the service image with Jib.
4. Authenticates to Google Cloud with workload identity.
5. Gets GKE credentials.
6. Runs `kubectl set image` in `appcatalyst-core`.
7. Waits for rollout.

Required secret names are visible in the workflow files, including Docker Hub credentials and GCP workload/cluster values. Treat self-hosted runners as optional advanced infrastructure; they are not part of normal local development and the Kubernetes API must not be exposed publicly just to reach a home cluster.


