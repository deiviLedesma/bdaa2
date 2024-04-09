/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import DAO.LicenciaDAO;
import DAO.PlacaDAO;
import JPA.Licencia;
import JPA.Persona;
import JPA.Placa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 * Clase para gestionar consultas relacionadas con personas, licencias y placas.
 * 
 * @author SDavidLedesma
 */
public class ConsultaManager {

    private LicenciaDAO licenciaDAO;
    private PlacaDAO placaDAO;
    private EntityManagerFactory emf;

    /**
     * Constructor que inicializa los objetos LicenciaDAO, PlacaDAO y EntityManagerFactory.
     */
    public ConsultaManager() {
        this.licenciaDAO = new LicenciaDAO();
        this.placaDAO = new PlacaDAO();
        emf = Persistence.createEntityManagerFactory("PlacasPU");
    }

    /**
     * Busca personas por RFC, nombre similar o año de nacimiento.
     *
     * @param criterio El criterio de búsqueda ('curp', 'nombre', 'año_nacimiento').
     * @param valor El valor para la búsqueda.
     * @return Una lista de personas que coinciden con el criterio de búsqueda.
     */
    public List<Persona> buscarPersonas(String criterio, String valor) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "";
            TypedQuery<Persona> query = null;

            if ("curp".equalsIgnoreCase(criterio)) {
                jpql = "SELECT p FROM Persona p WHERE p.rfc = :valor";
                query = em.createQuery(jpql, Persona.class).setParameter("valor", valor);
            } else if ("nombre".equalsIgnoreCase(criterio)) {
                jpql = "SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :valor, '%'))";
                query = em.createQuery(jpql, Persona.class).setParameter("valor", valor);
            } else if ("año_nacimiento".equalsIgnoreCase(criterio)) {
                try {
                    int anio = Integer.parseInt(valor);
                    jpql = "SELECT p FROM Persona p WHERE FUNCTION('YEAR', p.fecha_nacimiento) = :año";
                    query = em.createQuery(jpql, Persona.class).setParameter("año", anio);
                } catch (NumberFormatException e) {
                    System.err.println("El valor proporcionado para el año de nacimiento no es válido: " + valor);
                    return null;
                }
            } else {
                throw new IllegalArgumentException("Criterio de búsqueda no soportado: " + criterio);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene el historial de licencias de una persona.
     *
     * @param persona La persona para la cual se desea obtener el historial de licencias.
     * @return Una lista de licencias de la persona especificada.
     */
    public List<Licencia> obtenerHistorialLicencias(Persona persona) {
        if (persona == null || persona.getId() == null) {
            throw new IllegalArgumentException("La persona o su ID no pueden ser nulos");
        }
        return (List<Licencia>) licenciaDAO.obtenerLicenciaPorId(persona.getId());
    }

    /**
     * Obtiene el historial de placas de un vehículo.
     *
     * @param vehiculoId El ID del vehículo para el cual se desea obtener el historial de placas.
     * @return Una lista de placas del vehículo especificado.
     */
    public List<Placa> obtenerHistorialPlacas(Long vehiculoId) {
        return (List<Placa>) placaDAO.obtenerPlacaPorId(vehiculoId);
    }
}