package com.example.proyecto_final;



import DS.HashMap;
import Logica.ControladoraJPA;
import Logica.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SvInscribir", value = "/SvInscribir")
public class SvInscribir extends HttpServlet {

    ControladoraJPA control = new ControladoraJPA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int usu_id = Integer.parseInt(request.getParameter("usu_id"));
        int ele_id = Integer.parseInt(request.getParameter("ele_id"));
        HttpSession session = request.getSession(true);
        Usuario usuarioCurrent = (Usuario) request.getSession().getAttribute("usuario");
        usuarioCurrent.setUsu_ele_id(ele_id);
        control.editUsuario(usuarioCurrent);

        session.setAttribute("usu_id", usu_id);
        session.setAttribute("ele_id", ele_id);


        response.sendRedirect("Pagina_Principal.jsp");
    }
}
