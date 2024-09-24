package Persistencia;

import DS.HashMap;
import Logica.Candidato;
import Logica.Eleccion;
import Logica.Usuario;
import Logica.Voto;

import java.util.List;

public class ControladoraPersistencia {

    // instanciamos los controladores.

    UsuarioController usuariojpa = new UsuarioController();
    EleccionController eleccionController = new EleccionController();
    CandidatoController candidatoController = new CandidatoController();
    VotoController votoController = new VotoController();

    // aqui van los metodos para cada accion
    // Usuario
    public void editarUsuario(Usuario usuarioCorrunt) {
        usuariojpa.edit(usuarioCorrunt);
    }
    public void crearUsuario(Usuario usuario) {
       usuariojpa.create(usuario);
    }

    public void eliminarUsuario(int id){
        usuariojpa.delete(id);
    }

    public List<Usuario> getUsuarios(){
        return usuariojpa.findUsuarioEntities();
    }



    // Eleccion
    public void crerEleccion(Eleccion eleccion) {
        eleccionController.createEleccion(eleccion);
    }
    public List<Eleccion> getEleccion(){
        return eleccionController.findEleccionEntities();
    }

    public void eliminarEleccion(int idEleccion) throws Exception {
        eleccionController.deleteEleccion(idEleccion);
    }

    public Eleccion findEleccion(int id) {
        return eleccionController.findEleccion(id);
    }

    public void editEleccion(Eleccion ele) {
        eleccionController.editEleccion(ele);
    }

    public void createCandit(Candidato candidato) {
        candidatoController.createCandidato(candidato);
    }

    public List<Candidato> traerCandidatos() {
        return candidatoController.findCandidatoEntities();
    }

    public Candidato finCandidato(int id) {
        return candidatoController.findCandidato(id);
    }


    public void createVot(Voto votoUsuario) {
        votoController.createVoto(votoUsuario);
    }

    public List<Voto> getVotos() {
        return votoController.findVotoEntities();
    }

    public Usuario findUsuario(int idUsuario) {
        return usuariojpa.findUsuario(idUsuario);
    }

    public List<String> getCandidatosPorEleccion(int idEleccion) {
        return candidatoController.obtenerCandidatosPorEleccion(idEleccion);
    }

    public HashMap<String, Integer> getCandidatosVotos(int id_eleccion) {
        return eleccionController.canEleVot(id_eleccion);
    }

    public HashMap<String, Integer> getCandidatosVotos() {
        return eleccionController.canEleVot();
    }
}
