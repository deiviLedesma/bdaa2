/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.Placa;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author SDavidLedesma
 */
public class PlacaDAO implements IPlacaDAO {

    private EntityManagerFactory emf;

    //para generar placas unicas
    private static final int LONGITUD_PLACA = 7;
    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int NUMERO_INTENTOS_MAX = 10000;

    private Set<String> placasGeneradas = new HashSet<>();
    private Random random = new Random();

    public PlacaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PlacaDAO() {
        this.emf = Persistence.createEntityManagerFactory("PlacasPU");
    }

    public String generarPlaca() {
        String placa;
        int intentos = 0;

        do {
            placa = generarNumeroAleatorio();
            intentos++;
        } while (placasGeneradas.contains(placa) && intentos < NUMERO_INTENTOS_MAX);

        if (intentos == NUMERO_INTENTOS_MAX) {
            throw new RuntimeException("No se pudo generar una placa única después de " + NUMERO_INTENTOS_MAX + " intentos.");
        }

        placasGeneradas.add(placa);
        return placa;
    }

    private String generarNumeroAleatorio() {
        StringBuilder sb = new StringBuilder(LONGITUD_PLACA);
        for (int i = 0; i < LONGITUD_PLACA; i++) {
            int indice = random.nextInt(CARACTERES_PERMITIDOS.length());
            sb.append(CARACTERES_PERMITIDOS.charAt(indice));
        }
        return sb.toString();
    }

    // Método para insertar una nueva placa en la base de datos
    @Override
    public void insertarPlaca(Placa placa) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(placa);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Método para actualizar una placa existente en la base de datos
    @Override
    public void actualizarPlaca(Placa placa) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(placa);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Método para eliminar una placa de la base de datos
    @Override
    public void eliminarPlaca(Long idPlaca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Placa placa = em.find(Placa.class, idPlaca);
            if (placa != null) {
                em.remove(placa);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Manejo del error
        } finally {
            em.close();
        }
    }

    // Método para obtener todas las placas almacenadas en la base de datos
    @Override
    public List<Placa> obtenerTodasLasPlacas() {
        EntityManager em = emf.createEntityManager();
        List<Placa> placas = null;
        try {
            placas = em.createQuery("SELECT p FROM Placa p", Placa.class).getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return placas;
    }

    // Método para obtener una placa por su identificador
    public Placa obtenerPlacaPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Placa placa = null;

        try {
            placa = em.find(Placa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return placa;
    }
}
