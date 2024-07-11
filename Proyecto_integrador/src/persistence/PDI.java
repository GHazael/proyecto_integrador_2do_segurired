package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PDI extends Conection {
    // Attributes
    private int pdiId;
    private int clientId;
    private double latitud;
    private double longitud;


    // Constructor
    public PDI() {
        clientId = 0;
        latitud = 0.0;
        longitud = 0.0;

    }

    // Getters
    public int getPdiId() {
        return pdiId;
    }

    public int getClientId() {
        return clientId;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }



    // Setters
    public void setPdiId(int pdiId) {
        this.pdiId = pdiId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }



    // Method to display PDI information
    public void show() {
        System.out.println("PDI ID: " + pdiId);
        System.out.println("Client ID: " + clientId);
        System.out.println("Latitud: " + latitud);
        System.out.println("Longitud: " + longitud);

    }

    // Method to save PDI in the database
    public void save() {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "INSERT INTO puntos_de_interes (CLIENT_ID, LATITUD, LONGITUD) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.setDouble(2, latitud);
            statement.setDouble(3, longitud);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("PDI guardado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el PDI: " + e.getMessage());
        }
    }

    // Method to get all PDIs from the database
    public List<PDI> getAllPdis(String sql) {
        List<PDI> pdiList = new ArrayList<>();
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return pdiList;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PDI pdi = new PDI();
                pdi.setPdiId(resultSet.getInt("PDI_ID"));
                pdi.setClientId(resultSet.getInt("CLIENT_ID"));
                pdi.setLatitud(resultSet.getDouble("LATITUD"));
                pdi.setLongitud(resultSet.getDouble("LONGITUD"));


                pdiList.add(pdi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los PDIs: " + e.getMessage());
        }

        return pdiList;
    }

    // Method to fetch PDI by pdiId
    public PDI fetchPdiById(int pdiId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return null;
        }

        String sql = "SELECT * FROM puntos_de_interes WHERE PDI_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pdiId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PDI pdi = new PDI();
                pdi.setPdiId(resultSet.getInt("PDI_ID"));
                pdi.setClientId(resultSet.getInt("CLIENT_ID"));
                pdi.setLatitud(resultSet.getDouble("LATITUD"));
                pdi.setLongitud(resultSet.getDouble("LONGITUD"));


                return pdi;
            } else {
                System.out.println("PDI no encontrado.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el PDI: " + e.getMessage());
        }
        return null;
    }

    // Method to update a specific field of the PDI in the database
    public void updateField(String fieldName, Object fieldValue, int pdiId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "UPDATE PDI SET " + fieldName + " = ? WHERE PDI_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, fieldValue);
            statement.setInt(2, pdiId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Campo actualizado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el campo: " + e.getMessage());
        }
    }

    public void registrar(){
        PDI p = new PDI();
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Ingrese ID del cliente");
        p.setClientId(sc1.nextInt());
        System.out.println("latitud");
        p.setLatitud(sc1.nextDouble());
        System.out.println("longitud");
        p.setLongitud(sc1.nextDouble());
        p.connect();
        p.save();
        p.disconnect();
        System.out.println("Se ha registrado con exito");
    }

}
