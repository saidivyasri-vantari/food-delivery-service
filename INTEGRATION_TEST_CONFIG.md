# Integration Test Configuration Guide

## Where Integration Tests Are Configured

Integration tests in this project are configured in multiple places:

---

## 1. **Configuration File** 
📁 **Location:** `src/test/resources/application-test.properties`

This file contains all the test-specific configurations:

```properties
spring.application.name=food-delivery-service

# H2 In-Memory Database Configuration for Testing
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA / Hibernate settings for testing
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Logging configuration for tests
logging.level.root=WARN
logging.level.com.ibc.training.fooddelivery=DEBUG
logging.level.org.springframework.test=DEBUG
```

### Configuration Details:

| Property | Value | Purpose |
|----------|-------|---------|
| `spring.datasource.url` | `jdbc:h2:mem:testdb` | In-memory H2 database connection string |
| `spring.datasource.driverClassName` | `org.h2.Driver` | H2 JDBC driver for in-memory database |
| `spring.jpa.hibernate.ddl-auto` | `create-drop` | Auto-creates schema before tests, drops after |
| `spring.jpa.show-sql` | `false` | Disables SQL logging in tests (for performance) |
| `logging.level.root` | `WARN` | Minimal logging to reduce noise |

---

## 2. **Maven Dependencies**
📁 **Location:** `pom.xml`

The H2 database dependency is defined in the `pom.xml`:

```xml
<!-- H2 In-Memory Database for Integration Tests -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

**Key Points:**
- H2 is included with `<scope>test</scope>` - only used during testing
- Spring Boot's parent POM automatically manages the H2 version
- No version specified - uses the version managed by Spring Boot 4.0.3

---

## 3. **Test Classes Annotation**
📁 **Location:** Integration test files in `src/test/java/com/ibc/training/fooddelivery/integration/`

Each integration test class uses these annotations:

```java
@SpringBootTest                    // Loads full Spring context
@AutoConfigureMockMvc             // Configures MockMvc for REST testing
@ActiveProfiles("test")           // Activates the 'test' profile (application-test.properties)
public class CustomerIntegrationTest {
    // test methods
}
```

### What Each Annotation Does:

| Annotation | Purpose |
|-----------|---------|
| `@SpringBootTest` | Loads the entire Spring Boot application context for integration testing |
| `@AutoConfigureMockMvc` | Automatically configures MockMvc for testing REST endpoints |
| `@ActiveProfiles("test")` | Tells Spring to load `application-test.properties` instead of `application.properties` |

---

## 4. **How the Configuration Works**

### Profile Activation Flow:

```
Test Class
    ↓
@ActiveProfiles("test")
    ↓
Spring loads: application-test.properties
    ↓
Configuration:
  - H2 database initialized
  - Schema created (DDL: create-drop)
  - Table structure ready for testing
    ↓
Tests Execute
    ↓
Schema Dropped (cleanup)
    ↓
Next Test Class
```

---

## 5. **Integration Test Files Using This Configuration**

The following integration test files use this configuration:

- ✅ `CustomerIntegrationTest.java`
- ✅ `DriverIntegrationTest.java`
- ✅ `OrderIntegrationTest.java`
- ✅ `MenuItemIntegrationTest.java`
- ✅ `RestaurantIntegrationTest.java`

Each uses: `@ActiveProfiles("test")` to activate `application-test.properties`

---

## 6. **Comparison: Production vs Test Configuration**

### Production Configuration (`application.properties`)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodservice
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Test Configuration (`application-test.properties`)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
```

**Key Differences:**
- Production: MySQL database (persistent)
- Testing: H2 in-memory (temporary, fast)
- Production: `ddl-auto=update` (updates existing schema)
- Testing: `ddl-auto=create-drop` (fresh schema each time)

---

## 7. **Directory Structure**

```
food-delivery-service/
├── src/
│   ├── main/
│   │   └── resources/
│   │       └── application.properties          (Production config)
│   └── test/
│       ├── java/
│       │   └── com/ibc/training/fooddelivery/
│       │       ├── integration/                 (Integration tests)
│       │       │   ├── CustomerIntegrationTest.java
│       │       │   ├── DriverIntegrationTest.java
│       │       │   ├── OrderIntegrationTest.java
│       │       │   ├── MenuItemIntegrationTest.java
│       │       │   └── RestaurantIntegrationTest.java
│       │       ├── service/                     (Service unit tests)
│       │       ├── controller/                  (Controller unit tests)
│       │       └── dao/                         (DAO unit tests)
│       └── resources/
│           └── application-test.properties     (Test config)
└── pom.xml                                      (H2 dependency)
```

---

## 8. **Running Tests with Specific Configurations**

### Run all tests (uses application-test.properties for integration tests)
```bash
mvn clean test
```

### Run only integration tests
```bash
mvn clean test -Dtest=*IntegrationTest
```

### Run with test output (shows SQL from H2)
```bash
mvn clean test -Dtest=*IntegrationTest -X
```

---

## 9. **Key Configuration Features**

✅ **Isolation** - Each test gets a fresh H2 database
✅ **Speed** - In-memory database is much faster than MySQL
✅ **No Side Effects** - `create-drop` ensures clean state for each test
✅ **No Production Data** - Tests don't touch production MySQL
✅ **Easy Setup** - H2 requires no external database setup
✅ **Profile-Based** - `@ActiveProfiles("test")` makes it automatic

---

## Summary

**Configuration Layers:**

1. **application-test.properties** → H2 database settings
2. **pom.xml** → H2 dependency
3. **@ActiveProfiles("test")** → Activates test properties
4. **@SpringBootTest** → Loads full Spring context
5. **@AutoConfigureMockMvc** → Enables REST testing

This creates a **complete isolation** between test and production environments!

