# Integration Test Configuration - Complete Guide

## 📦 Answer to: "Where is Integration Test Configuration?"

Integration tests are configured in **THREE** main places:

---

## 1️⃣ TEST CONFIGURATION FILE

### File Path:
```
src/test/resources/application-test.properties
```

### File Content:
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

### What It Does:
```
┌─────────────────────────────────────┐
│   application-test.properties       │
├─────────────────────────────────────┤
│ ✓ Configures H2 In-Memory Database  │
│ ✓ Sets JPA/Hibernate behavior       │
│ ✓ Configures logging levels         │
│ ✓ Schema auto-create/drop settings  │
└─────────────────────────────────────┘
```

---

## 2️⃣ MAVEN DEPENDENCY

### File Path:
```
pom.xml
```

### Dependency Configuration:
```xml
<!-- H2 In-Memory Database for Integration Tests -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

### What It Does:
```
┌──────────────────────────────────┐
│    H2 Database Dependency        │
├──────────────────────────────────┤
│ ✓ Provides in-memory database    │
│ ✓ Only included in test scope    │
│ ✓ Not shipped to production      │
│ ✓ Auto-managed by Spring Boot    │
└──────────────────────────────────┘
```

---

## 3️⃣ TEST CLASS ANNOTATIONS

### File Path:
```
src/test/java/com/ibc/training/fooddelivery/integration/
    - CustomerIntegrationTest.java
    - DriverIntegrationTest.java
    - OrderIntegrationTest.java
    - MenuItemIntegrationTest.java
    - RestaurantIntegrationTest.java
```

### Annotation Configuration:
```java
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")          // ← This activates application-test.properties
public class CustomerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // Tests here...
}
```

### What It Does:
```
┌────────────────────────────────────────────┐
│    @ActiveProfiles("test")                 │
├────────────────────────────────────────────┤
│ ✓ Tells Spring to load test properties    │
│ ✓ Activates application-test.properties   │
│ ✓ Uses H2 database instead of MySQL       │
│ ✓ Applies test-specific configurations    │
└────────────────────────────────────────────┘
```

---

## 🔗 How They Work Together

```
┌─────────────────────────────────────────────────────────────┐
│                    TEST EXECUTION FLOW                       │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  1. Test Class with @ActiveProfiles("test")                 │
│       ↓                                                       │
│  2. Spring Boot loads @SpringBootTest context               │
│       ↓                                                       │
│  3. Spring activates "test" profile                          │
│       ↓                                                       │
│  4. Loads application-test.properties                        │
│       ↓                                                       │
│  5. H2 dependency (from pom.xml) initializes                │
│       ↓                                                       │
│  6. In-memory database created (jdbc:h2:mem:testdb)         │
│       ↓                                                       │
│  7. Schema auto-created (DDL: create-drop)                  │
│       ↓                                                       │
│  8. Tests execute with fresh database                        │
│       ↓                                                       │
│  9. Schema auto-dropped (cleanup)                            │
│       ↓                                                       │
│ 10. Next test gets fresh database                            │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 📊 Configuration Summary Table

| Component | Location | Configuration |
|-----------|----------|----------------|
| **Database URL** | application-test.properties | `jdbc:h2:mem:testdb` |
| **Database Driver** | application-test.properties | `org.h2.Driver` |
| **DDL Strategy** | application-test.properties | `create-drop` |
| **H2 Library** | pom.xml | `com.h2database:h2` |
| **Profile Activation** | Test Class | `@ActiveProfiles("test")` |
| **Spring Context** | Test Class | `@SpringBootTest` |
| **REST Testing** | Test Class | `@AutoConfigureMockMvc` |

---

## 🎯 Production vs Test Configuration

### Production Setup:
```
application.properties
  ↓
MySQL Database (localhost:3306/foodservice)
  ↓
Real persistent data
  ↓
Production MySQL Server
```

### Test Setup:
```
application-test.properties
  ↓
H2 In-Memory Database (jdbc:h2:mem:testdb)
  ↓
Temporary isolated data
  ↓
No external database needed
```

---

## ✅ Quick Checklist

- ✅ `application-test.properties` exists in `src/test/resources/`
- ✅ H2 dependency added to `pom.xml` with `<scope>test</scope>`
- ✅ Integration test classes annotated with `@ActiveProfiles("test")`
- ✅ Each integration test has `@SpringBootTest`
- ✅ Each integration test has `@AutoConfigureMockMvc`
- ✅ Database auto-creates with `create-drop` strategy
- ✅ No production database is touched during testing
- ✅ All tests run in isolation with fresh schema

---

## 📝 Summary

**Integration tests are configured in 3 files:**

1. **`application-test.properties`** - Test database settings (H2)
2. **`pom.xml`** - H2 database dependency
3. **Test classes** - `@ActiveProfiles("test")` annotation

**These 3 work together to:**
- Load test configuration automatically
- Use H2 in-memory database
- Create fresh schema for each test
- Provide complete test isolation
- Ensure zero production impact

