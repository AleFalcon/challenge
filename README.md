# Instrucciones para deployar la app

## Requisitos previos

1. **Verificar que se encuentra instalado gradle**
2. **Verificar que se encuentra instalado docker-compose**
   ```bash
   docker-compose --version
   ```
   
## Compilación del proyecto
* **Compilar el proyecto.** Ejecuta el siguiente comando para la compilación:
   ```bash
   ./gradlew clean build
  ```
  
## Levantar los servicios
* **Levantar los servicios.** Debes estar en la raíz del proyecto (donde se encuentra el docker-compose.yml) y ejecutar:
   ```bash
  docker-compose up --build
   ```
  * Opción para correr en segundo plano:
    ```bash
      docker-compose up --build -d
      ```

## Verificar los servicios
* **Verificar que todos los servicios y componentes se encuentran levantados:**
  ```bash
      docker ps
  ```

## Escalado del proyecto
* **Para escalar el proyecto**, puedes ejecutar el siguiente comando, siendo **'N'** la cantidad de instancias:
     ```bash
         docker-compose up --scale app=N -d
     ```

> **NOTA:** Esta aplicación está utilizando un wiremockServer. En caso de querer apuntar la aplicación a un servicio real, y no un mock, basta con cambiar la variable `msPercentage.url` del `application.properties` para apuntarlo a otro servicio. Si se desea cambiar esta URL, es necesario volver a buildear el proyecto y deployarlo nuevamente.

---
# Uso de la aplicación
## Uso con Postman
* **Importa la colección de Postman** que se encuentra en **'postman/collection/'**, con el nombre **'Challenge.postman_collection.json'**.
Para hacerlo, ve a **'File -> Import -> Seleccionar el archivo mencionado'**.

* **Importa el entorno de Postman** que apunta a **'localhost:8080'**. Si necesitas usar otra URL, puedes crear la variable **'path'** en Postman.
Una vez importadas las colecciones y configurado el entorno, podrás ejecutar las peticiones HTTP y recibir respuestas de la aplicación.