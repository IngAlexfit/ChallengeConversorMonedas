# Challenge Conversor de Monedas

Este proyecto es un conversor de monedas que utiliza la API de ExchangeRate para obtener tasas de cambio y realizar conversiones entre diferentes monedas.


## Configuración del Proyecto

1. Clona el repositorio:
    ```sh
    git clone https://github.com/IngAlexfit/ChallengeConversorMonedas.git
    cd currency-converter
    ```

2. Configura tu clave de API en el archivo `src/main/resources/config.properties`:
    ```java
    EXCHANGE_RATE_API_KEY=

    ```



## Ejecución del Proyecto

Para ejecutar el proyecto en IntelliJ IDEA, sigue estos pasos:

1. Abre IntelliJ IDEA y carga tu proyecto.
2. Ve a `Run` > `Edit Configurations...`.
3. Haz clic en el botón `+` y selecciona `Application`.
4. Configura los siguientes campos:
   - **Name**: Ponle un nombre a tu configuración, por ejemplo, `Run Main`.
   - **Main class**: Especifica la clase principal, en tu caso `org.project.main.Main`.
   - **Use classpath of module**: Selecciona el módulo correspondiente, por ejemplo, `ChallengeConversorMonedas`.
5. Haz clic en `Apply` y luego en `OK`.

Ahora puedes usar el botón de ejecución (el ícono de play verde) para ejecutar tu proyecto.

## Uso del Conversor de Monedas

Para usar el conversor de monedas, sigue estos pasos:

1. Ejecuta el proyecto usando el botón de ejecución en IntelliJ IDEA.
2. Una vez que el programa esté en ejecución, verás un menú de opciones en la consola.

### Menú de Opciones

1. **Listar Códigos de Monedas Disponibles**: Selecciona esta opción para ver una lista de todos los códigos de monedas soportados.
2. **Convertir Moneda**: Selecciona esta opción para convertir una cantidad de una moneda a otra.
   - Ingresa el código de la moneda de origen (por ejemplo, USD).
   - Ingresa el código de la moneda de destino (por ejemplo, EUR).
   - Ingresa la cantidad que deseas convertir.
3. **Ver Historial de Conversiones**: Selecciona esta opción para ver el historial de conversiones realizadas.
4. **Salir**: Selecciona esta opción para salir del programa.
5. **USD a COP**: Selecciona esta opción para una conversión rápida de USD a COP.
6. **COP a USD**: Selecciona esta opción para una conversión rápida de COP a USD.
7. **USD a ARS**: Selecciona esta opción para una conversión rápida de USD a ARS.
8. **ARS a USD**: Selecciona esta opción para una conversión rápida de ARS a USD.
9. **EUR a USD**: Selecciona esta opción para una conversión rápida de EUR a USD.
10. **USD a EUR**: Selecciona esta opción para una conversión rápida de USD a EUR.

### Ejemplo de Uso

```plaintext
*************** Conversor de Monedas ***************
1. Listar Codigos de Monedas Disponibles
2. Convertir Moneda
3. Ver Historial de Conversiones
4. Salir
*************** Conversiones Rápidas ***************
5. USD a COP
6. COP a USD
7. USD a ARS
8. ARS a USD
9. EUR a USD
10. USD a EUR
Seleccione una opción: 2
Ingrese El codigo moneda de origen: USD
Ingrese Codigo de la moneda de destino: EUR
Ingrese la cantidad a convertir: 100

*************** Resultado de la Conversión ***************
Convertido: 92.53 EUR
*******************************************************
