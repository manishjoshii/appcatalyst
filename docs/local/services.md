# Local Spring Boot Services

Start services only after local infrastructure and Config Server are ready.

| Order | Directory | Command | Port/context |
| --- | --- | --- | --- |
| 1 | `config-service` | `.\mvnw.cmd spring-boot:run` | 8888 |
| 2 | `api-gateway` | `.\mvnw.cmd spring-boot:run` | 8080 |
| 3 | `account-service` | `.\mvnw.cmd spring-boot:run` | 9050, `/account` |
| 4 | `workspace-service` | `.\mvnw.cmd spring-boot:run` | 9020, `/workspace` |
| 5 | `intelligence-service` | `.\mvnw.cmd spring-boot:run` | 9030, `/intelligence` |

For each service, run from its own directory:

```powershell
cd .\api-gateway
.\mvnw.cmd spring-boot:run
```

Replace the directory for the service being started. The service's repository-local YAML imports the private Config Server through `CONFIG_SERVER_URL`. Account, workspace, and intelligence also require the installed `common-lib` artifact.
