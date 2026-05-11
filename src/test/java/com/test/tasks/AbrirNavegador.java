package com.test.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import io.github.bonigarcia.wdm.WebDriverManager;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirNavegador implements Task {


    @Override
    @Step("{0} abre la página principal")
    public <T extends Actor> void performAs(T actor) {
        // Esta línea descarga (si hace falta) y configura el driver correcto automáticamente
        WebDriverManager.chromedriver().setup();

        actor.attemptsTo(
                Open.url("https://saucedemo.com")
        );
    }




    public static AbrirNavegador enLaPaginaDeInicio() {
        return instrumented(AbrirNavegador.class);
    }
}