package flow;

import interface_layer.admin_interface;
import interface_layer.guard_interface;
import persistence.Users;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class access_control {
    public static void main(String[] args) {
        String role = "";
        int id = 0;
        Users checkUser = new Users();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name");
        String userName = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();

        //Validar usuario y obtener rol.
        try {
            checkUser.connect();
            List<Users> auth = checkUser.getAllUsers("SELECT * FROM users WHERE username='" + userName + "' AND password='" + password + "'");
            checkUser.disconnect();
            Users x = auth.get(0);
            role = x.getRole();
            id = x.getId();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("User or password incorrect");
        }

        //control por rol
        if (Objects.equals(role, "guard")){

            System.out.println("You are guard");
            guard_interface inter = new guard_interface();
            inter.main_guard(id);
        }
        else if (Objects.equals(role,"admin")){
            // admin stuff
            System.out.println("You are admin");
            admin_interface inter = new admin_interface();
            inter.main_admin(id);
        }
        else if (Objects.equals(role, "client")){
            //student stuff
            System.out.println("You are client");
        }
        else {
            System.out.println("Invalid role");
        }




    }
}
