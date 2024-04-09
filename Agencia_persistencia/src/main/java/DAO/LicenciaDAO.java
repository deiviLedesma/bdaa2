/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.Licencia;
import java.util.HashSet;
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
public class LicenciaDAO implements ILicenciaDAO {

    private EntityManagerFactory emf;

    
    //Esto es para generar las licencias
    private static final int LONGITUD_LICENCIA = 10;
    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int NUMERO_INTENTOS_MAX = 10000;

    private Set<String> licenciasGeneradas = new HashSet<>();
    private Random random = new Random();

    public LicenciaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public LicenciaDAO() {
        this.emf = Persistence.createEntityManagerFactory("PlacasPU");
    }

    @Override
    public void insertarLicencia(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(licencia);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarLicencia(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Licencia licenciaActualizada = em.merge(licencia);
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

    @Override
    public void eliminarLicencia(Long idLicencia) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Licencia licencia = em.find(Licencia.class, idLicencia);
            if (licencia != null) {
                em.remove(licencia);
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

    @Override
    public Licencia obtenerLicenciaPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Licencia licencia = null;

        try {
            licencia = em.find(Licencia.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return licencia;
    }

    public String generarLicencia() {
        String licencia;
        int intentos = 0;

        do {
            licencia = generarNumeroAleatorio();
            intentos++;
        } while (licenciasGeneradas.contains(licencia) && intentos < NUMERO_INTENTOS_MAX);

        if (intentos == NUMERO_INTENTOS_MAX) {
            throw new RuntimeException("No se pudo generar una licencia única después de " + NUMERO_INTENTOS_MAX + " intentos.");
        }

        licenciasGeneradas.add(licencia);
        return licencia;
    }

    private String generarNumeroAleatorio() {
        StringBuilder sb = new StringBuilder(LONGITUD_LICENCIA);
        for (int i = 0; i < LONGITUD_LICENCIA; i++) {
            int indice = random.nextInt(CARACTERES_PERMITIDOS.length());
            sb.append(CARACTERES_PERMITIDOS.charAt(indice));
        }
        return sb.toString();
    }
}
