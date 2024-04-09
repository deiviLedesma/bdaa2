/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import JPA.Placa;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IPlacaDAO {

    public void insertarPlaca(Placa placa);

    public void actualizarPlaca(Placa placa);

    public void eliminarPlaca(Long idPlaca);

    public Placa obtenerPlacaPorId(Long id);

    public List<Placa> obtenerTodasLasPlacas();

    // una posible solucion
    /*
     // Método para obtener todas las placas de la base de datos
    public List<Placa> obtenerTodasLasPlacas() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Placa p");
        List<Placa> placas = query.getResultList();
        em.close();
        return placas;
    }
     */
}
