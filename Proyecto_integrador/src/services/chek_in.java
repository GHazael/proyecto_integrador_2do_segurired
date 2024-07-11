package services;

import org.json.JSONObject;
import persistence.Conection;
import persistence.Guards;

public class chek_in {
    public static boolean cheks(int pdi_id) {

        float hours = 0;
        get_location location = new get_location();
        JSONObject geodata = location.location();
        String lat = geodata.getString("latitude");
        String lon = geodata.getString("longitude");
        boolean autenticate = get_location.autenticate(lat, lon, pdi_id);





        return autenticate;


    }

        public void save_time(){
        double x = 0;
        Guards guards = new Guards();
        guards.connect();
        guards.disconnect();

    }



    public static void main(String[] args) {
        System.out.println(cheks(1));
        System.out.println("Hola :3");

    }
}