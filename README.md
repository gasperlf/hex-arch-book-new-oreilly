# Example HA

```shell
newman run topology-inventory.postman_collection.json
```

```shell
podman compose -f docker-compose.yaml up -d
podman compose -f docker-compose.yaml stop
podman compose -f docker-compose.yaml rm
```

# Dockerfile and Kubernetes Objects for Cloud Deployment
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 14 directory:

**To generate a Docker image with Uber Jar**
```
$ mvn clean package
$ docker build . -t topology-inventory
```
**To generate a Docker image with native executable**
```
$ mvn clean package -Pnative -Dquarkus.native.container-build=true -Dnative-image.xmx=6g
$ docker build . -t topology-inventory-native -f Dockerfile-native
```
**To start the container with Uber Jar**
```
docker run -p 5555:8080 topology-inventory
```
**To start the container with native executable**
```
docker run -p 5555:8080 topology-inventory-native
```
**To access the Dockerized application**
```
http://localhost:5555/q/swagger-ui/
```

To generate base64, you can execute the following command on Unix-based systems:
```shell
echo root | base64 && echo password | base64

cm9vdAo=
cGFzc3dvcmQK
```