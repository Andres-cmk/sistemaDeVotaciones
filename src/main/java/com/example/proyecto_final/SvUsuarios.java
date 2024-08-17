package com.example.proyecto_final;

import java.io.*;
import Logica.ControladoraJPA;
import Logica.Usuario;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;

@WebServlet(name = "SvUsuarios", value = "/SvUsuarios")
public class SvUsuarios extends HttpServlet {

    ControladoraJPA control = new ControladoraJPA();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int NumeroDocumento = Integer.parseInt(request.getParameter("documento"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rolUsuario = request.getParameter("rolEstudiante");

            List<Usuario> listaUsuarios = control.getUsuarios();

            if (listaUsuarios != null){
                for (Usuario usu: listaUsuarios){
                    if(usu.getUsu_correo().equals(email) || usu.getUsu_password().equals(password)){
                        response.getWriter().write("{\"status\":\"valueRepet\"}");
                        return;
                    }
                }
            }
            control.crearUsuario(new Usuario(nombre,apellido, email, password, NumeroDocumento, rolUsuario));
            response.getWriter().write("{\"status\":\"success\"}");


        }catch (Exception e){
            response.getWriter().write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    public void destroy() {
    }
}