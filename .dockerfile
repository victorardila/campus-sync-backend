# Etapa de construcción (builder)
FROM gradle:jdk17-alpine AS builder

WORKDIR /app
COPY . /app

# Construir el JAR excluyendo las pruebas
RUN gradle build -x test

# Etapa de ejecución (runtime)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el archivo JAR construido desde la etapa de construcción
COPY --from=builder /app/build/libs/*-all.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Variables de entorno necesarias para Spring Boot
ENV SPRING_APPLICATION_NAME=campus_sync
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
ENV SPRING_DATASOURCE_URL=jdbc:mysql://<AWS_RDS_ENDPOINT>:3306/campus_sync?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=<YOUR_RDS_USERNAME>
ENV SPRING_DATASOURCE_PASSWORD=<YOUR_RDS_PASSWORD>
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-Dspring.application.name=${SPRING_APPLICATION_NAME}", \
    "-Dspring.datasource.driver-class-name=${SPRING_DATASOURCE_DRIVER_CLASS_NAME}", \
    "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
    "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
    "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", \
    "-Dspring.jpa.hibernate.ddl-auto=${SPRING_JPA_HIBERNATE_DDL_AUTO}", \
    "-Dspring.jpa.show-sql=${SPRING_JPA_SHOW_SQL}", \
    "-Dspring.jpa.properties.hibernate.dialect=${SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT}", \
    "-jar", "app.jar"]
