package com.test.tasks;

import com.test.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DiligenciarFormulario implements Task {

    private final String nombre;
    private final String apellido;
    private final String zip;

    public DiligenciarFormulario(String nombre, String apellido, String zip) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.zip = zip;
    }

    @Override
    @Step("{0} ingresa los datos de envío: #nombre #apellido")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(nombre).into(LoginPage.INPUT_NOMBRE),
                Enter.theValue(apellido).into(LoginPage.INPUT_APELLIDO),
                Enter.theValue(zip).into(LoginPage.INPUT_CODIGO_POSTAL),
                Click.on(LoginPage.BOTON_CONTINUAR),
                Click.on(LoginPage.BOTON_FINISH)
        );
    }

    public static DiligenciarFormulario conDatos(String nombre, String apellido, String zip) {
        return instrumented(DiligenciarFormulario.class, nombre, apellido, zip);
    }
}