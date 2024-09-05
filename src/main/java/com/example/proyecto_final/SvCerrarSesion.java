package com.example.proyecto_final;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SvCerrarSesion", value = "/SvCerrarSesion")

public class SvCerrarSesion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String accion = request.getParameter("param");
        HttpSession session = request.getSession();
        if(accion.equals("salir")){
            session.invalidate();
            request.setAttribute("mensaje","Session finalizada");
            response.sendRedirect("login.jsp");
        }
    }
}
