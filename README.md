# VeterinariaApp (Android + SQLite/Room)

App sencilla para Android Studio que gestiona *mascotas de una veterinaria* con CRUD completo:

- *Listar* mascotas (RecyclerView)
- *Registrar* nueva mascota
- *Editar* mascota existente
- *Eliminar* (desde la pantalla de edición)
- Persistencia con *SQLite* usando *Room*

## Requisitos

- Android Studio Giraffe/Koala o superior  
- JDK 17+  
- Gradle 8.x (incluido por wrapper)  
- Mínimo Android 7.0 (API 24)  

## Estructura
## Cómo ejecutar

1. *Abrir* el proyecto en Android Studio (File > Open > VeterinariaApp).  
2. Esperar la sincronización de Gradle.  
3. Ejecutar en un *emulador* o dispositivo físico (API 24+).  

## Notas

- La base de datos se llama veterinaria.db y se crea automáticamente.  
- Para probar rápidamente, registra un par de mascotas con el botón *"Registrar Mascota"*.  
- El icono y estilos son básicos; puedes personalizarlos en res/.  

## Licencia

Uso libre para fines educativos.
