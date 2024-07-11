package persistence;

import java.sql.*;

public class Conection {
    private Connection connection; //variable donde se guardara la conexion

    //metodo para conectarse a la base de datos
    public void connect() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/PROYECTOINTEGRADOR";
        String username = "root";
        String password = "pasto1208";

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexión exitosa a la base de datos!");



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }

    //metodo para desconectarse de la base de datos
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    //metodo para leer la base de datos
    public void query(String sql) {
        if (connection == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Obtener metadata para procesar los resultados de manera genérica
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Imprimir nombres de las columnas
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println();

            // Imprimir los resultados fila por fila
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }


}
