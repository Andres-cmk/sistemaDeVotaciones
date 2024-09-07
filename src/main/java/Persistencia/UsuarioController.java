package Persistencia;

import Logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class UsuarioController {


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
        EntityManager em = null;
        try  {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            if(em!=null) em.close();
        }
    }

    public Usuario findUsuario(int id){
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.find(Usuario.class, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(em!=null) em.close();
        }
        return null;
    }

    public void delete(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // En caso de error, se hace rollback de la transacción.
            }
        }finally {
            if(em!=null) em.close();
        }
    }

    public void edit(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em!= null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            if(em!=null) em.close();
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
