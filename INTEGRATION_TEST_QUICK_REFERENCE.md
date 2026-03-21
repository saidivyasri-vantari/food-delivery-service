# Quick Reference: Integration Test Configuration

## 🔍 Where Integration Tests Are Configured

### 1️⃣ **Test Configuration File**
```
📁 src/test/resources/application-test.properties
```
- **Purpose:** Contains all test-specific database and logging settings
- **Database:** H2 In-Memory Database
- **DDL:** `create-drop` (fresh schema for each test, cleaned up after)

### 2️⃣ **Maven Dependencies**
```
📁 pom.xml
```
- **Dependency:** `com.h2database:h2`
- **Scope:** `test` (only used during testing)

### 3️⃣ **Test Classes**
```
📁 src/test/java/com/ibc/training/fooddelivery/integration/
```
- **Annotation:** `@ActiveProfiles("test")`
- **Effect:** Loads `application-test.properties` instead of `application.properties`

---

## 📋 Configuration Summary

| Component | Location | Purpose |
|-----------|----------|---------|
| **Test Properties** | `src/test/resources/application-test.properties` | H2 database URL, JPA settings, logging |
| **H2 Dependency** | `pom.xml` | Provides in-memory database for testing |
| **Profile Activation** | `@ActiveProfiles("test")` | Switches from production to test config |
| **Spring Context** | `@SpringBootTest` | Loads full application context |
| **MockMvc Setup** | `@AutoConfigureMockMvc` | Enables REST endpoint testing |

---

## 🎯 Configuration Files

### application-test.properties
```properties
# Test Database (H2 In-Memory)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver

# Auto DDL (Create schema before tests, drop after)
spring.jpa.hibernate.ddl-auto=create-drop

# Logging (Minimal noise for test output)
logging.level.root=WARN
logging.level.com.ibc.training.fooddelivery=DEBUG
```

### pom.xml
```xml
<!-- H2 In-Memory Database for Integration Tests -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

---

## 🧪 Integration Test Example

```java
@SpringBootTest              // Loads full Spring context
@AutoConfigureMockMvc        // Configures MockMvc
@ActiveProfiles("test")      // Uses application-test.properties
public class CustomerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();  // Clean slate for each test
    }
    
    @Test
    public void testCreateCustomer() throws Exception {
        // Test uses H2 database from application-test.properties
    }
}
```

---

## 🔄 Configuration Flow

```
Test Execution
    ↓
@ActiveProfiles("test")
    ↓
Spring loads application-test.properties
    ↓
H2 Database Initialized (in-memory)
    ↓
Schema Created (DDL: create-drop)
    ↓
Tests Run (isolated, no production impact)
    ↓
Schema Dropped (cleanup)
    ↓
Next Test
```

---

## ✅ Key Features

- **✅ Isolation:** Each test runs on fresh H2 database
- **✅ No Setup:** H2 is in-memory, no external database needed
- **✅ Speed:** H2 is much faster than MySQL
- **✅ Clean:** `create-drop` ensures no test data leaks
- **✅ Profile-Based:** Automatic switching via `@ActiveProfiles`

---

## 📍 Files Involved

| File | Type | Content |
|------|------|---------|
| `application-test.properties` | Configuration | Database & logging settings |
| `pom.xml` | Dependency | H2 library inclusion |
| `*IntegrationTest.java` | Test Class | `@ActiveProfiles("test")` annotation |

---

## 🚀 Running Integration Tests

```bash
# Run all integration tests
mvn clean test -Dtest=*IntegrationTest

# Run specific integration test
mvn clean test -Dtest=CustomerIntegrationTest

# Run with debug output
mvn clean test -Dtest=*IntegrationTest -X
```

---

**Created:** March 21, 2026
**Purpose:** Integration Test Configuration Reference
**Scope:** Food Delivery Service Project

