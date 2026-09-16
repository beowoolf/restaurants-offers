# restaurants-offers

[![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-blue)](pom.xml)
[![Java](https://img.shields.io/badge/Java-25-orange)](pom.xml)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.16-brightgreen)](pom.xml)
[![License](https://img.shields.io/badge/license-not%20specified-lightgrey)](#license)

REST API for food ordering. Restaurants publish menus, customers place orders, and the application tracks payment, handoff, and delivery together with customer account operations.

Maven coordinates: `pl.offers:restaurants:0.0.1-SNAPSHOT`. The artifact is packaged as an executable WAR.

## Table of contents

- [Project description](#project-description)
- [Tech stack](#tech-stack)
- [Getting started locally](#getting-started-locally)
- [Available scripts](#available-scripts)
- [Project scope](#project-scope)
- [Project status](#project-status)
- [License](#license)

## Project description

`restaurants-offers` is a Spring Boot web API that models a food-ordering domain: restaurants, menus, dishes and ingredients, customers, employees and deliverers, discount codes, orders, and delivery addresses.

Resources are identified by a client-supplied UUID. Create and update use `PUT /api/{resource}/{uuid}` (upsert). Responses are JSON. List and detail payloads use Jackson `@JsonView` so list endpoints return a smaller shape than a single-resource `GET`. Bean Validation runs on incoming DTOs. Hibernate updates the MySQL schema on startup (`hbm2ddl.auto=update`).

There is no `application.properties` or `application.yml`. Database connection settings come from environment variables, with local defaults in `JPAConfiguration`.

## Tech stack

| Area | Choice |
| --- | --- |
| Language | Java 25 (`java.version` in `pom.xml`) |
| Framework | Spring Boot 3.4.16 |
| Web | Spring Web (`@RestController`), executable WAR (`ServletInitializer`) |
| Persistence | Spring Data JPA, Hibernate `MySQLDialect`, `hbm2ddl.auto=update` |
| Database | MySQL 8.4 (Docker Compose); driver `mysql-connector-j` |
| Validation | Jakarta Bean Validation, custom period validators |
| Boilerplate | Lombok |
| Utilities | Guava (`ImmutableMap` in JPA configuration) |
| Tests | JUnit (via `spring-boot-starter-test`), `@SpringBootTest`, Google Truth (`truth-java8-extension`) |
| Build | Maven Wrapper 3.8.7 (`mvnw` / `mvnw.cmd`) |
| Runtime packaging | WAR, embedded Tomcat (`spring-boot-starter-tomcat`, `provided`) |
| Containers | Multi-stage Dockerfile, Docker Compose (MySQL, phpMyAdmin, Adminer, app) |

Caching is turned on with `@EnableCaching` on `RestaurantsApplication`. No separate cache provider is configured.

The Dockerfile still builds with Eclipse Temurin 21. The compiler target in `pom.xml` is Java 25, so a container build can fail until the image JDK matches that target. Local runs should use JDK 25.

## Getting started locally

### Prerequisites

- JDK 25
- Maven Wrapper (included) or Maven 3.8+
- MySQL 8 listening on port 3306, or Docker with Docker Compose
- Optional: phpMyAdmin and Adminer are started by Compose for database inspection

### 1. Start MySQL

With Docker Compose, from the repository root:

```bash
docker compose up -d mysql
```

That creates database `restaurants` and sets the root password to `toor`. These values are local development defaults from `docker-compose.yml`. Do not use them in production.

Compose also defines:

| Service | URL |
| --- | --- |
| MySQL | `localhost:3306` |
| phpMyAdmin | http://localhost:8081 |
| Adminer | http://localhost:8082 |
| Application (when the `restaurants-offers` service is started) | http://localhost:8080 |

### 2. Configure the database connection

The application reads these variables. Defaults match the Compose file, so a local MySQL with the same credentials needs no extra configuration.

| Variable | Default |
| --- | --- |
| `MYSQL_HOST` | `localhost` |
| `MYSQL_PORT` | `3306` |
| `MYSQL_DB_NAME` | `restaurants` |
| `MYSQL_USER` | `root` |
| `MYSQL_PASSWORD` | `toor` |

Example (Unix shells):

```bash
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_DB_NAME=restaurants
export MYSQL_USER=root
export MYSQL_PASSWORD=toor
```

Example (PowerShell):

```powershell
$env:MYSQL_HOST = "localhost"
$env:MYSQL_PORT = "3306"
$env:MYSQL_DB_NAME = "restaurants"
$env:MYSQL_USER = "root"
$env:MYSQL_PASSWORD = "toor"
```

### 3. Run the API

Unix:

```bash
./mvnw spring-boot:run
```

Windows:

```bat
mvnw.cmd spring-boot:run
```

The API listens on port **8080**. Base path for resources is `/api`. Schema changes are applied automatically on startup. There is no authentication layer.

### Run everything in Docker

```bash
docker compose up -d
```

The application service expects `MYSQL_HOST=mysql` (set in Compose). The image build uses JDK 21; align it with Java 25 before relying on this path. See [Tech stack](#tech-stack).

## Available scripts

Commands use the Maven Wrapper. On Windows, replace `./mvnw` with `mvnw.cmd`.

| Command | Purpose |
| --- | --- |
| `./mvnw spring-boot:run` | Run the application locally |
| `./mvnw test` | Run unit and `@SpringBootTest` tests |
| `./mvnw clean package` | Build `target/restaurants-0.0.1-SNAPSHOT.war` |
| `./mvnw clean package -DskipTests` | Package without tests (same flag the Dockerfile uses) |
| `docker build -f Dockerfile -t restaurants-offers .` | Build the application image |
| `docker run -d -p 8080:8080 restaurants-offers` | Start the image (database must be reachable) |
| `docker compose up -d` | Start MySQL, phpMyAdmin, Adminer, and the application |
| `docker compose up -d mysql` | Start only the database |

## Project scope

Package root: `pl.offers.restaurants`.

Layers:

- `controller` — REST resources and Jackson view interfaces
- `controller.advice` — shared error responses
- `service` — use cases
- `repo` — Spring Data repositories
- `model` — JPA entities and enums
- `dto` — request and response objects
- `validator` — custom constraints for periods and period times
- `event` / `listener` — account operation evidence
- `config` — manual JPA and `DataSource` setup
- `converter`, `serializers`, `utils` — UUID, money, and mapping helpers

### Domain

- **Restaurants** — name, login data, company data, opening hours, menu, discount codes, archive flag
- **Menu** — menu items (net price, VAT, gross price), dishes (quantity of a product), products, ingredients
- **People** — users (personal data, login data, delivery addresses, account operations), employees, deliverers (employees who fulfill orders)
- **Orders** — line items, delivery address, optional discount code, net and gross totals, amount to pay, note, status (`orderTime`, `isPaid`, `giveOutTime`, `deliveryTime`)
- **Discount codes** — code, amount, unit, validity period, restaurants and users
- **Account operations** — deposit, withdrawal, and payment evidence; balance must stay positive after an operation

### HTTP API

Unless noted, each resource supports:

- `GET /api/{resource}` — list
- `GET /api/{resource}/{uuid}` — one item (`404` when missing)
- `PUT /api/{resource}/{uuid}` — create or replace
- `DELETE /api/{resource}/{uuid}` — delete

| Resource | Path | Extra operations |
| --- | --- | --- |
| Restaurants | `/api/restaurants` | — |
| Opening hours | `/api/open-times` | `POST /api/open-times` accepts a list and upserts each item |
| Menu items | `/api/menu-items` | — |
| Dishes | `/api/dishes` | — |
| Products | `/api/products` | — |
| Ingredients | `/api/ingredients` | — |
| Users | `/api/users` | `GET /api/users/{uuid}/delivery-addresses`; `POST /api/users/{uuid}/new-operation` records an account operation |
| Delivery addresses | `/api/delivery-address` | — |
| Employees | `/api/employees` | — |
| Deliverers | `/api/deliverers` | — |
| Discount codes | `/api/discount-codes` | — |
| Orders | `/api/orders` | `GET /api/orders?user={uuid}`; `GET /api/orders?deliverer={uuid}` (query parameter name is `delivererUuid`); `PATCH /api/orders/{uuid}/paid`; `PATCH /api/orders/{uuid}/gived-out`; `PATCH /api/orders/{uuid}/delivered` |

`PATCH .../paid` publishes an operation-evidence event. `PATCH .../gived-out` and `PATCH .../delivered` take an `OrderStatusDTO` body.

### Errors

`GlobalExceptionHandler` maps:

- bean validation failures to `400`
- `ResponseStatusException` to `400`
- constraint and integrity violations to `409`

### Tests

`src/test` covers orders, deliverers, delivery addresses, discount codes, and the operation-evidence listener with `@SpringBootTest` and Truth. `RestaurantsApplicationTests` checks that the Spring context loads. Tests that touch JPA need a reachable MySQL instance using the variables above.

## Project status

**0.0.1-SNAPSHOT** — early development snapshot.

In place today:

- REST resources listed above
- JPA model with schema update on startup
- Validation, JSON views, and operation-evidence events
- Maven Wrapper build and a Docker Compose stack for MySQL
- Controller and listener tests for part of the API

Not in place:

- No project license file
- No authentication or authorization
- No checked-in `application.properties` / `application.yml`
- No CI workflow (no build-status badge)
- No API reference beyond this README (no OpenAPI spec in the repository)
- Dockerfile JDK (21) does not match the Java 25 compiler target
- Order query parameter for deliverers is named `delivererUuid` while the mapping declares `params = {"deliverer"}`

## License

No license file is included in this repository. All rights reserved by the copyright holder until a license is added.

The Maven Wrapper files under `.mvn/wrapper` are covered by the Apache License 2.0. That license applies to the wrapper, not to the application source.
