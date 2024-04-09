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

/**
 *
 * @author SDavidLedesma
 */
public class CurpGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/PlacasItson";
    private static final String USER = "root";
    private static final String PASSWORD = "Inunanash1";

    
    /**
     * Metodo que nos genera y guarda la curp
     */
    public static void generarYGuardarCurp() {
        // Consulta SQL para obtener las licencias sin CURP
        String sql = "SELECT id, nombre_completo, fecha_nacimiento FROM personas WHERE curp IS NULL";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmtLicencias = conn.prepareStatement(sql); ResultSet rsLicencias = stmtLicencias.executeQuery()) {

            while (rsLicencias.next()) {
                long idPersona = rsLicencias.getLong("id");
                String nombreCompleto = rsLicencias.getString("nombre_completo");
                String fechaNacimiento = rsLicencias.getString("fecha_nacimiento");

                // Generar la CURP
                String curp = generarCurp(nombreCompleto, fechaNacimiento);

                // Guardar la CURP en la tabla personas
                guardarCurp(idPersona, curp);
            }

            System.out.println("CURPs generadas y guardadas correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al generar y guardar las CURPs: " + e.getMessage());
        }
    }

    /**
     * Genera una CURP basada en el nombre completo y la fecha de nacimiento.
     *
     * @param nombreCompleto El nombre completo de la persona.
     * @param fechaNacimiento La fecha de nacimiento en formato "YYYY-MM-DD".
     * @return La CURP generada.
     */
    private static String generarCurp(String nombreCompleto, String fechaNacimiento) {
        String[] partesNombre = nombreCompleto.split("\\s+");
        String primerApellido = partesNombre[0];
        String segundoApellido = partesNombre.length > 1 ? partesNombre[1] : "";
        String nombre = partesNombre[partesNombre.length - 1];

        // Lógica para generar la CURP...
        // Implementa aquí la lógica según las reglas descritas arriba
        StringBuilder curpBuilder = new StringBuilder();

        // Paso 1: Tomar la primera letra del primer apellido
        curpBuilder.append(Character.toUpperCase(primerApellido.charAt(0)));

        // Paso 2: Tomar la primera vocal interna del primer apellido
        curpBuilder.append(obtenerPrimeraVocal(primerApellido.substring(1)));

        // Paso 3: Tomar la primera letra del segundo apellido
        curpBuilder.append(Character.toUpperCase(segundoApellido.charAt(0)));

        // Paso 4: Tomar la primera letra del nombre
        curpBuilder.append(Character.toUpperCase(nombre.charAt(0)));

        // Paso 5: Tomar los dos últimos dígitos del año, seguidos de los dos dígitos del mes y dos dígitos del día de nacimiento
        String[] partesFecha = fechaNacimiento.split("-");
        String anio = partesFecha[0].substring(2);
        String mes = partesFecha[1];
        String dia = partesFecha[2];
        curpBuilder.append(anio).append(mes).append(dia);

        // Paso 6: Agregar un dígito específico para indicar el sexo (hombre: 0-4, mujer: 5-9)
        // Aquí debes agregar la lógica para determinar el sexo y asignar el dígito correspondiente
        // Paso 7: Agregar un código específico para la entidad federativa de nacimiento
        // Aquí debes agregar la lógica para determinar la entidad federativa y asignar el código correspondiente
        // Paso 8: Agregar las primeras consonantes internas del apellido y nombre
        curpBuilder.append(obtenerConsonantesInternas(primerApellido)).append(obtenerConsonantesInternas(segundoApellido)).append(obtenerConsonantesInternas(nombre));

        // Paso 9: Calcular el dígito verificador
        // Aquí debes implementar la lógica para calcular el dígito verificador
        // Devolver la CURP generada
        return curpBuilder.toString();
    }

    /**
     * Obtiene la primera vocal interna de un apellido.
     *
     * @param apellido El apellido del cual se desea obtener la primera vocal interna.
     * @return La primera vocal interna del apellido.
     */
    private static char obtenerPrimeraVocal(String apellido) {
        char[] vocales = {'A', 'E', 'I', 'O', 'U'};
        for (char letra : apellido.toUpperCase().toCharArray()) {
            for (char vocal : vocales) {
                if (letra == vocal) {
                    return letra;
                }
            }
        }
        // Si no se encuentra ninguna vocal, retornar una 'X'
        return 'X';
    }

    /**
     * Obtiene las consonantes internas de una palabra.
     *
     * @param palabra La palabra de la cual se desean obtener las consonantes internas.
     * @return Las consonantes internas de la palabra.
     */
    private static String obtenerConsonantesInternas(String palabra) {
        StringBuilder consonantes = new StringBuilder();
        char[] vocales = {'A', 'E', 'I', 'O', 'U'};
        for (char letra : palabra.toUpperCase().toCharArray()) {
            boolean esVocal = false;
            for (char vocal : vocales) {
                if (letra == vocal) {
                    esVocal = true;
                    break;
                }
            }
            if (!esVocal && letra != 'Ñ') {
                consonantes.append(letra);
            }
        }
        return consonantes.toString();
    }

    /**
     * Guarda la CURP generada para una persona en la base de datos.
     *
     * @param idPersona El ID de la persona para la cual se desea guardar la CURP.
     * @param curp La CURP generada.
     */
    private static void guardarCurp(long idPersona, String curp) {
        // Guardar la CURP en la tabla personas
        String sql = "UPDATE personas SET curp = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curp);
            stmt.setLong(2, idPersona);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar la CURP para la persona con ID " + idPersona + ": " + e.getMessage());
        }
    }
}
