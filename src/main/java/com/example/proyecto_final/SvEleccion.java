package com.example.proyecto_final;

import Logica.ControladoraJPA;
import Logica.Eleccion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SvEleccion", value = "/SvEleccion")
public class SvEleccion extends HttpServlet {

    ControladoraJPA controlPersistencia = new ControladoraJPA();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("estadoEleccion");
        String fechaIn = request.getParameter("fechainicial");
        String fechaFi = request.getParameter("fechafinal");
        String nombreEleccion = request.getParameter("nombreEleccion");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaFinal = dateFormat.parse(fechaFi);
            Date fechaInicio = dateFormat.parse(fechaIn);
            
            controlPersistencia.crearEleeccion(new Eleccion(nombreEleccion,fechaInicio,fechaFinal,estado));
            response.sendRedirect("Pagina_Principal.jsp");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
