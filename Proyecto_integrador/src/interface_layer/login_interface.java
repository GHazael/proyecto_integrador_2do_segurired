package interface_layer;

import javax.swing.JOptionPane;

public class login_interface {


    public String[] promptForLogin() {
        String[] loginDetails = new String[2]; // Array to hold username and password

        // Prompt user for username
        loginDetails[0] = JOptionPane.showInputDialog(null, "Enter your username:");

        // Prompt user for password (using password field)
        loginDetails[1] = JOptionPane.showInputDialog(null, "Enter your password:", "Password", JOptionPane.PLAIN_MESSAGE);

        return loginDetails;
    }

    public static void main(String[] args) {
        login_interface login = new login_interface();
        String[] loginDetails = login.promptForLogin();
        System.out.println(loginDetails[0]);
        System.out.println(loginDetails[1]);

    }
}