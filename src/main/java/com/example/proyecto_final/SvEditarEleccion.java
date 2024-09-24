package com.example.proyecto_final;

import Logica.ControladoraJPA;
import Logica.Eleccion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SvEditarEleccion", value = "/SvEditarEleccion")
public class SvEditarEleccion extends HttpServlet {

    ControladoraJPA controlPersistencia = new ControladoraJPA();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String estado = request.getParameter("estadoEleccion");
        String fechaIn = request.getParameter("fechainicial");
        String fechaFi = request.getParameter("fechafinal");
        String nombreEleccion = request.getParameter("nombreEleccion");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaFinal = dateFormat.parse(fechaFi);
            Date fechaInicio = dateFormat.parse(fechaIn);

            Eleccion ele = (Eleccion) request.getSession().getAttribute("eleccionEdit");
            ele.setEle_estado(estado);
            ele.setEle_fechaInicio(fechaInicio);
            ele.setEle_fechaFinal(fechaFinal);
            ele.setEle_nombre(nombreEleccion);

            controlPersistencia.editEleccion(ele);
            response.sendRedirect("Pagina_Principal.jsp");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int idEleccion = Integer.parseInt(request.getParameter("ele_id"));

        HttpSession session = request.getSession(true);

        Eleccion eleccion = controlPersistencia.getEleccion(idEleccion);

        session.setAttribute("eleccionEdit", eleccion);

        Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String eleccionJson = json.toJson(eleccion);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(eleccionJson);

    }
}

