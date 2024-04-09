/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase para gestionar la carga y visualización del historial de personas en
 * una tabla. Permite cargar los datos del historial de personas desde la base
 * de datos y mostrarlos en una tabla.
 *
 * @author SDavidLedesma
 */
public class HistorialPersonaManager {

    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";

    /**
     * Carga el historial de personas desde la base de datos y lo muestra en una
     * tabla.
     *
     * @param table La tabla donde se mostrará el historial de personas.
     */
    public static void cargarHistorialPersonas(JTable table) {
        // Definir las columnas del modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("CURP");
        model.addColumn("Fecha de Nacimiento");

        // Consulta SQL para obtener el historial de personas
        String sql = "SELECT nombre_completo, curp, fecha_nacimiento FROM personas";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            // Iterar sobre los resultados y agregarlos al modelo de la tabla
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                String curp = rs.getString("curp");
                String fechaNacimiento = rs.getString("fecha_nacimiento");

                model.addRow(new Object[]{nombreCompleto, curp, fechaNacimiento});
            }

            // Asignar el modelo de la tabla
            table.setModel(model);
        } catch (SQLException e) {
            System.err.println("Error al cargar el historial de personas: " + e.getMessage());
        }
    }
    
    
}
