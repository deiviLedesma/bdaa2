/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package NEGOCIO;

import JPA.Persona;
import java.util.Random;

/**
 * Clase para generar datos aleatorios de personas.
 */
public class PersonaAleatoria {

    private static final String[] NOMBRES = {"Juan", "María", "Pedro", "Ana", "Luis", "Laura", "Carlos", "Sofía", "Diego", "Elena"};
    private static final String[] APELLIDOS = {"González", "López", "Martínez", "Rodríguez", "Pérez", "García", "Fernández", "Lara", "Sánchez", "Díaz"};
    private static final String[] DIRECCIONES = {"Calle A", "Avenida B", "Calle C", "Avenida D", "Calle E", "Avenida F", "Calle G", "Avenida H", "Calle I", "Avenida J"};

    /**
     * Genera un nombre aleatorio.
     *
     * @return El nombre aleatorio generado.
     */
    public static String generarNombreAleatorio() {
        Random random = new Random();
        return NOMBRES[random.nextInt(NOMBRES.length)];
    }

    /**
     * Genera un apellido aleatorio.
     *
     * @return El apellido aleatorio generado.
     */
    public static String generarApellidoAleatorio() {
        Random random = new Random();
        return APELLIDOS[random.nextInt(APELLIDOS.length)];
    }

    /**
     * Genera una edad aleatoria entre 18 y 70 años.
     *
     * @return La edad aleatoria generada.
     */
    public static int generarEdadAleatoria() {
        Random random = new Random();
        return random.nextInt(53) + 18; // Rango de 18 a 70 años
    }

    /**
     * Genera una dirección aleatoria.
     *
     * @return La dirección aleatoria generada.
     */
    public static String generarDireccionAleatoria() {
        Random random = new Random();
        return DIRECCIONES[random.nextInt(DIRECCIONES.length)];
    }

    /**
     * Genera un nombre completo aleatorio.
     *
     * @return El nombre completo aleatorio generado.
     */
    public static String generarNombreCompletoAleatorio() {
        String[] nombres = {"Juan Perez", "Maria Garcia", "Pedro Lopez", "Luisa Martinez", "Carlos Sanchez", "Ana Ramirez", "Jorge Torres", "Laura Flores", "Roberto Herrera", "Sofia Diaz"};
        Random random = new Random();
        int index = random.nextInt(nombres.length);
        return nombres[index];
    }
}
