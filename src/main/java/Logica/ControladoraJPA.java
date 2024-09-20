package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraJPA{

    ControladoraPersistencia persistencia = new ControladoraPersistencia();

    public void crearUsuario(Usuario usuario){
        persistencia.crearUsuario(usuario);
    }

    public void editUsuario(Usuario usuarioCorrunt) {
        persistencia.editarUsuario(usuarioCorrunt);
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

    public Eleccion getEleccion(int id){
        return persistencia.findEleccion(id);
    }

    public void editEleccion(Eleccion ele){persistencia.editEleccion(ele);}

    public void crearCandidato(Candidato candidato) {
        persistencia.createCandit(candidato);
    }

    public List<Candidato> getCandidatos() {
        return persistencia.traerCandidatos();
    }

    public Candidato getCandidato(int id){
        return persistencia.finCandidato(id);
    }


    public void createVoto(Voto votoUsuario) {
        persistencia.createVot(votoUsuario);
    }

    public List<Voto> getVotos() {
        return persistencia.getVotos();
    }

    public Usuario getUsuario(int idUsuario) {
        return persistencia.findUsuario(idUsuario);
    }
}
