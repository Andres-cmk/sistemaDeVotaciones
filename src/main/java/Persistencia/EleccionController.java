package Persistencia;

import java.io.Serializable;
import java.util.List;
import Logica.Eleccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/*
*la interfaz Serializable sirve para cuando un objeto sea mandado por
* una red se combierta en una cadena de bytes.
* */

public class EleccionController implements Serializable{

    private final EntityManagerFactory emf;

    public EleccionController(){
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public EleccionController(EntityManagerFactory emf){
        this.emf = emf;
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void createEleccion(Eleccion eleccion){
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(eleccion);
            em.getTransaction().commit();
        }
    }

    public void deleteEleccion(int idEleccion) throws Exception{
        EntityManager em;
        em = getEntityManager();
        em.getTransaction().begin();
        Eleccion eleccion = em.find(Eleccion.class, idEleccion);
        if(eleccion != null){
            em.remove(eleccion);
        }
        em.getTransaction().commit();
    }

    public void editEleccion(Eleccion eleccion){
        try (EntityManager em = getEntityManager()){
            em.getTransaction().begin();
            em.merge(eleccion);
            em.getTransaction().commit();
        }
    }

    public List<Eleccion> findEleccionEntities() {
        return findEleccionEntities(true, -1, -1);
    }

    public List<Eleccion> findEleccionEntities(int maxResults, int firstResult) {
        return findEleccionEntities(false, maxResults, firstResult);
    }

    private List<Eleccion> findEleccionEntities(boolean all, int maxResults, int firstResult) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Eleccion> cq = cb.createQuery(Eleccion.class);
            Root<Eleccion> rootEntry = cq.from(Eleccion.class);
            CriteriaQuery<Eleccion> allQuery = cq.select(rootEntry);
            if (!all) {
                return em.createQuery(allQuery)
                        .setMaxResults(maxResults)
                        .setFirstResult(firstResult)
                        .getResultList();
            } else {
                return em.createQuery(allQuery).getResultList();
            }
        }
    }
}
