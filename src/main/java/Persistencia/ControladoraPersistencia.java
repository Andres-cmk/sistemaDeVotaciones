package Persistencia;

import Logica.Eleccion;
import Logica.Usuario;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public class ControladoraPersistencia {

    // instanciamos los controladores.

    UsuarioController usuariojpa = new UsuarioController();
    EleccionController eleccionController = new EleccionController();

    // aqui van los metodos para cada accion
    // Usuario

    public void crearUsuario(Usuario usuario) {
       usuariojpa.create(usuario);
    }

    public void eliminarUsuario(int id){
        usuariojpa.delete(id);
    }

    public List<Usuario> getUsuarios(){
        return usuariojpa.findUsuarioEntities();
    }

    public void crerEleccion(Eleccion eleccion) {
        eleccionController.createEleccion(eleccion);

    }

    public List<Eleccion> getEleccion(){
        return eleccionController.findEleccionEntities();
    }
}
