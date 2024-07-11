package services;

public class authenticate {

    public String get_role(String username,String password){
        String role = "";
        if (username.equals("admin") && password.equals("admin")){
            role = "admin";
        }
        return role;
    }

    //use data from the login interface and compare
    // it with the users registered to see if the credentials match

}
