package com.example.proyecto_final;


import DS.HashMap;
import DS.VoteProcessing;
import DS.myQueque;
import Logica.ControladoraJPA;
import Logica.Usuario;
import Logica.Voto;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "SvVotar", value = "/SvVotar")
public class SvVotar extends HttpServlet {

    HashMap<Integer, Integer> map = new HashMap<>();

    ControladoraJPA control = new ControladoraJPA();
    private static myQueque colaVotos = new myQueque();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession httpSession = request.getSession();

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        int id_candidato = Integer.parseInt(request.getParameter("candidato"));

        Voto votoUsuario = new Voto(usuario, control.getEleccion(usuario.getUsu_ele_id()), control.getCandidato(id_candidato), new Date());

        if(map.hasKey(usuario.getUsu_id())){
            map.put(usuario.getUsu_id(), map.get(usuario.getUsu_id()) + 1);
        }else {
            map.put(usuario.getUsu_id(), 1);
        }
        colaVotos.enqueque(votoUsuario);

        try {
            JsonObject result = VoteProcessing.processing(map,colaVotos, usuario.getUsu_id());
            String status = result.get("status").getAsString();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if(status.equals("aceptado")){
                control.createVoto(votoUsuario);
                response.getWriter().write(result.toString());
            }else if(status.equals("rechazado")){
                response.getWriter().write(result.toString());
            }
        } catch (Exception e) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", "error");
            jsonObject.addProperty("message", e.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toString());

        }
    }
}
