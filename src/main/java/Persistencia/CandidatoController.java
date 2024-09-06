package Persistencia;

import Logica.Candidato;
import Logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
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
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(candidato);
            em.getTransaction().commit();
        }
    }

    public void deleteCandidato(int idCandidato){
        EntityManager em;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, idCandidato);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void editCandidato(Candidato candidato){
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(candidato);
            em.getTransaction().commit();
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
