# Food Delivery Service - Test Suite Documentation

## Test Structure Overview

The test suite is organized into separate categories for better maintainability and clarity:

### 1. DAO Layer Tests (`src/test/java/com/ibc/training/fooddelivery/dao/`)
Unit tests for Data Access Objects with mocked repositories.

- **CustomerDaoTest.java** - Tests CRUD operations for Customer DAO
- **DriverDaoTest.java** - Tests CRUD operations for Driver DAO
- **OrderDaoTest.java** - Tests CRUD operations for Order DAO
- **MenuItemDaoTest.java** - Tests CRUD operations for MenuItem DAO
- **RestaurantDaoTest.java** - Tests CRUD operations for Restaurant DAO

**Test Framework:** JUnit 5 with Mockito
**Approach:** Mocked repositories, unit testing only

### 2. Service Layer Tests (`src/test/java/com/ibc/training/fooddelivery/service/`)
Unit tests for business logic services with mocked DAOs.

- **CustomerServiceTest.java** - Tests customer business logic
- **DriverServiceTest.java** - Tests driver business logic
- **OrderServiceTest.java** - Tests order business logic
- **MenuItemServiceTest.java** - Tests menu item business logic
- **RestaurantServiceTest.java** - Tests restaurant business logic

**Test Framework:** JUnit 5 with Mockito
**Approach:** Mocked DAOs and mappers, unit testing only

### 3. Controller Layer Tests (`src/test/java/com/ibc/training/fooddelivery/controller/`)
Unit tests for REST endpoints with mocked services.

- **CustomerControllerTest.java** - Tests customer API endpoints
- **DriverControllerTest.java** - Tests driver API endpoints
- **OrderControllerTest.java** - Tests order API endpoints
- **MenuItemControllerTest.java** - Tests menu item API endpoints
- **RestaurantControllerTest.java** - Tests restaurant API endpoints

**Test Framework:** JUnit 5 with MockMvc
**Approach:** Mocked services, endpoint testing with JSON validation

### 4. Integration Tests (`src/test/java/com/ibc/training/fooddelivery/integration/`)
End-to-end tests with full Spring Boot context and real database.

- **CustomerIntegrationTest.java** - Full customer workflow testing
- **DriverIntegrationTest.java** - Full driver workflow testing
- **OrderIntegrationTest.java** - Full order workflow testing
- **MenuItemIntegrationTest.java** - Full menu item workflow testing
- **RestaurantIntegrationTest.java** - Full restaurant workflow testing

**Test Framework:** Spring Boot Test with MockMvc
**Approach:** Full context loading, real database operations

## Running the Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Layer
```bash
# Run only DAO tests
mvn clean test -Dtest=*DaoTest

# Run only Service tests
mvn clean test -Dtest=*ServiceTest

# Run only Controller tests
mvn clean test -Dtest=*ControllerTest

# Run only Integration tests
mvn clean test -Dtest=*IntegrationTest
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=CustomerControllerTest
```

### Run with Coverage Report
```bash
mvn clean test jacoco:report
```

## Test Coverage Summary

| Layer | Coverage | Focus |
|-------|----------|-------|
| DAO | Unit | CRUD operations, query methods |
| Service | Unit | Business logic, exception handling |
| Controller | Unit | HTTP status codes, JSON responses |
| Integration | E2E | Full workflows, database interactions |

## Key Testing Practices

✅ **Unit Tests:**
- Mock all external dependencies
- Test success and failure scenarios
- Verify mock interactions
- Focus on single responsibility

✅ **Controller Tests:**
- Use MockMvc for endpoint testing
- Validate HTTP status codes
- Test request/response JSON
- Verify service method calls

✅ **Integration Tests:**
- Use @SpringBootTest for full context
- Test real database interactions
- Validate complete workflows
- Clean up data between tests with @BeforeEach

## Test Configuration

### Integration Test Configuration
Integration tests use a separate Spring Boot Test profile with H2 in-memory database.

**Configuration File:** `src/test/resources/application-test.properties`

```properties
# H2 In-Memory Database for Testing
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA / Hibernate settings
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false

# Logging configuration
logging.level.root=WARN
logging.level.com.ibc.training.fooddelivery=DEBUG
```

### Profile Activation
Integration tests activate the test profile using the `@ActiveProfiles("test")` annotation:

```java
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerIntegrationTest {
    // test methods
}
```

### Key Test Configuration Features
- **H2 Database:** In-memory database for isolated testing
- **DDL Strategy:** `create-drop` - Creates schema before tests, drops after
- **Database Isolation:** Each test class runs independently
- **No Production Data:** Tests use isolated, temporary database
- **Fast Execution:** In-memory H2 is much faster than MySQL

## Test Dependencies

- **JUnit 5:** Core test framework
- **Mockito:** Mocking framework
- **Spring Boot Test:** Integration testing
- **Spring Test:** MockMvc for controller testing
- **Jackson:** JSON serialization/deserialization
- **H2 Database:** In-memory database for integration tests

## Future Enhancements

- Add test cases for edge cases and boundary conditions
- Add performance/load testing
- Add security testing
- Add API documentation tests
- Increase code coverage to >80%
- Add testcontainers for MySQL integration tests
- Add database migration testing with Flyway/Liquibase

