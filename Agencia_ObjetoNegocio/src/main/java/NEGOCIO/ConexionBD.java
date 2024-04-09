/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos PlacasItson.
 *
 * @author SDavidLedesma
 */
public class ConexionBD {

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";

    /**
     * Obtiene la conexión a la base de datos PlacasItson.
     *
     * @return La conexión a la base de datos, o null si hay un error.
     */
    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
