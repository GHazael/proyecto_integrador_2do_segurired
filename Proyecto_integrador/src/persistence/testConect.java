package persistence;

import java.util.List;

public class testConect {
    public static void main(String[] args) {
        Conection con = new Conection();
        con.connect();
        con.disconnect();


        Users newUser = new Users();
        newUser.connect();

        List<Users> userlist = newUser.getAllUsers("SELECT * FROM users");
        for (Users user : userlist) {
            System.out.println(user.getName());
        }
//        //int x = newUser.fetchUserIdByUsername("admin");
//        //System.out.println(x);
//        //newUser.updateField("ROLE","GUARD",3);
//        newUser.setUsername("fernandog");
//        newUser.setName("fernando");
//        newUser.setPassword("fer");
//        newUser.setActive(true);
//        newUser.setRole("client");
//        newUser.setF_surename("licenciado");
//        newUser.setM_surename("gallardo");
//        newUser.save();


        newUser.query("SELECT * FROM USERS;");

        newUser.disconnect();

    }
}
