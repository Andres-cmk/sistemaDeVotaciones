package com.example.proyecto_final;

import DS.HashMap;
import DS.MaxHeap;
import Logica.Candidato;
import Logica.ControladoraJPA;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SvGetGanador", value = "/SvGetGanador")
public class SvGetGanador  extends HttpServlet {

    ControladoraJPA control = new ControladoraJPA();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonObject = new JsonObject();
        Integer votos;
        MaxHeap maxHeap = new MaxHeap();
        int id_eleccion = Integer.parseInt(request.getParameter("id"));
        List<Candidato> lista = control.getCandidatos();

        List<String> listNameCandidatos = lista.stream()
                .filter(x -> x.getCad_ele_id() == id_eleccion)
                .map(Candidato::getCan_nombre)
                .collect(Collectors.toList());


        System.out.println(listNameCandidatos);
        HashMap<String, Integer> map = control.consultaCandidato(id_eleccion); // relaciona los nombre con la cantidad de votos

        for (String i: listNameCandidatos){
            votos = map.get(i);
            if(votos == null){
                votos = 0;
                map.put(i, votos);
            }
            maxHeap.insert(map.get(i));
        }

        maxHeap.printHeap();

        String ganador = map.getKeyByValue(maxHeap.getMax());

        if (ganador != null) {
            System.out.println("El ganador de la eleccion es: " + ganador + " con " + map.get(ganador) + " votos");
            jsonObject.addProperty("ganador", ganador);
        } else {
            System.out.println("No se pudo determinar el ganador.");
            jsonObject.addProperty("ganador", "No se pudo determinar el ganador.");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }
}
