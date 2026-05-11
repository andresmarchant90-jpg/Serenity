# Proyecto de Automatización E2E - Serenity BDD

Este proyecto realiza la automatización del flujo de compra y validación de inicio de sesión en la página [SauceDemo](https://saucedemo.com) utilizando el patrón de diseño **Screenplay**.

## 🛠️ Tecnologías y Versiones
*   **Lenguaje:** Java 1.8 (JDK 8)
*   **Framework de Automatización:** Serenity BDD 2.6.0
*   **Framework de Pruebas:** Cucumber 6.x
*   **Gestor de Dependencias:** Maven 3.9.1
*   **IDE:** IntelliJ IDEA
*   **Patrón de Diseño:** Screenplay

## 📁 Estructura del Proyecto
El proyecto sigue la estructura estándar de Maven y Screenplay:

*   `src/test/resources/features`: Archivos Gherkin (.feature).
*   `src/test/resources/data`: Archivos de datos externos (.csv).
*   `src/test/java/com/test/runners`: Motores de ejecución (RunnerIT).
*   `src/test/java/com/test/stepdefinitions`: Conexión entre Gherkin y código Java.
*   `src/test/java/com/test/tasks`: Acciones de alto nivel del actor (Login, Agregar al carrito, etc.).
*   `src/test/java/com/test/userinterfaces`: Mapeo de elementos web (Targets).

## 🚀 Configuración Inicial
* JDK 1.8: Asegurarse de tener configurada la variable de entorno JAVA_HOME apuntando a la versión 1.8.
* Gestión de Drivers: El proyecto utiliza la librería WebDriverManager (configurada en el pom.xml). No es necesario descargar ni configurar manualmente el chromedriver.exe; la librería detectará la versión de Chrome instalada (147+) y descargará el binario compatible automáticamente.
* Modo Incógnito: Configurado mediante chrome.switches en el archivo serenity.conf para garantizar una sesión limpia y evitar interrupciones del administrador de contraseñas de Google.

## 🏃 Ejecución de Pruebas
Para ejecutar todos los escenarios y generar el reporte, utiliza el siguiente comando en la terminal:

```bash
mvn clean verify
```

Para ejecutar un flujo específico mediante etiquetas (tags):
```bash
mvn clean verify -Dcucumber.filter.tags="@Compra"
```

## 📊 Reportes
Al finalizar la ejecución, Serenity genera un reporte detallado en HTML. Puedes consultarlo en:
`target/site/serenity/index.html`

## 💡 Características Implementadas
- **Data-Driven Testing:** Los usuarios de prueba se cargan dinámicamente desde un archivo CSV.
- **Esperas Explícitas:** Uso de `WaitUntil` para estabilizar la navegación entre pantallas.
- **Validaciones Condicionales:** Lógica para validar mensajes de éxito o error en un mismo paso de prueba.