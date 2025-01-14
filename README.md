Sistema de Gestión de Reservaciones para Restaurantes

Este proyecto implementa un sistema para gestionar reservaciones en un restaurante, asegurando que no haya conflictos de horarios.

Características

CRUD completo para reservaciones.

Validación para evitar horarios duplicados.

Arquitectura basada en Spring Boot, Spring Data JPA, y MySQL.

Requisitos

Software: Java 17, Maven, MySQL.

Base de datos:

CREATE DATABASE restaurante;

Configuración en application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/restaurante
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Ejecución

Clonar el repositorio:

git clone https://github.com/usuario/repositorio.git
cd repositorio

Compilar y ejecutar:

mvn spring-boot:run

Endpoints

Base URL: /api/reservaciones

Crear reservación:

POST /api/reservaciones

Body:

{
    "fecha": "2025-01-15",
    "idHorario": 1
}

Consultar reservaciones:

GET /api/reservaciones?fecha=2025-01-15

Actualizar reservación:

PUT /api/reservaciones/{id}

Body:

{
    "fecha": "2025-01-16",
    "idHorario": 2
}

Eliminar reservación:

DELETE /api/reservaciones/{id}


Notas

Validación de duplicados: Se usa el método existsByFechaAndIdHorario en el repositorio.

Errores comunes: Problemas con consultas derivadas resueltos ajustando nombres de atributos.

Autor: Camilo Chona

Cualquier mejora o sugerencia es bienvenida.

