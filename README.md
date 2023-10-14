Шлюз

Работает в паре с 

Для проксирования запроса /svc1/** на http://localhost:8081/**

application.yml:
```yml
spring:
  cloud:
    gateway:
      routes:
        - id: svc1_route
          uri: http://localhost:8081/**
          predicates:
            - Path=/svc1/**
          filters:
            - StripPrefix=1
```
Проверить можно выполнив запрос. Пример запроса:
```bash
curl http://localhost:8080/svc1/api/v1/status
```
будет перенаправлен на http://localhost:8081/api/v1/status



