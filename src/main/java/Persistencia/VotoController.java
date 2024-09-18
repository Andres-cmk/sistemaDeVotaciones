package Persistencia;

import Logica.Voto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class VotoController{

    private final EntityManagerFactory emf;

    public VotoController(){
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public VotoController(EntityManagerFactory emf){
        this.emf = emf;
    }

    public EntityManager getEntityManager(){return emf.createEntityManager();}

    public void createVoto(Voto voto){
        try(EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(voto);
            em.getTransaction().commit();
        }
    }

    public void deteleteVoto(int idVoto) throws Exception{
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Voto voto = em.find(Voto.class, idVoto);
        if(voto != null){
            em.remove(voto);
        }
        em.getTransaction().commit();
    }

    public void editVoto(Voto voto){
       try(EntityManager em = getEntityManager()){
           em.getTransaction().begin();
           em.merge(voto);
           em.getTransaction().commit();
       }
    }

    public List<Voto> findVotoEntities(){
        return findVotoEntities(true,-1,-1);
    }

    public List<Voto> findVotoEntities(int maxResults, int firstResult) {
        return findVotoEntities(false, maxResults, firstResult);
    }

    private List<Voto> findVotoEntities(boolean all, int maxResults, int firstResult) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Voto> cq = cb.createQuery(Voto.class);
            Root<Voto> rootEntry = cq.from(Voto.class);
            CriteriaQuery<Voto> allQuery = cq.select(rootEntry);
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
