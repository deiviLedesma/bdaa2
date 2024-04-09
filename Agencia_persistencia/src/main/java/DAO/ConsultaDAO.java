/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.Licencia;
import JPA.Placa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author SDavidLedesma
 */
public class ConsultaDAO {

    private EntityManager entityManager;

    public ConsultaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Método para buscar historial de licencias por CURP
    public List<Licencia> buscarHistorialLicenciasPorRFC(String rfc) {
        TypedQuery<Licencia> query = entityManager.createQuery(
                "SELECT l FROM Licencia l WHERE l.persona.rfc = :rfc", Licencia.class);
        query.setParameter("rfc", rfc);
        return query.getResultList();
    }

    // Método para buscar historial de placas por nombre similar de persona
    public List<Placa> buscarHistorialPlacasPorNombre(String nombre) {
        TypedQuery<Placa> query = entityManager.createQuery(
                "SELECT p FROM Placa p WHERE p.propietario.nombre LIKE :nombre", Placa.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    // Método para buscar historial de licencias por año de nacimiento
    public List<Licencia> buscarHistorialLicenciasPorAnioNacimiento(int anio) {
        TypedQuery<Licencia> query = entityManager.createQuery(
                "SELECT l FROM Licencia l WHERE YEAR(l.persona.fechaNacimiento) = :anio", Licencia.class);
        query.setParameter("anio", anio);
        return query.getResultList();
    }
}
