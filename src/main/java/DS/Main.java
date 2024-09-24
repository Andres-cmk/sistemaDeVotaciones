package DS;

import Logica.ControladoraJPA;
import Logica.Eleccion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    static ControladoraJPA control = new ControladoraJPA();
    public static void main(String[] args) {
        int id_eleccion = 1;
        Eleccion eleccion = control.getEleccion(1);
        Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        json.toJson(eleccion);
        System.out.println(json);
    }
}
