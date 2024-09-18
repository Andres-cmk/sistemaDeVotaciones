package com.example.proyecto_final;


import Logica.Candidato;
import Logica.ControladoraJPA;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SvCandidatos", value = "/SvCandidatos")
public class SvCandidatos extends HttpServlet {

    ControladoraJPA control = new ControladoraJPA();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession(true);
        String fechaRegistro = request.getParameter("fechaCandidato");
        String nombreCandidato = request.getParameter("nombreCandidato");
        String rolCandidato = request.getParameter("rolCandidato");
        int idEleccionCandidato = Integer.parseInt(request.getParameter("id_eleccion"));


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        try {
            fecha = dateFormat.parse(fechaRegistro);
            Candidato candidato = new Candidato(fecha,nombreCandidato,rolCandidato);
            candidato.setCad_ele_id(idEleccionCandidato);
            control.crearCandidato(candidato);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Pagina_Principal.jsp");
    }
}
