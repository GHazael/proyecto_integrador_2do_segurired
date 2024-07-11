package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Guards extends Conection {
    private int guardId;
    private int userId;
    private float horasLaboradas;

    // Constructor
    public Guards() {
        // Constructor vacío
    }

    // Getters y Setters
    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getHorasLaboradas() {
        return horasLaboradas;
    }

    public void setHorasLaboradas(float horasLaboradas) {
        this.horasLaboradas = horasLaboradas;
    }

    // Método para guardar un nuevo guardia en la base de datos
    public void saveGuard() {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "INSERT INTO guards (USER_ID, HORAS_LABORADAS) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setFloat(2, horasLaboradas);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Guardia añadido correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al añadir guardia: " + e.getMessage());
        }
    }

    public void updateField(String fieldName, Object fieldValue, int userId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "UPDATE GUARDS SET " + fieldName + " = ? WHERE USER_ID = ?";

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

    //metodo para leer la base de datos y añadir a los guardias a una lista
    public List<Guards> getAllGuards(String sql) {
        List<Guards> usersList = new ArrayList<>();
        Connection connection = getConnection(); // Solicitar a la clase Connect cuál es la conexión
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return usersList;
        }



        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Guards guard = new Guards();
                guard.setGuardId(resultSet.getInt("guard_id"));
                guard.setUserId(resultSet.getInt("user_id"));
                guard.setHorasLaboradas(resultSet.getFloat("horas_laboradas"));

                usersList.add(guard);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return usersList;
    }

}
