package interface_layer;

import persistence.Guards;
import services.chek_in;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class guard_interface {
    public static void main_guard(int id){
        Scanner sc = new Scanner(System.in);
        boolean x = true;

        while (x){
            System.out.println("Escriba 1 para: Hacer check-in");
            System.out.println("Escriba 2 para: Ver total de horas laboradas");

            String ans = sc.nextLine();
            if (ans.equals("1")) {
                System.out.println("Hacer check-in");
                Guards guard = new Guards();
                LocalDateTime exit;
                chek_in ch = new chek_in();
                boolean valid = ch.cheks(3);

                if (valid) {
                    LocalDateTime entrance = LocalDateTime.now();

                    while (true) {

                        System.out.println("Cuando este listo presione 1 para registrar su hora de salida: ");
                        Scanner scanner = new Scanner(System.in);
                        String confirmation = scanner.nextLine();
                        if (confirmation.equals("1")) {
                            exit = LocalDateTime.now();
                            System.out.println("Se ha registrado la hora de salida: " + exit);
                            break;

                        } else {
                            System.out.println("opcion no reonocido postergando registro de salida");
                        }
                    }

                    System.out.println("Usted trabajo por");

                    //obtener la duracion del lapso de trabajo
                    Duration duration = Duration.between(entrance, exit);
                    //convertir a horas
                    double hours = duration.toSeconds() / 3600.0;
                    System.out.println("Horas laborads: " + hours);

                guard.connect();
                List<Guards> allGuards = guard.getAllGuards("SELECT * FROM guards WHERE USER_ID = '" + id + "'");
                Guards guard_from_db = allGuards.get(0);
                float db_hours = guard_from_db.getHorasLaboradas();
                guard.updateField("horas_laboradas", hours + db_hours, id);
                guard.disconnect();

            }
                else {System.out.println("Debes estar en el pesto de trabajo para registrar tu entrada");}
                x = false;
            } else if (ans.equals("2")) {
                System.out.println("Ver total de horas laboradas");
                Guards guard = new Guards();
                guard.connect();
                List<Guards> allGuards = guard.getAllGuards("SELECT * FROM guards WHERE USER_ID = '" + id + "'");
                Guards guard_from_db = allGuards.get(0);
                float db_hours = guard_from_db.getHorasLaboradas();
                System.out.println("Horas laboradas: " + db_hours);

            } else {
                System.out.println("Seleccione una opcion valida");
            }









        }




    }


}