# Todo-Backend-Springboot

A Spring Boot backend for a TODO application.

This project is a Java 17 Spring Boot application (groupId: `com.aditya`, artifactId: `todo`) with JPA, Spring Security and JWT support. It is configured to use PostgreSQL at runtime and includes common validation and web MVC dependencies.

> Note: This README was created from the repository metadata (pom.xml, .gitignore, and project layout). If there are additional project-specific instructions or environment variables implemented in source code, adapt the values below accordingly.

## Tech stack

- Java 17
- Spring Boot (starter modules used: data-jpa, security, validation, webmvc)
- PostgreSQL (runtime)
- Lombok
- jjwt (JSON Web Token)
- Maven (wrapper included)

## Prerequisites

- Java 17 (JDK)
- Maven (optional — wrapper `./mvnw` is included)
- PostgreSQL (or a managed PostgreSQL service)
- Optional: an IDE with Lombok support (e.g., IntelliJ + Lombok plugin)

## Quick start

1. Clone the repository
   git clone https://github.com/aditya3012singh/Todo-Backend-Springboot.git
   cd Todo-Backend-Springboot

2. Configure the database and application properties (see next section).

3. Build
   ./mvnw clean package

4. Run
   java -jar target/*.jar
   or using Maven:
   ./mvnw spring-boot:run

5. Open logs to verify the application started successfully and connected to the configured datasource.

## Configuration

The application uses Spring Boot configuration (application.properties / application.yml). Below is a sample `application.properties` you can use as a template. Replace values with your real database credentials and secrets.

```properties
# Server
server.port=8080

# Datasource (PostgreSQL example)
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=todo_user
spring.datasource.password=todo_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT (example names — update to match actual configuration keys used in code)
app.jwt.secret=your-very-secret-key
app.jwt.expiration-ms=3600000
```

If your project expects environment variables, you can also set the same values via environment variables:
- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- APP_JWT_SECRET (or the key used in your code)
- APP_JWT_EXPIRATION_MS

Adjust variable names to match the keys read in your configuration class.

## Database setup (PostgreSQL example)

Create database and user (run as a PostgreSQL superuser):

psql -U postgres
CREATE DATABASE todo_db;
CREATE USER todo_user WITH ENCRYPTED PASSWORD 'todo_password';
GRANT ALL PRIVILEGES ON DATABASE todo_db TO todo_user;

If you use `spring.jpa.hibernate.ddl-auto=update`, Spring JPA will create/update tables automatically at startup. For production, consider using a proper migration tool (Flyway or Liquibase) instead of `ddl-auto`.

## Running tests

Run all tests with:
./mvnw test

If there are integration tests that require a DB, set up a test database or use testcontainers/local settings.

## Common tasks

- Rebuild: ./mvnw clean package
- Run locally: ./mvnw spring-boot:run
- Run jar: java -jar target/todo-0.0.1-SNAPSHOT.jar

## Development notes

- Lombok is included as an optional dependency in pom.xml. Make sure your IDE has Lombok enabled to avoid compilation/IDE errors.
- JWT dependencies (jjwt) are present — check the security configuration classes to see expected property names for the secret and expiration.

## Contributing

Contributions are welcome. Typical workflow:
1. Fork the repository
2. Create a feature branch: git checkout -b feat/your-feature
3. Commit changes and push
4. Open a pull request with a clear description of what you changed and why

Please add a license file to the repo if you intend to make the project open-source under a specific license.

## Troubleshooting

- If the application fails to connect to the DB, verify `spring.datasource.url`, the DB user, and that PostgreSQL is running and accessible from your environment.
- If you see Lombok-related IDE errors, install and enable the Lombok plugin and re-import Maven project.
