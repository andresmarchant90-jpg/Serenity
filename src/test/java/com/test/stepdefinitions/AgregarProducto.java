package com.test.tasks;

import com.test.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AgregarProducto implements Task {

    public AgregarProducto() {}

    @Override
    @Step("{0} agrega la mochila al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LoginPage.BOTON_ADD_TO_CART),
                Click.on(LoginPage.ICONO_CARRITO),
                WaitUntil.the(LoginPage.BOTON_CHECKOUT, isClickable()).forNoMoreThan(5).seconds(),
                Click.on(LoginPage.BOTON_CHECKOUT)
        );
    }

    public static AgregarProducto alCarrito() {
        return instrumented(AgregarProducto.class);
    }
}