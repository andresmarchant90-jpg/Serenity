package com.test.tasks;

import com.test.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Autenticarse implements Task {
    private final String usuario;
    private final String clave;

    public Autenticarse(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    @Step("{0} se autentica en la aplicación con el usuario #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(usuario).into(LoginPage.INPUT_USUARIO),
                Enter.theValue(clave).into(LoginPage.INPUT_PASSWORD),
                Click.on(LoginPage.BOTON_LOGIN)
        );
    }

    public static Autenticarse conCredenciales(String usuario, String clave) {
        return instrumented(Autenticarse.class, usuario, clave);
    }
}