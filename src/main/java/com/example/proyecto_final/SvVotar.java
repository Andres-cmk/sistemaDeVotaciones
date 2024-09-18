package com.example.proyecto_final;


import DS.HashMap;
import DS.myQueque;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SvVotar", value = "/SvVotar")
public class SvVotar extends HttpServlet {

    private static HashMap<Integer, Integer> usuario_candidato = new HashMap<>();
    private static myQueque colaVotos = new myQueque();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession();
        int id_usuario = (Integer) httpSession.getAttribute("usu_id");
        int id_eleccion = (Integer) httpSession.getAttribute("ele_id");
        int id_candidato = Integer.parseInt(request.getParameter("candidato"));
        //usuario_candidato.put(id_usuario, id_candidato);

        System.out.println("id_usuario: " + id_usuario);
        System.out.println("id_eleccion: " + id_eleccion);
        System.out.println("id_candidato: " + id_candidato);
    }
}
