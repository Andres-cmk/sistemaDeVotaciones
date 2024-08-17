package Persistencia;

import Logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class UsuarioController implements Serializable {


    private final EntityManagerFactory emf;


    public UsuarioController() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public UsuarioController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }
    }

    public void delete(int id) {
        EntityManager em;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void edit(Usuario usuario) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> rootEntry = cq.from(Usuario.class);
            CriteriaQuery<Usuario> allQuery = cq.select(rootEntry);
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
