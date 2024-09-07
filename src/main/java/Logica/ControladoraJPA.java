package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraJPA{

    ControladoraPersistencia persistencia = new ControladoraPersistencia();

    public void crearUsuario(Usuario usuario){
        persistencia.crearUsuario(usuario);
    }

    public void eliminarUsuario(int id){
        persistencia.eliminarUsuario(id);
    }

    public List<Usuario> getUsuarios(){
        return persistencia.getUsuarios();
    }

    public void crearEleeccion(Eleccion eleccion){
        persistencia.crerEleccion(eleccion);
    }

    public List<Eleccion> getELeccion(){
        return persistencia.getEleccion();
    }

    public void removeEleccion(int idEleccion) throws Exception {
        persistencia.eliminarEleccion(idEleccion);
    }
}
