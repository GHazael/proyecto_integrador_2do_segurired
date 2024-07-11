package services;

import java.io.BufferedReader; //para leer la respuesta  de la api
import java.io.BufferedReader;
import java.io.InputStreamReader; // convierte los bytes en caracteres
import java.net.HttpURLConnection; //permite establecer la conexion con la http con la api
import java.net.URL; //sirve para crear la url
import java.net.URLConnection;
import org.json.JSONObject; //manipular y analizar los datos json, la api responde en json
import persistence.PDI;

public class get_location {

    public static JSONObject location() {
        try {
            //clave de API  de geolocation aquí
            String apiKey = "cf66a7f4da434654bdab3c92075c90db";
            String url = "https://api.ipgeolocation.io/ipgeo?apiKey=" + apiKey;

            // Crear el objeto URL
            URL urlObject = new URL(url);

            // Abrir la conexión
            URLConnection urlConnection = urlObject.openConnection();

            // Hacer el casting a HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) urlConnection;

            // Establecer el método de solicitud
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            // El BufferedReader nos proporciona métodos para leer la entrada de datos de manera eficiente.
            // InputStreamReader convierte los bits del InputStream a caracteres, haciendo que los datos sean legibles como texto.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Se genera una variable String vacía que almacenará cada línea de la respuesta de la API mientras se lee.
            String inputLine;

            // Se crea un StringBuilder, que es un objeto que se utiliza para construir una cadena de texto de manera eficiente.
            // Es preferible usar StringBuilder en lugar de String cuando se realizan múltiples concatenaciones, ya que es más eficiente en términos de rendimiento.
            StringBuilder response = new StringBuilder();

            // Bucle que lee cada línea de la respuesta de la API hasta que no haya más líneas por leer.
            // Cada línea leída se añade al StringBuilder.
            while ((inputLine = in.readLine()) != null) {
                // Lee una línea de texto desde el BufferedReader y la añade al StringBuilder.
                response.append(inputLine);
            }

            // Cerramos el BufferedReader una vez que hemos terminado de leer la respuesta.
            // Es una buena práctica cerrar los flujos de entrada/salida una vez que ya no son necesarios para evitar fugas de recursos.
            in.close();


            // Convertir la respuesta a un objeto JSON
            //se crea el objeto json y se asigna el valor de response convertio en string.
            JSONObject json = new JSONObject(response.toString());

            return json;
//            String ip = json.getString("ip");
//            String city = json.getString("city");
//            String stateProv = json.getString("state_prov");
//            String country = json.getString("country_name");
//            String lat = json.getString("latitude");
//            String lon = json.getString("longitude");
//
//            // Imprimir la información obtenida
//            System.out.println("IP: " + ip);
//            System.out.println("Ciudad: " + city);
//            System.out.println("Estado/Provincia: " + stateProv);
//            System.out.println("País: " + country);
//            System.out.println("Latitud: " + lat);
//            System.out.println("Longitud: " + lon);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean autenticate(String lat, String lon, int pdi_id) {

        PDI pdi = new PDI();
        pdi.connect();
        PDI x = pdi.fetchPdiById(pdi_id);
        pdi.disconnect();

        double lat_pdi = x.getLatitud(); // UTJ latitude
        double lon_pdi = x.getLongitud();

        // UTJ longitude
        double tolerance = 0.150; // tolerancia aceptable

        // Convertir strings a doubles
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lon);

        // REvisar si las cordendas estan dentro del rango de tolerancia
        if (Math.abs(latitude - lat_pdi) < tolerance && Math.abs(longitude - lon_pdi) < tolerance) {
            return true;
        } else {
            return false;
        }
    }
}
