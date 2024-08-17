package Persistencia;

import Logica.Usuario;

import java.io.Serializable;
import java.util.List;

public class ControladoraPersistencia implements Serializable {

    // instanciamos los controladores.

    UsuarioController usuariojpa = new UsuarioController();

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
}
