/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.Tramite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class ReporteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";

    public List<Tramite> obtenerTramites() {
        List<Tramite> tramites = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tramites");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Obtener los datos de cada trámite y crear un objeto Tramite
                Tramite tramite = new Tramite();
                tramite.setId(rs.getLong("id"));
                tramite.setTipo(rs.getString("tipo"));
                tramite.getFechaTramite(rs.getDate("fecha"));
                // Aquí agregar más campos según la estructura de tu tabla de trámites

                // Agregar el trámite a la lista
                tramites.add(tramite);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener trámites de la base de datos: " + e.getMessage());
        }

        return tramites;
    }
}