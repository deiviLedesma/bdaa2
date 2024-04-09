/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Clase para gestionar la generación y almacenamiento de placas de automóviles en la base de datos.
 * Permite generar placas únicas y guardarlas en la base de datos.
 * También proporciona un método para guardar información de placas asociadas a automóviles.
 * @author SDavidLedesma
 */
public class PlacaManager {

    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";

    
    /**
     * Genera y guarda un número de placa único en la base de datos.
     *
     * @return El número de placa generado y guardado.
     */
    public String generarYGuardarPlaca() {
        String placa = generarPlaca();

        // Insertar la placa en la base de datos
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("INSERT INTO placas (placa) VALUES (?)")) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la placa en la base de datos: " + e.getMessage());
        }

        return placa;
    }
    
    /**
     * Genera un número de placa único.
     *
     * @return El número de placa generado.
     */
    private String generarPlaca() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        StringBuilder sb = new StringBuilder();

        // Agregar 3 letras aleatorias
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }

        // Agregar un guion
        sb.append("-");

        // Agregar 3 números aleatorios
        for (int i = 0; i < 3; i++) {
            sb.append(numeros.charAt(random.nextInt(numeros.length())));
        }

        return sb.toString();
    }

     /**
     * Guarda información de una placa de automóvil en la base de datos.
     *
     * @param numeroSerie El número de serie del automóvil.
     * @param marca La marca del automóvil.
     * @param linea La línea del automóvil.
     * @param color El color del automóvil.
     * @param modelo El modelo del automóvil.
     * @param licencia La licencia asociada al automóvil.
     * @param placa El número de placa del automóvil.
     * @return true si se guarda la información correctamente, false si ocurre algún error.
     */
    public boolean guardarPlacaEnBD(String numeroSerie, String marca, String linea, String color, String modelo, String licencia, String placa) {
        // Obtener una conexión a la base de datos
        try (Connection conexion = ConexionBD.obtenerConexion(); PreparedStatement statement = conexion.prepareStatement("INSERT INTO placas (numero_serie_automovil, marca, linea, color, modelo, licencia, placa) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            // Establecer los parámetros de la sentencia SQL
            statement.setString(1, numeroSerie);
            statement.setString(2, marca);
            statement.setString(3, linea);
            statement.setString(4, color);
            statement.setString(5, modelo);
            statement.setString(6, licencia);
            statement.setString(7, placa);

            // Ejecutar la sentencia SQL
            statement.executeUpdate();

            System.out.println("Placa guardada en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar placa en la base de datos: " + e.getMessage());
        }
        return false;
    }

}
