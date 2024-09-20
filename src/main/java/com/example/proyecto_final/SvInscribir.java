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

        int ele_id = Integer.parseInt(request.getParameter("ele_id"));
        HttpSession session = request.getSession();
        Usuario usuarioCurrent = (Usuario) session.getAttribute("usuario");
        usuarioCurrent.setUsu_ele_id(ele_id);
        control.editUsuario(usuarioCurrent);
        response.sendRedirect("Pagina_Principal.jsp");
    }
}
