# Prueba técnica ANDROID Banco Cuscatlán

<img src="https://github.com/user-attachments/assets/96529a17-7b5e-4678-a304-7f0f73bda2eb" width="150" height="150" />

### Requisitos

- Android Studio Meerkat o superior
- JDK 21
- Git
- Emulador o dispositivo Android físico

### Paso 1: clonar repositorio o descargar

Ejecutar la terminal en una ruta deseada y escribir el siguiente comando:

`git clone https://github.com/ardon-dev/bc-pokedex-v2.git`

Se descargarán los archivos del repositorio.

También puedes descargar el código fuente en un archivo .zip y descomprimirlo en la ruta deseada.

### Paso 2: abrir proyecto en Android Studio

2.1 Ejecuta Android Studio.

2.2 En la sección de `Projects` selecciona la opción `Open`.

2.3 Busca la ruta donde clonaste el repositorio. Debe aparecer con un ícono de Android.

2.4 Click en OK.

2.5 Espera a que Android Studio termine compilar y descargar las dependencias.

### Paso 3: sincronizar y compilar

3.1 Para validar que todas las dependencias y configuración del Gradle es válida, ve al menú de Android Studio -> File -> Sync project with gradle files

3.2 Si el paso anterior se completó de forma exitosa, ve al menú de Android Studio -> Build -> Clean project

3.3 Si el paso anterior se completó de forma exitosa, ve al menú de Android Studio -> Build -> Rebuild project

3.4 Conecta tu dispositivo Android (Con la depuración USB habilitada) o inicia un emulador, toma en cuenta que la versión de android mínima es Android Nougat (7.0).

3.5 Busca en la barra de herramientas superior de Android Studio la opción Run o el ícono ▶️.

3.6 Espera a que finalize la compilación y la aplicación finalmente se ejecute en tu dispositivo u emulador.

#### Si tuviste algún problema durante el proceso verifica que estés utilizando la versión del JDK 17 para compilar la aplicación, puedes revisarlo en Menú -> Settings -> Build, Execution, Deployment -> Build tools -> Gradle



