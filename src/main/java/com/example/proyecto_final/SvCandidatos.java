package com.example.proyecto_final;


import DS.HashMap;
import Logica.Candidato;
import Logica.ControladoraJPA;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SvCandidatos", value = "/SvCandidatos")
public class SvCandidatos extends HttpServlet {

    private HashMap<Integer, List<String>> dicc = new HashMap<>();
    private List<String> listaCandidatos = new ArrayList<>();

    ControladoraJPA control = new ControladoraJPA();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String fechaRegistro = request.getParameter("fechaCandidato");
        String nombreCandidato = request.getParameter("nombreCandidato");
        String rolCandidato = request.getParameter("rolCandidato");
        int idEleccionCandidato = Integer.parseInt(request.getParameter("id_eleccion"));

        listaCandidatos.add(nombreCandidato);
        dicc.put(idEleccionCandidato, listaCandidatos);



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        try {
            fecha = dateFormat.parse(fechaRegistro);
            Candidato candidato = new Candidato(fecha,nombreCandidato,rolCandidato);
            control.crearCandidato(candidato);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Pagina_Principal.jsp");
    }
}
