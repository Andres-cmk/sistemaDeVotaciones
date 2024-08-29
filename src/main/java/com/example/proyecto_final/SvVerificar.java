package com.example.proyecto_final;

import Logica.ControladoraJPA;
import Logica.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvVerificar", value = "/SvVerificar")
public class SvVerificar extends HttpServlet {

    private final ControladoraJPA control = new ControladoraJPA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numeroDocumento = Integer.parseInt(request.getParameter("documet"));
        String email = request.getParameter("correo");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);

        List<Usuario> listaUsuarios = control.getUsuarios();
        boolean usuarioEncontrado = false;

        if (listaUsuarios != null) {
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getUsu_NumeroDocumento() == numeroDocumento &&
                        usuario.getUsu_correo().equals(email) &&
                        usuario.getUsu_password().equals(password)) {
                    // Usuario encontrado
                    session.setAttribute("usuario", usuario);
                    usuarioEncontrado = true;
                    break;
                }
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (usuarioEncontrado) {
            // Usuario válido
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            // Usuario no válido
            response.getWriter().write("{\"status\":\"error\"}");
        }
    }
}
