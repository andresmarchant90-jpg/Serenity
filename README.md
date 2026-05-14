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

*   `src/test/resources/features`: Archivos Gherkin (.feature) que definen el comportamiento del negocio en español.
*   `src/test/resources/data`: Archivos de datos externos (.csv) para pruebas de inicio de sesión.
*   `src/test/java/com/test/runners`: Motores de ejecución JUnit de Serenity (RunnerIT).
*   `src/test/java/com/test/stepdefinitions`: Clases de enlace entre los pasos Gherkin y el código Java en Screenplay.
*   `src/test/java/com/test/tasks`: Acciones reutilizables y de alto nivel ejecutadas por el actor (Autenticarse, AgregarProducto, DiligenciarFormulario).
*   `src/test/java/com/test/userinterfaces`: Mapeo de elementos de la interfaz de usuario web mediante la clase `Target` de Serenity.

## 🚀 Configuración Inicial
*   **JDK 1.8:** Asegurarse de tener configurada la variable de entorno `JAVA_HOME` apuntando a la versión 1.8 en el sistema operativo.
*   **Gestión de Drivers:** El proyecto utiliza la librería `WebDriverManager` integrada en Serenity. No es necesario descargar ni configurar manualmente el archivo `chromedriver.exe`; la librería detectará la versión de Google Chrome instalada en la máquina y descargará el binario compatible de forma automática.
*   **Modo Incógnito:** Configurado mediante la propiedad `chrome.switches` en el archivo `serenity.conf` para garantizar una sesión de navegación limpia y evitar interrupciones del administrador de contraseñas del navegador.

## 🏃 Ejecución de Pruebas
Para ejecutar todos los escenarios y generar el reporte agregado de Serenity, utiliza el siguiente comando en la terminal:

```bash
mvn clean verify
```

Para ejecutar flujos específicos mediante etiquetas (*tags*) de Cucumber:
```bash
# Ejecuta todo el set de pruebas de compras
mvn clean verify -Dcucumber.filter.tags="@Compra"

# Ejecuta exclusivamente el esquema parametrizado de múltiples productos
mvn clean verify -Dcucumber.filter.tags="@CompraMultiple"
```

## 📊 Reportes
Al finalizar la ejecución con éxito, Serenity genera un reporte gráfico altamente detallado con capturas de pantalla por cada acción realizada. Puedes consultarlo abriendo el archivo local en tu navegador:
`target/site/serenity/index.html`

## 💡 Características Implementadas

*   **Esquema del Escenario (Data-Driven con Tablas):** Se incorporó un flujo End-to-End parametrizado mediante `Esquema del escenario` (*Scenario Outline*). Este permite realizar compras masivas iterando de forma consecutiva con diferentes usuarios, direcciones de envío y combinaciones de productos.
*   **Manejo de DataTables Dinámicas:** El framework procesa listas dinámicas de productos dentro de un mismo escenario sin duplicar líneas de código Gherkin. Utiliza bucles en Java y expresiones de Screenplay para iterar sobre tablas de datos complejas.
*   **Localizadores XPath Dinámicos:** La tarea `AgregarProducto` utiliza inyección de cadenas en expresiones XPath (`//div[contains(text(),'%s')]`). Esto permite interactuar con cualquier elemento del catálogo web en tiempo de ejecución basándose únicamente en el texto plano del producto enviado desde el archivo Feature.
*   **Constructores Instrumentados Seguros:** Clases de tareas configuradas estrictamente con constructores públicos sobrecargados. Esto cumple con los requisitos del motor de proxies e instrumentación de Serenity BDD (`Tasks.instrumented`), evitando excepciones de instanciación.
*   **Esperas Explícitas:** Uso del componente `WaitUntil` junto con condiciones web (`isClickable()`) para estabilizar la navegación entre las pantallas de carrito y pasarela de pago, controlando problemas de asincronía.
*   **Validaciones Condicionales:** El framework evalúa de forma inteligente las respuestas del sistema web, permitiendo validar tanto mensajes de éxito finales como errores de autenticación dinámicos en la pantalla de inicio de sesión.
    ógica para validar mensajes de éxito o error en un mismo paso de prueba.