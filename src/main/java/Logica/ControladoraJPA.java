package Logica;

import Persistencia.ControladoraPersistencia;
import java.io.Serializable;
import java.util.List;

public class ControladoraJPA implements Serializable {

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

}
