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

  @CompraMultiple
  Esquema del escenario: Compra completa E2E de múltiples productos variables
    Dado que el usuario se encuentra en la página de inicio
    Cuando el usuario ingresa el usuario "standard_user" y la clave "secret_sauce"
    Y el usuario agrega los siguientes productos al carrito:

      | producto   |
      | <item_1>   |
      | <item_2>   |
      | <item_3>   |
    Y el usuario procede al checkout
    Y el usuario ingresa su nombre "<nombre>", apellido "<apellido>" y código postal "<zip>"
    Entonces el usuario debería ver el mensaje final "Thank you for your order!"

    Ejemplos:

      | item_1                  | item_2                     | item_3                  | nombre | apellido | zip   |
      | Sauce Labs Backpack     | Sauce Labs Bike Light      | Sauce Labs Bolt T-Shirt | Juan   | Perez    | 12345 |
      | Sauce Labs Onesie       | Sauce Labs Fleece Jacket   | Sauce Labs Bike Light   | Maria  | Gomez    | 54321 |
