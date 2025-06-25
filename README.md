# Irish Backoffice

**Irish Backoffice** Irish Backoffice es una aplicación empresarial full-stack diseñada para la gestión integral de siniestros. Está compuesta por:

- **Backend RESTful:** en Java Spring Boot (versión 3.x), con Spring Data JPA y MySQL como motor de datos.
- **Frontend:** en React (v18+), utilizando React Router, React Query y Bootstrap para la interfaz y el manejo asíncrono de datos.
- **Autenticación y autorización:** con Spring Security y JWT, implementados mediante roles (ADMIN, ANALISTA) para controlar permisos sobre endpoints y vistas.


---

## Tabla de Contenido

1. [Propósito y Alcance](#proposito-y-alcance)  
2. [Características Principales](#características-principales)  
3. [Roles de Usuario](#roles-de-usuario)  
4. [Tecnologías Utilizadas](#tecnologías-utilizadas)  
5. [Arquitectura del Proyecto](#arquitectura-del-proyecto)  
6. [Modelo de Datos](#modelo-de-datos)  
7. [Instalación y Configuración](#instalación-y-configuración)  
8. [Uso](#uso)  
9. [Seguridad](#seguridad)  
10. [Estado del Proyecto y Próximos Pasos](#estado-del-proyecto-y-próximos-pasos)  
11. [Contribuyendo](#contribuyendo)  
12. [Licencia](#licencia)  
13. [Contacto](#contacto)

---
## Propósito y Alcance

Irish Backoffice nace para abordar los desafíos de la gestión manual de siniestros en organizaciones de ART y prestadores médicos:

- **Problema actual:**  procesos dispersos en hojas de cálculo o sistemas independientes generan demoras, pérdida de trazabilidad y falta de métricas precisas.

- **Solución propuesta:** una plataforma unificada que centraliza cada etapa —desde la carga inicial hasta el cierre del caso— con asignación dinámica, filtros avanzados y reportes en tiempo real.

- **Beneficio clave:** reduce tiempos de gestión, mejora la colaboración entre administradores y analistas, y proporciona visibilidad instantánea del estado y rendimiento de los siniestros.

---

## Características Principales

- **Gestión de siniestros:** alta, edición, eliminación y consulta de casos.  
- **Asignación dinámica:** el administrador puede asignar uno o varios analistas a cada siniestro.  
- **Filtros avanzados:** búsqueda por número (numStro), ART, analista, tipo de siniestro, tipo de investigación, resultado y ordenación por fecha de ingreso. 
- **Reportes y estadísticas:** componentes reutilizables (tablas, formularios, modales), hooks con React Query para sincronización de datos en tiempo real.  
- **Interfaz modular en React:** Permite generar reportes de días de gestión, resultados de casos, nombre de la ART, etc.  
- **Escalabilidad y rendimiento:** separación frontend-backend, caché de consultas y revalidación automática tras mutaciones.

---

## Roles de Usuario

1. **ADMIN**  
   - Visualiza y gestiona **todos** los siniestros (vencidos, en gestión, etc.).  
   - Registro y gestión de usuarios (Analistas, Auditores). 
   - Crea, edita y elimina **analistas** internos.  
   - Asigna siniestros a los analistas.  
   - Creación, edición y eliminación de siniestros.  
   - Obtiene reportes globales (estadísticas, filtrado por fechas, analistas y ART).

2. **ANALISTA**  
   - Visualiza y edita únicamente los siniestros **asignados**.  
   - Puede actualizar el estado, agregar notas, y marcar los siniestros como aceptados o rechazados.

---

## Tecnologías Utilizadas

- **Backend:**

- **Java 17** (Spring Boot 3.x)
- **Spring Data JPA** (Especificaciones dinámicas)
- **MySQL** (Base de datos relacional)
- **Maven** (Gestión de dependencias)
- **Spring Security + JWT**
- **Tomcat embebido** (servidor integrado en Spring Boot)

-**Frontend:**

- **React**
- **React Query** (caching y sincronización)
- **React Router v6**
- **JBootstrap 5**
- **Redux** (o Context API) para manejo de token JWT

---

## Arquitectura del Proyecto

┌──────────┐      ┌─────────────┐      ┌───────────┐
│ Frontend │────▶│  Backend     ─────▶│  MySQL    │
│  React   │      │ Spring Boot │      │ Base de   │
└──────────┘      └─────────────┘      │  Datos    │
                                       └───────────┘
- **Frontend**: rutas públicas y protegidas, formularios con validación nativa de Bootstrap, tablas con filtros, modales reutilizables y hooks para llamadas a la API.
- **Backend**: controladores REST (@RestController), servicios con lógica de negocio, repositorios JPA, especificaciones para filtros, seguridad con filtros JWT.

El uso de **Spring Boot** hace que sea modular, fácilmente escalable y con inyección de dependencias.  
**Spring Security** protege el acceso a cada endpoint según el rol de usuario.

---

## Modelo de Datos

En el diagrama de la base de datos se contemplan las siguientes tablas principales:

- **`art`**  
 - idART, nombreART, datos de analista responsable.
 - *`@OneToMany`* con *`Siniestro`*.

  
- **`Auditor, Trabajador, Asegurado`**  
  entidades auxiliares con sus propios campos y relaciones con *`Siniestro`*. 
  
- **`analista_ext`**  
  Datos de analistas externos (nombre, apellido, localidad, DNI, etc).  

- **`usuario`**  
  - id (UUID), nombre, apellido, dni, rol (ENUM: ADMIN / ANALISTA ), email, contraseña.
  - Relación @OneToMany con Siniestro (siniestros asignados).

- **`siniestro`**  
 - Núcleo de la aplicación. Contiene la información de cada siniestro (fechas, gravedad, observaciones).  
 - idStro, numStro, fechaIngreso, fechaVencimiento, tipoStro, tipoInvestigacion, resultado, lugar, gravedad, observaciones, recupero, esAceptado.
 - Relaciones: `@ManyToOne` con `Art`, `Usuario` (analista), `Trabajador`; `@OneToOne` con `Asegurado`; `@ManyToOne` con `Auditor`.

---

## Instalación y Configuración

1. **Clona el repositorio**  
   ```bash
   git clone https://github.com/Fernskt/IrishBackOffice.git
   cd IrishBackOffice
   ```

2. **Configura tu base de datos MySQL**
   - Crea una base de datos, por ejemplo: `art_backoffice`
   - Ajusta tus credenciales en el archivo `src/main/resources/application.properties:`

     
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/art_backoffice
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Compila y ejecuta la aplicación**
   
   - Desde Maven:
     
   ```bash
   mvn spring-boot:run
   ```

   - O bien genera el `.jar` y ejecútalo
  
    ```bash
   mvn clean package
   java -jar target/art-backoffice-0.0.1-SNAPSHOT.jar
   ```
4. **Accede la aplicación**

   - Por defecto, la aplicación se inicia en:
     http://localhost:8080/

## Uso

1. **Inicio de sesión**
   - Utiliza un usuario y contraseña válidos (por ejemplo, `admin / admin123`).
  
2. **Administrador**
   - Gestiona la lista completa de siniestros (en gestión, vencidos, etc.).
   - Crea, edita y elimina usuarios (analistas).
   - Asigna siniestros a cada analista.
   - Genera reportes y visualiza estadísticas (fechas, ART, analista, etc.).
  
3. **Analista Interno**

   - Visualiza únicamente los siniestros asignados.
   - Actualiza la información de cada siniestro (estado, observaciones, etc.).
  
4. **Asignar analistas:**

   - desde la tabla con <select>Analista</select>.

5. **Ver resumen:**

   - modal o vista de detalle con información completa.

6. **Reportes**
   - Acceder al módulo de estadísticas en el menú lateral (días de gestión, resultados por ART, etc.).
   - Permite filtrar casos por **estado**, fechas, ART o analista.
   - **Módulo de facturación** (pendiente de desarrollo).

7. **Carga de siniestros:**

   - datos obligatorios, ART, trabajador.
  
## Seguridad

   - **Spring Security + JWT** se encarga de la autenticación y autorización de peticiones.
   - Roles principales:
      - `ADMIN`: tiene todos los permisos; 
      - `ANALISTA`: con permisos limitados según el caso de uso.
   - **Encriptación BCrypt** para contraseñas.
   - **Validación de token** en cada petición HTTP (interceptor REST).
   - **CORS configurado** solo para el dominio del frontend.
   - Los endpoints y vistas se protegen de acuerdo con el rol de cada usuario.

## Estado del Proyecto y Próximos Pasos
- **Estado Actual:** MVP en desarrollo.
- **Próximas Mejoras:**
   - Funcionalidad de facturación.
   - Soporte para analistas externos.
   - Optimización de consultas.
   - Mejoras de UI/UX (validaciones, interfaz más intuitiva, etc.).
 
## Contribuyendo
   **¡Las contribuciones son bienvenidas!**

1. Haz un **fork** del repositorio.
2. Crea una rama descriptiva (por ejemplo: `feature/mejora-reportes`).
3. Aplica tus cambios y actualiza la documentación si es necesario.
4. Envía un **pull request**.

## Licencia
(C) **Hasperué Fernando, 2025**
- *Este software es confidencial y no puede distribuirse sin autorización.*

## Contacto
- **Autor / Maintainer:** Hasperué Fernando
- **Email:** fernando.hasperue@gmail.com
- **LinkedIn:** https://www.linkedin.com/in/hasperue/
