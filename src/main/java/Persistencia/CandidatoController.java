package Persistencia;

import Logica.Candidato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class CandidatoController{

    private final EntityManagerFactory emf;


    public CandidatoController() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public CandidatoController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createCandidato(Candidato candidato){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(candidato);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            if(em !=null){
                em.close();
            }
        }
    }

    public void deleteCandidato(int idCandidato){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Candidato candidato = em.find(Candidato.class, idCandidato);
            if (candidato != null) {
                em.remove(candidato);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            if(em!= null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }

    public void editCandidato(Candidato candidato){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(candidato);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em!= null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }

    public List<Candidato> findCandidatoEntities() {
        return findCandidatoEntities(true, -1, -1);
    }

    public List<Candidato> findCandidatoEntities(int maxResults, int firstResult) {
        return findCandidatoEntities(false, maxResults, firstResult);
    }

    private List<Candidato> findCandidatoEntities(boolean all, int maxResults, int firstResult) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Candidato> cq = cb.createQuery(Candidato.class);
            Root<Candidato> rootEntry = cq.from(Candidato.class);
            CriteriaQuery<Candidato> allQuery = cq.select(rootEntry);
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
