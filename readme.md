# Refugio Animal API

Bienvenido al **Refugio Animal API**, una aplicación desarrollada con Spring Boot para gestionar las operaciones de un refugio de animales. Este API permite registrar nuevos usuarios (cuidadores y administradores) y gestionar el inicio de sesión de los usuarios registrados.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Endpoints de la API](#endpoints-de-la-api)
    - [Registrar Usuario](#registrar-usuario)
    - [Iniciar Sesión](#iniciar-sesión)
- [Ejemplos de Solicitudes](#ejemplos-de-solicitudes)
    - [Registrar Usuario](#registrar-usuario-1)
    - [Iniciar Sesión](#iniciar-sesión-1)
- [Respuesta de la API](#respuesta-de-la-api)
    - [Registrar Usuario](#registrar-usuario-2)
    - [Iniciar Sesión](#iniciar-sesión-2)
- [Consideraciones de Seguridad](#consideraciones-de-seguridad)
- [Contacto](#contacto)

## Requisitos

- **Java 17** o superior
- **Gradle** para la gestión de dependencias
- **MySQL** como base de datos
- **Postman/Insomnia** o cualquier otra herramienta para probar las APIs

## Configuración del Entorno

1. **Clonar el Repositorio:**

   ```bash
   git clone https://github.com/fabian001254/RefugioAnimalPoli.git
   cd RefugioAnimalPoli
   ```

2. **Configurar la Base de Datos:**
    - Crear una base de datos en MySQL llamada `refugio_animal`.
    - Actualizar las credenciales en `src/main/resources/application.properties` según tu configuración local.

3. **Construir y Ejecutar la Aplicación:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   La aplicación estará disponible en `http://localhost:8080`.

## Endpoints de la API

### Registrar Usuario

- **URL:** `/usuario/registrar`
- **Método HTTP:** `POST`
- **Descripción:** Registra un nuevo usuario en el sistema. Puede ser un cuidador o un administrador.
- **Cuerpo de la Solicitud:** JSON con los detalles del usuario.

### Iniciar Sesión

- **URL:** `/usuario/login`
- **Método HTTP:** `POST`
- **Descripción:** Autentica a un usuario existente y permite el acceso al sistema.
- **Cuerpo de la Solicitud:** JSON con las credenciales del usuario.

## Ejemplos de Solicitudes

### Registrar Usuario

- **Endpoint:**

  ```bash
  POST http://localhost:8080/usuario/registrar
  ```

- **Cuerpo de la Solicitud:**

  ```json
  {
    "nombreUsuario": "Test",
    "contrasena": "Test123",
    "nombreCompleto": "Test Aguilar",
    "email": "test@test.com",
    "telefono": "309890474",
    "rol": "CUIDADOR"
  }
  ```

### Iniciar Sesión

- **Endpoint:**

  ```bash
  POST http://localhost:8080/usuario/login
  ```

- **Cuerpo de la Solicitud:**

  ```json
  {
    "nombreUsuario": "Test1",
    "contrasena": "Test123"
  }
  ```

## Respuesta de la API

### Registrar Usuario

- **Éxito** (`201 Created`):

  ```json
  {
    "mensaje": "Usuario registrado exitosamente",
  }
  ```

- **Fallo** (`400 Bad Request`):

  ```json
  {
    "error": "Nombre de usuario ya existe"
  }
  ```

### Iniciar Sesión

- **Éxito** (`204 No Content`):
    - No se retorna ningún contenido en el cuerpo de la respuesta.

- **Fallo** (`401 Unauthorized`):

  ```json
  {
    "error": "Credenciales inválidas"
  }
  ```

### Crear Animal

  ```bash
  POST http://localhost:8080/animal/crear
  ```

- **Cuerpo de la Solicitud:**

  ```json
    {
        "especie": "Mamifero",
        "habitat": "SALVAJE",
        "nombre": "Animal Test",
        "tipoDeComida": "CARNIVORO"
    }
  ```

- **Tipos de habitat y tipo de comida**
  ```json
    {
        "habitat": "SALVAJE/DOMESTICO",
        "tipoDeComida": "CARNIVORO/HERBIVORO/OMNIVORO"
    }
  ```
## Respuesta de la API

- **Éxito** (`201 Created`):

  ```json
  {
    "mensaje": "Animal registrado exitosamente",
  }
  ```


### Asignar Cuidador a Animal

- **URL:** `/cuidador/asignarCuidador/{idAnimal}`
- **Método HTTP:** `POST`
- **Descripción:** Asigna uno o más cuidadores a un animal específico.
- **Parámetros de Ruta:**
    - `idAnimal`: ID del animal al que se asignarán los cuidadores.
- **Cuerpo de la Solicitud:** JSON con la lista de IDs de los cuidadores.

### Asignar Animales a Cuidador

- **URL:** `/cuidador/asignarAnimales/{idCuidador}`
- **Método HTTP:** `POST`
- **Descripción:** Asigna uno o más animales a un cuidador específico.
- **Parámetros de Ruta:**
    - `idCuidador`: ID del cuidador al que se asignarán los animales.
- **Cuerpo de la Solicitud:** JSON con la lista de IDs de los animales.

## Ejemplos de Solicitudes

### Asignar Cuidador a Animal

- **Endpoint:**

  ```
  POST http://localhost:8080/animale/asignarCuidador/1
  ```

- **Cuerpo de la Solicitud:**

  ```json
  {
    "cuidadores": [1, 2, 3]
  }
  ```

### Asignar Animales a Cuidador

- **Endpoint:**

  ```
  POST http://localhost:8080/cuidador/asignarAnimales/1
  ```

- **Cuerpo de la Solicitud:**

  ```json
  {
    "animales": [1, 2, 3]
  }
  ```

## Respuesta de la API

### Asignar Cuidador a Animal

- **Éxito** (Todos los cuidadores asignados):

  ```json
  {
    "mensaje": "Cuidadores asignados exitosamente"
  }
  ```

- **Éxito** (Algunos cuidadores ya estaban asignados):

  ```json
  {
    "mensaje": "Algunos cuidadores ya estaban asignados al animal",
    "detalles": [
      {
        "id": 1,
        "nombreUsuario": "cuidador1",
        "nombreCompleto": "Cuidador Uno",
        "email": "cuidador1@example.com"
      }
    ]
  }
  ```

### Asignar Animales a Cuidador

- **Éxito** (Todos los animales asignados):

  ```json
  {
    "mensaje": "Todos los animales fueron asignados exitosamente"
  }
  ```

- **Éxito** (Algunos animales ya estaban asignados):

  ```json
  {
    "mensaje": "Algunos animales ya estaban asignados al cuidador",
    "detalles": [
      {
        "id": 1,
        "nombre": "Firulais",
        "especie": "Perro",
        "raza": "Labrador"
      }
    ]
  }
  ```

### Obtener cuidadores por animal

 ```
  GET http://localhost:8080/cuidador/obtenerAnimalesACargo/{id}
  ```
- **Parámetros de Ruta:**
- `id`: ID del cuidador.

### Respuesta de la API
  ```json
  {
    "mensaje": "Animales obtenidos exitosamente",
    "detalles": [
      {
        "especie": "Mamifero",
        "habitat": "SALVAJE",
        "nombre": "Test",
        "tipoDeComida": "CARNIVORO"
      },
      {
        "especie": "Mamifero",
        "habitat": "SALVAJE",
        "nombre": "Test1",
        "tipoDeComida": "CARNIVORO"
      }
    ]
  }
  ```


### Obtener animales por cuidador

 ```
  GET http://localhost:8080/animal/obtenerCuidadorACargo/{id}
  ```
- **Parámetros de Ruta:**
- `id`: ID del animal.

### Respuesta de la API
  ```json
  {
    "mensaje": "Cuidadores obtenidos exitosamente",
    "detalles": [
      {
        "nombreUsuario": "Test",
        "email": "test@test.com",
        "rol": "CUIDADOR"
      }
    ]
  }
 ```





## Consideraciones de Seguridad

- **Encriptación de Contraseñas:** Las contraseñas se almacenan de forma segura con bycrypt.
¡Gracias por utilizar el Refugio Animal API! 🐾
