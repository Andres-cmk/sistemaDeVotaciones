package com.example.proyecto_final;

import java.io.*;
import Logica.ControladoraJPA;
import Logica.Usuario;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;

@WebServlet(name = "SvUsuarios", value = "/SvUsuarios")
public class SvUsuarios extends HttpServlet {


     final ControladoraJPA control = new ControladoraJPA();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int NumeroDocumento = Integer.parseInt(request.getParameter("documento"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rolUsuario = request.getParameter("rol");

            List<Usuario> listaUsuarios = control.getUsuarios();
            boolean usuarioRegistrado = true;

            if (listaUsuarios != null){
                for (Usuario usu: listaUsuarios){
                    if(usu.getUsu_correo().equals(email) || usu.getUsu_password().equals(password)) {
                        usuarioRegistrado = false;
                        break;
                    }
                }
            }


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if(usuarioRegistrado){
                Usuario usuario = new Usuario(nombre,apellido, email, password, NumeroDocumento, rolUsuario);
                control.crearUsuario(usuario);
                response.getWriter().write("{\"status\":\"success\"}");
            }else {
                response.getWriter().write("{\"status\":\"valueRepet\"}");
            }

        }catch (Exception e){
            response.getWriter().write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");

        }
    }

    public void destroy() {
    }
}