package Persistencia;

import java.util.List;
import DS.HashMap;
import Logica.Eleccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



public class EleccionController{

    public HashMap<String, Integer> canEleVot(int id){
        EntityManager em = getEntityManager();
        String sql = "SELECT can_nombre, COUNT(*) FROM voto JOIN candidato ON (can_id = candidato_vot_id) where cad_ele_id = ? group by can_nombre;";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);

        HashMap<String, Integer> map = new HashMap<>();
        String nombreCandidato;
        int votos;
        List<Object []> lista = query.getResultList();
        for (Object [] i: lista){
            nombreCandidato = i[0].toString();
            votos = ((Number) i[1]).intValue();
            map.put(nombreCandidato,votos);
        }
        return map;
    }

    public HashMap<String, Integer> canEleVot(){
        EntityManager em = getEntityManager();
        String sql = "SELECT can_nombre, COUNT(*) FROM voto JOIN candidato ON (can_id = candidato_vot_id)  group by can_nombre;";
        Query query = em.createNativeQuery(sql);

        HashMap<String, Integer> map = new HashMap<>();
        String nombreCandidato;
        int votos;
        List<Object []> lista = query.getResultList();
        for (Object [] i: lista){
            nombreCandidato = i[0].toString();
            votos = ((Number) i[1]).intValue();
            map.put(nombreCandidato,votos);
        }
        return map;
    }


    private final EntityManagerFactory emf;

    public EleccionController(){
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public EleccionController(EntityManagerFactory emf){
        this.emf = emf;
    }

    public int obtenerVotos(long idEleccion) {
        EntityManager em = getEntityManager();
        String sql = "SELECT COUNT(*) FROM voto WHERE eleccion_vot_id = ?";
        Query consulta = em.createNativeQuery(sql);
        consulta.setParameter(1,idEleccion);

        Number count = (Number) consulta.getSingleResult();
        return count.intValue();
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

    public Eleccion findEleccion(int id){
        EntityManager em;
        try {
            em = getEntityManager();
            return em.find(Eleccion.class, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
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