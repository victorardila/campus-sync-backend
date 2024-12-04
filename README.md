# Backend Campus Sync

Proyecto de pago de matricula financiera de una universidad

## Sprint Boot y Docker

<p align="center">
  <img src="https://github.com/user-attachments/assets/a9e32ce8-8523-47b4-83a6-fce54b05e894" alt="Docher and Sprint Boot" width="500"/>
</p>

## Problema

![Problema](https://github.com/user-attachments/assets/83fc9414-1827-4538-9d8a-7d2a8987c83d)

## Diagrama de clases

![Class Diagram](https://github.com/user-attachments/assets/7bbdab34-db5b-4323-9b3d-eccf69b77532)

## Diagrama de componentes

![Diagrama de componentes](https://github.com/user-attachments/assets/07410fc3-92ba-42f7-9462-c81c7af0c4cf)

## Configuracion de la base de datos

### Configuraciones para conectar a MySQL

```text
# Configuración de la base de datos MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/campus_sync?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=#Victor2002

# Configuración de JPA e Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Configuraciones para conectar a Aurora MySQL en AWS

```text
# Configuración de la base de datos MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://aurora-db.cluster-xxxxxx.us-east-1.rds.amazonaws.com:3306/nombre_de_tu_base?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración de JPA e Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Comandos de docker para Pop! Os

- #### *`Configurar docker`*

```bash
# Agregar tu usuario al grupo docker
sudo groupadd docker
# Añadir tu usuario al grupo docker
sudo usermod -aG docker $USER
# Verificar que el usuario está realmente en el grupo docker
groups $USER
# Verificar permisos en docker.sock
ls -l /var/run/docker.sock
# verificar el estado de Docker
snap services docker
# listar los contenedores de Docker que están en ejecución
docker ps
```

- #### *`Gestion de docker`*

```bash
# Iniciar Docker
snap start docker
# Reiniciar Docker
snap restart docker
# verificar el estado de Docker
snap services docker
# listar los contenedores de Docker que están en ejecución
docker ps
```
