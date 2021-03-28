# rest-api-2
Customer and Debts rest API

Supports the following functionality:
- CRUD (create, read, update, delete)
- [Customer] user submits: name, surname, country, email, password
- [Customer' debt] user submits amount, currency, due date. A debt is linked to existing customer
- Application configured to port:8080

Project based on:
- Java 8
- Spring Boot
- Spring WebSecurity basic authentication
- Swagger2 (http://localhost:8080/swagger-ui.html)
- MySQL
- Project Lombok

Uses: logback (writes logs to "/logs" dir)

*SQL starter script added to resources/sql)

*mvn install compiles jar/tar/zip packages