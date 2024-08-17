package com.example.proyecto_final;


import Logica.ControladoraJPA;
import Logica.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SvVerificar", value = "/SvVerificar")
public class SvVerificar extends HttpServlet{

    ControladoraJPA control = new ControladoraJPA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        int numeroDocumento = Integer.parseInt(request.getParameter("documet"));
        String email = request.getParameter("correo");
        String password = request.getParameter("password");


        List<Usuario> listaUsuarios = control.getUsuarios();

        if(listaUsuarios!=null){
            for (Usuario usuario: listaUsuarios){
                if(usuario.getUsu_NumeroDocumento() == numeroDocumento && usuario.getUsu_correo().equals(email) && usuario.getUsu_password().equals(password)){
                    response.sendRedirect("Pagina_Principal.jsp");
                }
            }
        }
    }
}