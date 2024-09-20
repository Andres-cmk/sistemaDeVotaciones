package DS;

import Logica.ControladoraJPA;
import Logica.Voto;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteProcessing {

    ControladoraJPA control = new ControladoraJPA();
    int id_usuario;
    myQueque colaVotos;


    public VoteProcessing(myQueque cola, int id_usuario){
        this.colaVotos = cola;
        this.id_usuario = id_usuario;
    }

    public boolean processingVote(Voto voto, HashMap<Integer, Integer> map){
        List<Voto> list = control.getVotos();
        List<Voto> listaFinal = list.stream().filter(i -> i.getUsuario().getUsu_id() == voto.getUsuario().getUsu_id()).collect(Collectors.toList());
        if(map.get(id_usuario) > 1 && listaFinal != null) return false;
        return true;
    }

    public static JsonObject processing(HashMap<Integer, Integer> map,myQueque votos, int id_usuario) throws InterruptedException {
        VoteProcessing voteProcessing = new VoteProcessing(votos, id_usuario);
        JsonObject json = new JsonObject();
        Voto voto = votos.poll();
        if(voteProcessing.processingVote(voto, map)){
            TimeUnit.SECONDS.sleep(1);
            json.addProperty("status","aceptado");
            return json;
        }else {
            TimeUnit.SECONDS.sleep(1);
            json.addProperty("status", "rechazado");
            return json;
        }
    }
}
