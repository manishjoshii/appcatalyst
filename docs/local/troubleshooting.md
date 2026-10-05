# Local troubleshooting

- **Config Server unavailable:** check `http://localhost:8888/actuator/health`, Git access, and `CONFIG_SERVER_URL`.
- **Database connection refused:** verify PostgreSQL is listening on host port 9010 and the three local databases exist.
- **Kafka connection refused:** verify the broker is on 29092; Kubernetes uses a different address.
- **MinIO errors:** verify both buckets, credentials, and the starter prefix.
- **Stripe events missing:** keep `stripe listen` running and use `http://localhost:9050/account/webhooks/payment`.
- **Eureka registration fails:** correct `defaultZone` in the private YAML and confirm Eureka is on 8761.



