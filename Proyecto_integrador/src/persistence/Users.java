package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Users extends Conection {
    //actributes
    private String name;
    private String f_surename;
    private String m_surename;
    private String username;
    private String password;
    private String role;
    private boolean active = true;
    int id;


    //constructor
    public Users() {
        name = "";
        f_surename = "";
        m_surename = "";
        username = "";
        password = "";
        role = "";
        active = true;

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getF_surename() {
        return f_surename;
    }

    public String getM_surename() {
        return m_surename;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setF_surename(String f_surename) {
        this.f_surename = f_surename;
    }

    public void setM_surename(String m_surename) {
        this.m_surename = m_surename;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role.toLowerCase();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    //mehtods

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("F surename: " + f_surename);
        System.out.println("M surename: " + m_surename);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);
        System.out.println("Active: " + active);

    }

    //METODO PARA GUARDAR EN LA BASE DE DATOS

    public void save() {
        Connection connection = getConnection(); //SOLICITAR A LA CLASE CONNECT CUAL ES LA CONEXION;
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }


        String sql = "INSERT INTO USERS (NOMBRE, APELLIDOPAT, APELLIDOMAT, USERNAME, PASSWORD, ROLE, ACTIVE) VALUES (?, ?, ?, ?, ?, ?, ?)";

        //GENERO UN OBJETO LLAMADO statement DE LA CLASE PreparedStatemen QUE CONTENDRA EL QUERY
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, f_surename);
            statement.setString(3, m_surename);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, role);
            statement.setBoolean(7, active);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario guardado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    // metodo para leer a los ususarios de la base de datos y devolver una lista de objetos ctipo usuario

    public List<Users> getAllUsers(String sql) {
        List<Users> usersList = new ArrayList<>();
        Connection connection = getConnection(); // Solicitar a la clase Connect cuál es la conexión
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return usersList;
        }



        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("NOMBRE"));
                user.setF_surename(resultSet.getString("APELLIDOPAT"));
                user.setM_surename(resultSet.getString("APELLIDOMAT"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(resultSet.getString("ROLE"));
                user.setActive(resultSet.getBoolean("ACTIVE"));
                user.setId(resultSet.getInt("USER_ID"));

                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return usersList;
    }

    // Método para obtener el userId basado en el nombre de usuario
    public int fetchUserIdByUsername(String username) {
        int userId = 0;
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return 0;
        }

        String sql = "SELECT USER_ID FROM USERS WHERE USERNAME = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("USER_ID");
                return userId;
            } else {
                System.out.println("Usuario no encontrado.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el userId: " + e.getMessage());
        }
        return 0;
    }

    // Método para actualizar un campo específico del usuario en la base de datos
    public void updateField(String fieldName, Object fieldValue, int userId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "UPDATE USERS SET " + fieldName + " = ? WHERE USER_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, fieldValue);
            statement.setInt(2, userId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Campo actualizado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el campo: " + e.getMessage());
        }
    }

    //
    public void registrar(){
        Scanner sc = new Scanner(System.in);
        String op;
        Users u = new Users();
        System.out.println("Ingrese el nombre de usuario");
        op = sc.nextLine();
        u.setUsername(op);
        System.out.println("Ingrese el password de usuario");
        op = sc.nextLine();
        u.setPassword(op);
        System.out.println("Ingrese el nombre del usuario");
        op = sc.nextLine();
        u.setName(op);
        System.out.println("Ingrese el apellido paterno del usuario");
        op = sc.nextLine();
        u.setF_surename(op);
        System.out.println("Ingrese el apellido materno del usuario");
        op = sc.nextLine();
        u.setM_surename(op);
        u.setActive(true);
        System.out.println("Ingrese el rol del usuario");
        op = sc.nextLine();
        u.setRole(op);
        u.connect();
        u.save();
        u.disconnect();
        System.out.println("Usuario registrado exitosamente");
    }

}