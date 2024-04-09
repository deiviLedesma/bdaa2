/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author SDavidLedesma
 */
public class LicenciaManager {

    private Set<String> numerosLicenciaGenerados;
    private Random random;
    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";
    private Connection conexion;

    /**
     * Constructor por defecto
     */
    public LicenciaManager() {
        this.numerosLicenciaGenerados = new HashSet<>();
        this.random = new Random();
    }

    /**
     * Constructor que permite inicializar la conexión a la base de datos.
     *
     * @param conexion La conexión a la base de datos.
     */
    public LicenciaManager(Connection conexion) {
        this.conexion = conexion;
        this.random = new Random();
        this.numerosLicenciaGenerados = new HashSet<>();

    }

    /**
     * Guarda la información de una persona en la base de datos.
     *
     * @param RFC El RFC de la persona.
     * @param nombre El nombre de la persona.
     * @param apellidoPaterno El apellido paterno de la persona.
     * @param apellidoMaterno El apellido materno de la persona.
     * @param fechaNacimiento La fecha de nacimiento de la persona en formato
     * "YYYY-MM-DD".
     * @param telefono El teléfono de contacto de la persona.
     * @return true si se guarda la información correctamente, false si ocurre
     * algún error.
     */
    public boolean generarYGuardarLicencia(String RFC, String nombre, String apellidoPaterno, String apellidoMaterno,
            String fechaNacimiento, String telefono, int vigencia, double costo, String tipoLicencia) {
        String numeroLicencia = generarNumeroLicencia();

        // Insertar la licencia en la base de datos
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("INSERT INTO licencias (RFC, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, telefono, vigencia, costo, numero, tipo_licencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); PreparedStatement stmtPersona = conn.prepareStatement("INSERT INTO personas (nombre_completo, fecha_nacimiento) VALUES (?, ?)")) {

            stmt.setString(1, RFC);
            stmt.setString(2, nombre);
            stmt.setString(3, apellidoPaterno);
            stmt.setString(4, apellidoMaterno);
            stmt.setString(5, fechaNacimiento);
            //stmt.setDate(5, new java.sql.Date(fechaNacimiento.getTime()));
            stmt.setString(6, telefono);
            stmt.setInt(7, vigencia);
            stmt.setDouble(8, costo);
            stmt.setString(9, numeroLicencia);
            stmt.setString(10, tipoLicencia);

            stmt.executeUpdate();
            System.out.println("Licencia guardada en la base de datos.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar la licencia en la base de datos: " + e.getMessage());
            return false;
        }

        //con la misma informacion de licencia guardamos la de persona
    }

    /**
     * Guarda la información de una persona en la base de datos.
     *
     * @param RFC El RFC de la persona.
     * @param nombre El nombre de la persona.
     * @param apellidoPaterno El apellido paterno de la persona.
     * @param apellidoMaterno El apellido materno de la persona.
     * @param fechaNacimiento La fecha de nacimiento de la persona en formato
     * "YYYY-MM-DD".
     * @param telefono El teléfono de contacto de la persona.
     * @return true si se guarda la información correctamente, false si ocurre
     * algún error.
     */
    public boolean guardarPersona(String RFC, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String telefono) {
        String nombreCompleto = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("INSERT INTO personas (rfc, nombre_completo, fecha_nacimiento, telefono) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, RFC);
            stmt.setString(2, nombreCompleto);
            stmt.setString(3, fechaNacimiento);
            stmt.setString(4, telefono);
            stmt.executeUpdate();
            System.out.println("Información de persona guardada en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar la información de persona en la base de datos: " + e.getMessage());
        }
        return false;
    }

    /**
     * Genera un número de licencia único.
     *
     * @return El número de licencia generado.
     */
    public String generarNumeroLicencia() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                sb.append(letras.charAt(random.nextInt(letras.length())));
            } else {
                sb.append(numeros.charAt(random.nextInt(numeros.length())));
            }
        }

        return sb.toString();
    }

    /**
     * Con este metodo generamos numero de licecnia aleatorio
     *
     * @return el numero de la licencia
     */
    private String generarNumeroLicenciaAleatorio() {
        StringBuilder sb = new StringBuilder();
        // Generar 3 letras aleatorias
        for (int i = 0; i < 3; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            sb.append(letra);
        }
        sb.append('-');
        // Generar 3 dígitos aleatorios
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * Metodo con el cual obtenemos las licencias existentes
     *
     * @return las licencias
     */
    public String obtenerLicenciaExistente() {
        String numeroLicencia = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT numero FROM licencias LIMIT 1"); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                numeroLicencia = rs.getString("numero");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener número de licencia de la base de datos: " + e.getMessage());
        }
        return numeroLicencia;
    }

    /**
     * Metodo para guardar las licencias en la base de datos
     *
     * @param numeroLicencia son las licencias
     */
    private void guardarNumeroLicenciaEnBD(String numeroLicencia) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("INSERT INTO licencias (numero) VALUES (?)")) {
            stmt.setString(1, numeroLicencia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar número de licencia en la base de datos: " + e.getMessage());
        }
    }
}
