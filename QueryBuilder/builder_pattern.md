* Design a Java-based SQL Query Builder using the Builder Design Pattern. The builder should support creating SELECT queries in a fluent and chainable manner.


* Before implementation learn about  `fluent interface` 

### You should be able to construct SQL queries like:

```
String query = QueryBuilder.select("id", "name", "email")
    .from("users")
    .where("age > 18")
    .and("status = 'active'")
    .orderBy("name")
    .limit(10)
    .build();
```


### Expected output:

```	
SELECT id, name, email FROM users WHERE age > 18 AND status = 'active' ORDER BY name LIMIT 10;
```

### Requirements

Build a QueryBuilder class that supports:
Core methods:

```
    select(String... columns)

    from(String table)

    where(String condition)

    and(String condition)

    or(String condition)

    orderBy(String column)

    limit(int count)

    build() – returns the final SQL query as a String
```

### Behavior Rules

```
    select() must be called first.

    from() must follow select().

    where() is optional, but if present, must come before and() or or().

    orderBy() and limit() are optional.

    build() should throw IllegalStateException if select() or from() is missing.
```

### Sample Usages
```
QueryBuilder qb = QueryBuilder
    .select("name", "email")
    .from("employees")
    .where("department = 'IT'")
    .or("department = 'HR'")
    .orderBy("name")
    .limit(5);

String sql = qb.build();
System.out.println(sql);
```

### output
```
SELECT name, email FROM employees WHERE department = 'IT' OR department = 'HR' ORDER BY name LIMIT 5;
```
