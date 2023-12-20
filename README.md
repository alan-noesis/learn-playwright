## Requirements
- Java 21
- Gradle 8.5
- Docker
- Docker compose

## Start the example database
```
docker-compose -f ./docker-compose.yml up -d
```

## Start the example API
```
java --enable-preview -jar demo.jar
```
