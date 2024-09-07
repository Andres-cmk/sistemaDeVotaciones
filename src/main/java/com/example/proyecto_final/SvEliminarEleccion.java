package com.example.proyecto_final;

import Logica.ControladoraJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SvEliminarEleccion", value = "/SvEliminarEleccion")
public class SvEliminarEleccion extends HttpServlet {
    ControladoraJPA control = new ControladoraJPA();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEleccion = Integer.parseInt(req.getParameter("eliminarEleccion"));
        try {
            control.removeEleccion(idEleccion);
            resp.sendRedirect("Pagina_Principal.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
