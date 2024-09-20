package com.example.proyecto_final;

import DS.HashMap;
import DS.MaxHeap;
import Logica.ControladoraJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvGetGanador", value = "/SvGetGanador")
public class SvGetGanador  extends HttpServlet {

    ControladoraJPA control = new ControladoraJPA();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaxHeap maxHeap = new MaxHeap();
        int id_eleccion = Integer.parseInt(request.getParameter("id"));
        HashMap<String, Integer> map = control.consultaCandidato();
        List<String> list = control.getCandidatosEleccion(id_eleccion);
        for (String i: list) maxHeap.insert(map.get(i));
        maxHeap.printHeap();
        String nameGanador = map.getKeyByValue(maxHeap.getMax());
        System.out.println("El ganador es: " + nameGanador);
        HttpSession session = request.getSession(true);
        session.setAttribute("name Ganador", nameGanador);
    }
}
