# 🎬 API de Películas

## Descripción

He realizado esta aplicación con el propósito principal de **aprender y poner en práctica mis conocimientos de Spring**. Esto no significa que la aplicación no sea plenamente funcional.

La aplicación interactúa con la base de datos **sample_mflix** de MongoDB, una base de datos de muestra que MongoDB ofrece de forma libre para todo tipo de uso.
Esta base de datos contiene las colecciones: `movies`, `comments`, `theaters`, `users` y `sessions`.

> ⚠️ La base de datos no está incluida en este repositorio. Para un uso completo de la aplicación, es necesario introducir la URI correspondiente a **sample_mflix** en el archivo `application.properties`.

El propósito de esta aplicación no ha sido profundizar en la seguridad, por eso he optado por implementar una seguridad básica. La aplicación cuenta con una seguridad de tipo HTTPBasic con el usuario **admin** y la contraseña **1234** en memoria, el cual da acceso a todos los endpoints.

## Documentación de Endpoints

## 📁 `/movies`

### `GET /movies`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** La página especificada de todas las películas existentes en la colección.

### `GET /movies/{id}`
- **Devuelve:** La película que tiene el campo `id` con el valor introducido.

### `GET /movies/title`
- **Parámetros:** `String title`
- **Devuelve:** La película que tiene el título introducido.

### `GET /movies/genre/{genre}`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** La página especificada de todas las películas que pertenecen al género especificado.

### `GET /movies/actor/{actor}`
- **Devuelve:** Lista de todas las películas donde aparece el actor especificado.

### `GET /movies/director/{director}`
- **Devuelve:** Lista de todas las películas dirigidas o co-dirigidas por el director especificado.

### `GET /movies/genre/todos`
- **Devuelve:** Lista de todos los géneros diferentes disponibles en la colección.

### `GET /movies/actor/todos`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** Página especificada de la lista de actores distintos en la colección.

### `GET /movies/director/todos`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** Página especificada de la lista de directores distintos en la colección.

## 💬 `/comments`

### `GET /comments`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** Página especificada de todos los comentarios existentes.

### `GET /comments/{id}`
- **Devuelve:** El comentario correspondiente al `id` especificado.

### `PUT /comments/{id}`
- **Cuerpo de la solicitud:** Contenido del comentario actualizado
- **Devuelve:** El comentario actualizado con su `id`.

### `DELETE /comments/{id}`
- **Acción:** Elimina el comentario con el `id` introducido.

### `GET /comments/movie/{movieId}`
- **Devuelve:** Lista de todos los comentarios asociados a la película con `id` especificado.

### `GET /comments/user/{email}`
- **Devuelve:** Lista de todos los comentarios hechos por el usuario con el `email` especificado.

## 👤 `/users`

### `GET /users`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** Página especificada de todos los usuarios existentes.

### `GET /users/{id}`
- **Devuelve:** Usuario correspondiente al `id` especificado.

### `POST /users`
- **Propósito:** Guardar un nuevo usuario
- **Cuerpo de la solicitud:** Datos del usuario con `id`, `name`, `email`, `password`
- **Devuelve:** El usuario guardado correctamente.

### `PUT /users/{id}`
- **Propósito:** Actualizar los detalles de un usuario existente
- **Cuerpo de la solicitud:** Datos actualizados (`id`, `name`, `email`, `password`)
- **Devuelve:** El usuario actualizado correctamente.

### `DELETE /users/{id}`
- **Propósito:** Eliminar el usuario con el `id` especificado
- **Devuelve:** Código `200 OK` si se eliminó correctamente.

### `GET /users/exists/{email}`
- **Propósito:** Verificar si existe un usuario con el `email` introducido
- **Devuelve:** El usuario si existe.

### `GET /users/login`
- **Propósito:** Iniciar sesión y obtener el token JWT
- **Parámetros:** `String email`, `String password`
- **Devuelve:** Token JWT si el login es válido.

## 🎭 `/theaters`

### `GET /theaters`
- **Parámetros:** `int numPagina`, `int tamañoPagina`
- **Devuelve:** Página especificada de todos los cines disponibles.

### `GET /theaters/{id}`
- **Propósito:** Obtener el cine con el `id` especificado.

### `PUT /theaters/{id}`
- **Propósito:** Actualizar la ubicación de un cine
- **Cuerpo de la solicitud:** Cine completo actualizado.

### `DELETE /theaters/{id}`
- **Propósito:** Eliminar el cine especificado
- **Devuelve:** Código `200 OK` si se eliminó correctamente.

### `GET /theaters/closest`
- **Propósito:** Obtener las coordenadas del cine más cercano a una ubicación
- **Cuerpo de la solicitud:** Coordenadas de ubicación
- **Devuelve:** Coordenadas del cine más cercano.

## ⏱️ `/sessions`

### `GET /sessions`
- **Devuelve:** Lista de todas las sesiones activas, incluyendo `id`, `user_id` y token JWT.

## 📝 Comentarios por película

### `POST /movies/{id}/comments`
- **Propósito:** Añadir un nuevo comentario a una película existente
- **Cuerpo de la solicitud:** Comentario con `text`, `name`, `email`
- **Devuelve:** Comentario guardado correctamente.




