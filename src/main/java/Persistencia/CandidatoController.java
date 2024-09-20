package Persistencia;

import Logica.Candidato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class CandidatoController{

    private final EntityManagerFactory emf;

    public List<String> obtenerCandidatosPorEleccion(int cad_ele_id){
        EntityManager em = getEntityManager();
        String query = "SELECT can_nombre FROM candidato WHERE cad_ele_id = ?";
        Query consulta = em.createNativeQuery(query);
        consulta.setParameter(1,cad_ele_id);

        return consulta.getResultList();
    }

    public int obtenerCantidadVotos(long id){
        EntityManager em = getEntityManager();
        String sql = "SELECT COUNT(usuario_usu_id) FROM voto WHERE candidato_vot_id = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);

        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    public CandidatoController() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public CandidatoController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Candidato findCandidato(int id){
        EntityManager em = getEntityManager();
        try {
            return em.find(Candidato.class, id);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
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
            Candidato candidato = em.find(Candidato.class, idCandidato);
            if (candidato != null) {
                em.remove(candidato);
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