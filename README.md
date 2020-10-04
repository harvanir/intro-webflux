# Introduction Spring WebFlux

## Pre requisite
1. Docker
2. Java 8

## Sequential Application
1. Run <b>postgres database</b> (`run-infra` file).
2. Run <b>data-migration</b> application.
3. Run <b>webmvc-demo</b> application.
4. Run <b>webflux-demo</b> application.

## Command
### Run infrastructure (PostgreSQL Database)
```shell script
./run-infra
```

### Run data migration
```shell script
cd data-migration/
./mvnw spring-boot:run
```

### Run webmvc demo
```shell script
cd webmvc-demo/
./mvnw spring-boot:run
```

### Run webflux demo
```shell script
cd webflux-demo/
./mvnw spring-boot:run
```