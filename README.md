# 📦 Plataforma de Gestión de Stock con Predicción de Demanda

Este proyecto es una aplicación web desarrollada con **Spring Boot** que permite gestionar productos, movimientos de inventario y usuarios, incluyendo un **módulo de predicción de demanda**, autenticación basada en roles, y persistencia en **base de datos H2**.

---

## 🧩 Funcionalidades principales

- 👤 **Autenticación y autorización por roles** (`ADMIN`, `USER`, etc.)
- 📦 **Gestión de productos**
    - ABM completo
    - Activación/desactivación lógica
- 🔢 **Predicción de stock** basada en datos históricos
- 📈 **Gestión de movimientos de inventario** (entrada/salida)
- 🔐 Endpoints protegidos por **Spring Security**
- 🧪 Test de controllers, servicios y seguridad
- ☁️ Preparado para despliegue en la nube (Docker o Spring Cloud-ready)

---

## 🧠 Tecnologías utilizadas

| Herramienta / Framework | Uso |
|--------------------------|-----|
| **Java 17**              | Lenguaje principal |
| **Spring Boot 3**        | Framework backend |
| **Spring Data JPA**      | Persistencia de datos |
| **Spring Security**      | Seguridad y roles |
| **H2**                   | Base de datos en memoria |
| **Lombok**               | Reducción de boilerplate |
| **JUnit + Mockito**      | Testing unitario e integración |
| **Postman** / Swagger    | Pruebas de endpoints |
| **Git**                  | Control de versiones |

---

## 🛠️ Estructura del proyecto

src/
├── controller/ # Controladores REST
├── entity/ # Entidades JPA (ProductMaster, PredictorStock, User, Role, etc.)
├── repository/ # Interfaces JPA
├── service/ # Lógica de negocio
├── config/ # Configuración de seguridad
├── dto/ # (opcional) Objetos de transferencia
├── resources/
│ ├── application.properties
│ ├── schema.sql # Creación de tablas
│ └── data.sql # Datos iniciales
└── test/ # Test unitarios y de integración

---

## ✅ Cobertura de tests

Se realizaron pruebas con **JUnit 5** y **Mockito**, incluyendo:

- ✔️ Unit tests de `Controllers`: validación de endpoints y respuestas esperadas
- ✔️ Unit tests de `Services`: lógica de negocio aislada
- ✔️ Tests de `Repositories`: consultas JPA personalizadas
- ✔️ Tests de seguridad: autorización y acceso a endpoints
- ✔️ Tests del predictor: validación de cálculo de demanda (promedio + margen de seguridad)

---

## 🔐 Roles y Seguridad

La aplicación implementa **autenticación basada en usuarios con roles**:

- `ADMIN`: acceso completo a usuarios, productos, y movimientos.
- `USER`: acceso limitado a visualización y acciones específicas.

Seguridad implementada con:
- `BCryptPasswordEncoder`
- `@PreAuthorize` y control de acceso por endpoint
- Filtro personalizado de login
- ABM de usuarios con roles

---

## 🧪 Cómo correr los tests

Desde tu IDE o consola: Click derecho sobre java (test) y run test!


🧰 Cómo correr el proyecto
Pre-requisitos

    Java 17+
    Maven 3.8+