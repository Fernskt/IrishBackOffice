# ART Backoffice

**ART Backoffice** es una aplicación web desarrollada con **Java Spring Boot** y **MySQL** bajo el patrón **MVC**. Su objetivo principal es gestionar y analizar siniestros de manera eficiente, permitiendo que distintos usuarios (administrador y analistas) puedan interactuar con la información de forma segura y escalable.

---

## Tabla de Contenido

1. [Características Principales](#características-principales)  
2. [Roles de Usuario](#roles-de-usuario)  
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)  
4. [Arquitectura del Proyecto](#arquitectura-del-proyecto)  
5. [Modelo de Datos](#modelo-de-datos)  
6. [Instalación y Configuración](#instalación-y-configuración)  
7. [Uso](#uso)  
8. [Seguridad](#seguridad)  
9. [Estado del Proyecto y Próximos Pasos](#estado-del-proyecto-y-próximos-pasos)  
10. [Contribuyendo](#contribuyendo)  
11. [Licencia](#licencia)  
12. [Contacto](#contacto)

---

## Características Principales

- **Gestión de siniestros:** Carga, edición y actualización de siniestros asociados a distintas ART.  
- **Asignación de siniestros:** El administrador asigna siniestros a uno o varios analistas.  
- **Múltiples perfiles de usuario:** Dependiendo del rol (Administrador o Analista), se puede acceder a funcionalidades específicas.  
- **Consulta rápida de casos:** Filtrado por estado (en gestión, vencidos, etc.).  
- **Reportes y estadísticas básicas:** Permite generar reportes de días de gestión, resultados de casos, nombre de la ART, etc.  
- **Escalable y seguro:** Construido en **Java Spring Boot** con **Spring Security**, garantizando protección de datos y acceso controlado.

---

## Roles de Usuario

1. **Administrador**  
   - Visualiza y gestiona **todos** los siniestros (vencidos, en gestión, etc.).  
   - Crea, edita y elimina **analistas** internos.  
   - Asigna siniestros a los analistas.  
   - Obtiene reportes globales (estadísticas, filtrado por fechas, analistas y ART).

2. **Analista Interno**  
   - Visualiza únicamente los siniestros **asignados**.  
   - Puede actualizar el estado, agregar notas, y marcar los siniestros como gestionados o vencidos.

---

## Tecnologías Utilizadas

- **Java** (versión 8+)
- **Spring Boot** (MVC, Data JPA, Security)
- **MySQL** (Base de datos relacional)
- **Maven** (Gestión de dependencias)
- **Tomcat embebido** (servidor integrado en Spring Boot)

---

## Arquitectura del Proyecto

El proyecto sigue la arquitectura **MVC**:
- **Modelo**: Entidades de JPA (por ejemplo, `Siniestro`, `Usuario`, `Asegurado`).
- **Vista**: Páginas web (Thymeleaf).
- **Controlador**: Endpoints REST / controladores que gestionan la lógica de negocio y la interacción con la base de datos.

El uso de **Spring Boot** hace que sea modular, fácilmente escalable y con inyección de dependencias.  
**Spring Security** protege el acceso a cada endpoint según el rol de usuario.

---

## Modelo de Datos

En el diagrama de la base de datos se contemplan las siguientes tablas principales:

- **`art`**  
  Registra las distintas Aseguradoras de Riesgos de Trabajo.
  
- **`asegurado`**  
  Información de la persona asegurada o accidentada (CUIT, DNI, nombre, empresa, etc.).  
  
- **`analista_ext`**  
  Datos de analistas externos (nombre, apellido, localidad, DNI).  

- **`usuario`**  
  Almacena los datos de los **usuarios internos** (Administrador o Analista), incluyendo `rol`, `email`, `contra`, etc.  

- **`siniestro`**  
  Núcleo de la aplicación. Contiene la información de cada siniestro (fechas, gravedad, observaciones).  
  Relacionada con `art` (quién provee la cobertura) y `usuario` (analista asignado).

## Modelo de Datos

El diagrama relacional contempla (entre otras) las siguientes tablas principales:

- **`usuario`**  
  - Guarda la información de los usuarios (Administrador, Analista Interno).  
  - Campos ejemplo: `dni`, `nombre`, `apellido`, `email`, `rol`, `contra`, etc.

- **`analista_ext`**  
  - Tabla pensada para almacenar analistas externos.  
  - Campos ejemplo: `dni`, `nombre`, `apellido`, `domicilio`, `localidad`, etc.

- **`art`**  
  - Almacena las distintas Aseguradoras de Riesgos de Trabajo.  
  - Campos ejemplo: `idart`, `nombreart`.

- **`siniestro`**  
  - Registra la información de cada siniestro.  
  - Campos ejemplo: `id_stro`, `num_stro`, `fecha_ingreso`, `fecha_vencimiento`, `graveda`, etc.  
  - Se relaciona con `art`, `usuario` (analista interno), e incluso `analista_ext`.

- **`asegurado`**  
  - Contiene información del asegurado/accidentado.  
  - Campos ejemplo: `cuit`, `dni`, `nombre`, `apellido`, `empresa`, etc.

---

## Instalación y Configuración



