package com.test.tasks;

import com.test.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AgregarProducto implements Task {

    // Inicializamos las variables por defecto de forma segura
    private String nombreProducto = null;
    private boolean esSoloCheckout = false;

    // Constructor por defecto OBLIGATORIAMENTE PUBLICO
    public AgregarProducto() {
        this.nombreProducto = null;
        this.esSoloCheckout = false;
    }

    // Constructor parametrizado OBLIGATORIAMENTE PUBLICO para instrumentación de Serenity
    public AgregarProducto(String nombreProducto, Boolean esSoloCheckout) {
        this.nombreProducto = nombreProducto;
        this.esSoloCheckout = esSoloCheckout != null ? esSoloCheckout : false;
    }

    @Override
    @Step("{0} interactúa con el carrito de compras")
    public <T extends Actor> void performAs(T actor) {
        if (esSoloCheckout) {
            actor.attemptsTo(
                    Click.on(LoginPage.ICONO_CARRITO),
                    WaitUntil.the(LoginPage.BOTON_CHECKOUT, isClickable()).forNoMoreThan(5).seconds(),
                    Click.on(LoginPage.BOTON_CHECKOUT)

            );

        } else if (nombreProducto != null && !nombreProducto.isEmpty()) {
            // Modificado text()='%s' por contains(text(),'%s') para tolerar coincidencias parciales
            String xpathBotonDinamico = String.format("//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button", nombreProducto);
            Target botonProducto = Target.the("Botón de compra para: " + nombreProducto).located(By.xpath(xpathBotonDinamico));

            actor.attemptsTo(
                    Click.on(botonProducto)
            );
        } else {
            actor.attemptsTo(
                    Click.on(LoginPage.BOTON_ADD_TO_CART),
                    Click.on(LoginPage.ICONO_CARRITO),
                    WaitUntil.the(LoginPage.BOTON_CHECKOUT, isClickable()).forNoMoreThan(5).seconds(),
                    Click.on(LoginPage.BOTON_CHECKOUT)
            );
        }
    }

    public static AgregarProducto alCarrito() {
        return instrumented(AgregarProducto.class);
    }

    public static AgregarProducto conNombre(String nombreProducto) {
        return instrumented(AgregarProducto.class, nombreProducto, false);
    }

    public static AgregarProducto procederAlCheckout() {
        return instrumented(AgregarProducto.class, "", true);
    }
}
