/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JPA;

import DAO.LicenciaDAO;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SDavidLedesma
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlacasPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        /*
        String numero = "n";
        Date fechaEmision = new Date();
        Date fechaRecepcion = new Date();
        String numeroSerieAutomovil = "serieSXXXX";
        String marca = "marca";
        String linea = "linea";
        String color = "color";
        int modelo = 1;
        Placa placa = new Placa(numero, fechaEmision, fechaRecepcion, numeroSerieAutomovil, marca, linea, color, modelo);

        
        PlacaDAO p = new PlacaDAO();
        System.out.println(p.obtenerPlacaPorId(2L));
         */
 /*
        //ejemplo para actualizar licencia
         // Crear una instancia de Licencia con los datos actualizados
        Licencia licencia = new Licencia();
        licencia.setId(1L); // Supongamos que el ID de la licencia que queremos actualizar es 1
        licencia.setRFC("RFC actualizado");
        licencia.setNombre("Nombre actualizado");
        licencia.setApellidoPaterno("apppp");
        licencia.setFechaExpedicion(new Date());
        licencia.setFechaNacimiento(new Date()); // Fecha de nacimiento actualizada
        licencia.setTelefono("Teléfono actualizado");
        licencia.setVigencia(3); // Supongamos que la vigencia se actualiza a 3 años
        licencia.setCosto(1200.00); // Costo actualizado
         */
        Licencia licencia = new Licencia();
        LicenciaDAO o = new LicenciaDAO();
        Automovil a = new Automovil();
        Persona p = new Persona();
        Tramite t = new Tramite();
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
