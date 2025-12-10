Ecomerce Uribe
🚀 Tecnologías principales
        Java 17
        Spring Boot 3.5.5
        Spring Web (controladores REST)
        Spring Data JPA
        H2 Database (en memoria)
        MapStruct (mapeo DTO–Entidad)
        Swagger (SpringDoc OpenAPI) para documentación
        Maven

🧩 Dependencias principales (pom.xml)
        El proyecto incluye:
        spring-boot-starter-web: soporte para controladores REST.
        spring-boot-starter-data-jpa: ORM con Hibernate.
        h2: base de datos embebida para desarrollo y pruebas.
        springdoc-openapi-starter-webmvc-ui: interfaz Swagger/UI.
        mapstruct + mapstruct-processor: conversión DTO–Entidad.

⚙ Configuración del proyecto (application.properties)
    spring.application.name=EcomerceUribe
    spring.datasource.url=jdbc:h2:mem:demo_db
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=create
    springdoc.swagger-ui.path=/docs
    springdoc.api-docs.path=/api-docs

Explicación:
        H2 en memoria: se crea automáticamente al iniciar la app.
        ddl-auto=create: genera todas las tablas al arrancar.
        Swagger UI disponible en:
            👉 http://localhost:8080/docs
        OpenAPI JSON:
            👉 http://localhost:8080/api-docs

▶ Cómo ejecutar el proyecto
        1. Clonar el repositorio
            git clone https://github.com/usuario/EcomerceUribe.git
        2. Entrar al proyecto
           cd EcomerceUribe
        3. Ejecutar con Maven
            mvn spring-boot:run
        4. Ingresar a la documentación Swagger
            http://localhost:8080/docs

🗄 Acceso a la base de datos H2
    Spring Boot habilita automáticamente la consola H2.
        Accede a:
            http://localhost:8080/h2-console
        Usa los mismos datos del properties:
            JDBC URL: jdbc:h2:mem:demo_db
            User: sa
            Password: (vacío)

📄 Licencia
        Este proyecto puede usarse con fines educativos y de práctica.
