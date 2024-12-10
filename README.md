# Example HA

```shell
newman run topology-inventory.postman_collection.json
```

```shell
podman compose -f docker-compose.yaml up -d
podman compose -f docker-compose.yaml stop
podman compose -f docker-compose.yaml rm
```