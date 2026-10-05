# Local MinIO

Workspace uses two buckets:

- `starter-projects`: source templates
- `projects`: per-project files

The code expects the starter template prefix `react-vite-tailwind-daisyui-starter/` inside `starter-projects`. You may use a compatible starter or the existing project at https://github.com/manishjoshii/react-vite-tailwind-daisyui-starter.

## Configure

1. Start MinIO at `http://localhost:9000`.
2. Open the console at port `9001`.
3. Create `starter-projects` and `projects`.
4. Upload the starter project so its object keys begin with `react-vite-tailwind-daisyui-starter/`.
5. Use the same access key and secret key in the private local configuration.

If using the MinIO client `mc`, the equivalent commands are:

```powershell
mc alias set local http://localhost:9000 YOUR_MINIO_USER YOUR_MINIO_PASSWORD
mc mb --ignore-existing local/starter-projects
mc mb --ignore-existing local/projects
mc cp --recursive .\path\to\react-vite-tailwind-daisyui-starter local/starter-projects/react-vite-tailwind-daisyui-starter
```

Replace only the marked local path and credentials. The application copies template objects into the `projects/<project-id>/` prefix and stores file metadata in PostgreSQL.



