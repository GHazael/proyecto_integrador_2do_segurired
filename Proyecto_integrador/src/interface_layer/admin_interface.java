package interface_layer;

import persistence.PDI;
import persistence.Users;

import java.util.Objects;
import java.util.Scanner;

public class admin_interface {
    public static void main_admin(int id){
        // generar menu de opciones

        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione la opcion que desea realizar");
        System.out.println("1. Registrar usuario");

        System.out.println("4. Registrar PDI");
        System.out.println("5. Salir");

        String op = sc.nextLine();

        if(Objects.equals(op, "1")){
            // registrar user
            Users u = new Users();
            u.registrar();
        } else if (Objects.equals(op, "4")){
            //Registrar pdi
            PDI p = new PDI();
            p.registrar();
        }

    }
}
