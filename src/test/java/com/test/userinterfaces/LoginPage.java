package com.test.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    // En tu LoginPage.java agrega el localizador de error
    public static final Target MENSAJE_ERROR = Target.the("Mensaje de error")
            .located(By.cssSelector("[data-test='error']"));

    // Definimos el input de usuario
    public static final Target INPUT_USUARIO = Target.the("Campo para el nombre de usuario")
            .located(By.id("user-name"));

    // Definimos el input de contraseña
    public static final Target INPUT_PASSWORD = Target.the("Campo para la contraseña")
            .located(By.id("password"));

    // Definimos el botón de login
    public static final Target BOTON_LOGIN = Target.the("Botón para iniciar sesión")
            .located(By.id("login-button"));

    // Definimos un elemento para validar el éxito (ej. el título de la página interna)
    public static final Target TITULO_BIENVENIDA = Target.the("Título de la página principal")
            .located(By.className("title"));

    public static final Target BOTON_ADD_TO_CART = Target.the("Botón agregar al carrito de la mochila")
            .located(By.id("add-to-cart-sauce-labs-backpack"));

    public static final Target ICONO_CARRITO = Target.the("Icono del carrito de compras")
            .located(By.className("shopping_cart_link"));

    public static final Target BOTON_CHECKOUT = Target.the("Botón de Checkout")
            .located(By.id("checkout"));

    // Localizadores para el formulario de Checkout
    public static final Target INPUT_NOMBRE = Target.the("Campo nombre")
            .located(By.id("first-name"));

    public static final Target INPUT_APELLIDO = Target.the("Campo apellido")
            .located(By.id("last-name"));

    public static final Target INPUT_CODIGO_POSTAL = Target.the("Campo código postal")
            .located(By.id("postal-code"));

    public static final Target BOTON_CONTINUAR = Target.the("Botón continuar")
            .located(By.id("continue"));

    public static final Target BOTON_FINISH = Target.the("Botón finalizar compra")
            .located(By.id("finish"));

    public static final Target MENSAJE_EXITO = Target.the("Mensaje de orden completada")
            .located(By.className("complete-header"));
}