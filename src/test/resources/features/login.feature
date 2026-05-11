# language: es
@Login
Característica: Inicio de sesión parametrizado

  Esquema del escenario: Inicio de sesión desde archivo externo
    Dado que el usuario se encuentra en la página de inicio
    Cuando el usuario ingresa el usuario "<usuario>" y la clave "<clave>"
    Entonces el usuario debería ver el mensaje de bienvenida "<mensaje>"

    # Esta es la magia de Serenity:
       # Esta es la magia de Serenity:
    Ejemplos:

      | usuario | clave | mensaje |
      | {serenity.data.file: src/test/resources/data/usuarios.csv} | | |

