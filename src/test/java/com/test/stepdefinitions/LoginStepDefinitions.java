package com.test.stepdefinitions;

import com.test.tasks.AbrirNavegador;
import com.test.tasks.AgregarProducto;
import com.test.tasks.Autenticarse;
import com.test.tasks.DiligenciarFormulario;
import com.test.userinterfaces.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;

import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class LoginStepDefinitions {

    @Before
    public void prepararEscenario() {
        // Prepara el escenario para que existan actores
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el usuario se encuentra en la página de inicio")
    public void queElUsuarioSeEncuentraEnLaPaginaDeInicio() {
        // Creamos un actor llamado "Usuario" y le pedimos que abra el navegador
        theActorCalled("Usuario").wasAbleTo(AbrirNavegador.enLaPaginaDeInicio());
    }

    @Cuando("el usuario ingresa el usuario {string} y la clave {string}")
    public void elUsuarioIngresaElUsuarioYLaClave(String usuario, String clave) {
        // El actor que ya está en escena intenta autenticarse
        theActorInTheSpotlight().attemptsTo(Autenticarse.conCredenciales(usuario, clave));
    }

    @Entonces("el usuario debería ver el mensaje de bienvenida {string}")
    public void elUsuarioDeberiaVerElMensaje(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El mensaje visible en pantalla",
                        actor -> {
                            // Si el mensaje de error es visible, devolvemos su texto
                            if (LoginPage.MENSAJE_ERROR.resolveFor(actor).isVisible()) {
                                return LoginPage.MENSAJE_ERROR.resolveFor(actor).getText();
                            }
                            // Si no, devolvemos el texto del título principal
                            return LoginPage.TITULO_BIENVENIDA.resolveFor(actor).getText();
                        },
                        Matchers.containsString(mensajeEsperado)
                )
        );
    }

    @Cuando("el usuario agrega un producto al carrito y procede al checkout")
    public void elUsuarioAgregaUnProductoAlCarritoYProcedeAlCheckout() {
        theActorInTheSpotlight().attemptsTo(AgregarProducto.alCarrito());
    }

    @Entonces("el usuario debería ver el formulario de información de envío")
    public void verFormularioEnvio() {
        // Aquí podrías validar que existe el campo "First Name"
        theActorInTheSpotlight().should(
                seeThat(actor -> Text.of(Target.the("Título Checkout").located(By.className("title"))).viewedBy(actor).asString(),
                        equalToIgnoringCase("Checkout: Your Information"))
        );
    }

    @Y("el usuario ingresa su nombre {string}, apellido {string} y código postal {string}")
    public void ingresarDatosEnvio(String nombre, String apellido, String zip) {
        theActorInTheSpotlight().attemptsTo(DiligenciarFormulario.conDatos(nombre, apellido, zip));
    }

    @Entonces("el usuario debería ver el mensaje final {string}")
    public void verificarCompra(String mensaje) {
        theActorInTheSpotlight().should(
                seeThat(actor -> Text.of(LoginPage.MENSAJE_EXITO).viewedBy(actor).asString(),
                        equalToIgnoringCase(mensaje))
        );
    }

    @Y("el usuario agrega los siguientes productos al carrito:")
    public void agregarMultiplesProductosAlCarrito(io.cucumber.datatable.DataTable dataTable) {
        // Convertimos la tabla a una estructura de lista de listas
        List<List<String>> filas = dataTable.asLists(String.class);

        // Recorremos desde el índice 1 para omitir la cabecera "producto"
        for (int i = 1; i < filas.size(); i++) {
            // El .trim() elimina espacios en blanco que puedan romper el XPath
            String nombreProducto = filas.get(i).get(0).trim();

            // El actor agrega cada producto de la tabla de forma individual
            theActorInTheSpotlight().attemptsTo(
                    AgregarProducto.conNombre(nombreProducto)
            );
        }
    }

    @Y("el usuario procede al checkout")
    public void procederAlCheckout() {
        // El actor ejecuta la acción exclusiva de ir al checkout
        theActorInTheSpotlight().attemptsTo(
                AgregarProducto.procederAlCheckout()
        );
    }
}
