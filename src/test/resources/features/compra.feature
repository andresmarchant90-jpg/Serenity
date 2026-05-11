# language: es
@Compra
Característica: Compra de productos en la tienda

Escenario: Comprar una mochila exitosamente
Dado que el usuario se encuentra en la página de inicio
Cuando el usuario ingresa el usuario "standard_user" y la clave "secret_sauce"
Y el usuario agrega un producto al carrito y procede al checkout
Entonces el usuario debería ver el formulario de información de envío

Escenario: Compra completa E2E
Dado que el usuario se encuentra en la página de inicio
Cuando el usuario ingresa el usuario "standard_user" y la clave "secret_sauce"
Y el usuario agrega un producto al carrito y procede al checkout
Y el usuario ingresa su nombre "Juan", apellido "Perez" y código postal "12345"
Entonces el usuario debería ver el mensaje final "Thank you for your order!"