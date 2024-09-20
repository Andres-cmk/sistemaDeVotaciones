package com.example.proyecto_final;

import DS.HashMap;
import Logica.ControladoraJPA;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.List;


@WebServlet(name = "SvResultados", value = "/SvResultados")
public class SvResultados extends HttpServlet {
    ControladoraJPA control = new ControladoraJPA();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        int idEleccion = Integer.parseInt(request.getParameter("valor"));

        List<String> lista = control.getCandidatosEleccion(idEleccion);

        HashMap<String, Integer> map = control.consultaCandidato();

        session.setAttribute("map", map);

        JsonObject jsonObject = new JsonObject();

        for (String i: lista){
            jsonObject.addProperty(i, map.get(i));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }
}
