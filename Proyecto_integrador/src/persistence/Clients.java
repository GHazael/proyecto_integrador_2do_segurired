package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clients extends Conection {
    // Attributes
    private int userId;
    private String regimenFiscal;
    private String razonSocial;
    private String estado;
    private String numero;
    private String colonia;
    private String municipio;
    private String calle;
    private int pdiId;

    // Constructor
    public Clients() {

        userId = 0;
        regimenFiscal = "";
        razonSocial = "";
        estado = "";
        numero = "";
        colonia = "";
        municipio = "";
        calle = "";
        pdiId = 0;
    }

    // Getters


    public int getUserId() {
        return userId;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getEstado() {
        return estado;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getCalle() {
        return calle;
    }

    public int getPdiId() {
        return pdiId;
    }

    // Setters

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setPdiId(int pdiId) {
        this.pdiId = pdiId;
    }

    // Method to display client information
    public void show() {

        System.out.println("User ID: " + userId);
        System.out.println("Regimen Fiscal: " + regimenFiscal);
        System.out.println("Razon Social: " + razonSocial);
        System.out.println("Estado: " + estado);
        System.out.println("Numero: " + numero);
        System.out.println("Colonia: " + colonia);
        System.out.println("Municipio: " + municipio);
        System.out.println("Calle: " + calle);
        System.out.println("PDI ID: " + pdiId);
    }

    // Method to save client in the database
    public void save() {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "INSERT INTO CLIENTS (USER_ID, REGIMEN_FISCAL, RAZON_SOCIAL, ESTADO, NUMERO, COLONIA, MUNICIPIO, CALLE, PDI_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, regimenFiscal);
            statement.setString(3, razonSocial);
            statement.setString(4, estado);
            statement.setString(5, numero);
            statement.setString(6, colonia);
            statement.setString(7, municipio);
            statement.setString(8, calle);
            statement.setInt(9, pdiId);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente guardado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    // Method to get all clients from the database
    public List<Clients> getAllClients(String sql) {
        List<Clients> clientsList = new ArrayList<>();
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return clientsList;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clients client = new Clients();
                client.setUserId(resultSet.getInt("USER_ID"));
                client.setRegimenFiscal(resultSet.getString("REGIMEN_FISCAL"));
                client.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
                client.setEstado(resultSet.getString("ESTADO"));
                client.setNumero(resultSet.getString("NUMERO"));
                client.setColonia(resultSet.getString("COLONIA"));
                client.setMunicipio(resultSet.getString("MUNICIPIO"));
                client.setCalle(resultSet.getString("CALLE"));
                client.setPdiId(resultSet.getInt("PDI_ID"));

                clientsList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los clientes: " + e.getMessage());
        }

        return clientsList;
    }

    // Method to fetch client by clientId
    public Clients fetchClientById(int clientId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return null;
        }

        String sql = "SELECT * FROM CLIENTS WHERE CLIENT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Clients client = new Clients();
                client.setUserId(resultSet.getInt("USER_ID"));
                client.setRegimenFiscal(resultSet.getString("REGIMEN_FISCAL"));
                client.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
                client.setEstado(resultSet.getString("ESTADO"));
                client.setNumero(resultSet.getString("NUMERO"));
                client.setColonia(resultSet.getString("COLONIA"));
                client.setMunicipio(resultSet.getString("MUNICIPIO"));
                client.setCalle(resultSet.getString("CALLE"));
                client.setPdiId(resultSet.getInt("PDI_ID"));

                return client;
            } else {
                System.out.println("Cliente no encontrado.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el cliente: " + e.getMessage());
        }
        return null;
    }

    // Method to update a specific field of the client in the database
    public void updateField(String fieldName, Object fieldValue, int clientId) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "UPDATE CLIENTS SET " + fieldName + " = ? WHERE CLIENT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, fieldValue);
            statement.setInt(2, clientId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Campo actualizado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el campo: " + e.getMessage());
        }
    }
}

