# Backend Campus Sync

Proyecto de pago de matricula financiera de una universidad

## Problema

![Problema](https://github.com/user-attachments/assets/83fc9414-1827-4538-9d8a-7d2a8987c83d)

## Diagrama de clases

![Class Diagram](https://github.com/user-attachments/assets/7bbdab34-db5b-4323-9b3d-eccf69b77532)

## Diagrama de componentes

![Patron-Facade-Componentes](https://github.com/user-attachments/assets/dcdb516f-9c91-45b1-b90d-f3878d5aedfc)

## Configuracion de la base de datos

```text
spring.application.name=campus_sync
! Configuraciones para conectar a MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/campus_sync?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=#Victor2002
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
