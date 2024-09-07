package com.example.proyecto_final;

import Logica.ControladoraJPA;
import Logica.Eleccion;
import Logica.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvVerificar", value = "/SvVerificar")
public class SvVerificar extends HttpServlet {

    private final ControladoraJPA control = new ControladoraJPA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numeroDocumento = Integer.parseInt(request.getParameter("documet"));
        String email = request.getParameter("correo");
        String password = request.getParameter("password");
        // traemos la sesion de la sesion del usuario.
        HttpSession session = request.getSession(true);
        // traemos la session para la lista de usuarios.
        HttpSession misession1 = request.getSession(true);
        // traemos la session para la lista de eleccion.
        HttpSession miSession2 = request.getSession(true);

        List<Eleccion> eleccionList = control.getELeccion();
        List<Usuario> listaUsuarios = control.getUsuarios();
        boolean usuarioEncontrado = false;

        if (listaUsuarios != null) {
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getUsu_NumeroDocumento() == numeroDocumento &&
                        usuario.getUsu_correo().equals(email) &&
                        usuario.getUsu_password().equals(password)) {
                    // Usuario encontrado
                    session.setAttribute("usuario", usuario);
                    misession1.setAttribute("ListaUsuarios", listaUsuarios);
                    miSession2.setAttribute("listaEleccion", eleccionList);
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
