package com.example.proyecto_final;

import DS.HashMap;
import Logica.ControladoraJPA;
import Logica.Usuario;
import com.google.gson.JsonObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvVerificar", value = "/SvVerificar")
public class SvVerificar extends HttpServlet {

    private ControladoraJPA control = new ControladoraJPA();
    private HashMap<Integer, Integer> intentosLogin = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        Usuario usuarioActual = null;
        JsonObject json = new JsonObject();
        int numeroDocumento = Integer.parseInt(request.getParameter("documet"));
        String email = request.getParameter("correo");
        String password = request.getParameter("password");
        // traemos la sesion de la sesion del usuario.
        HttpSession session = request.getSession(true);
        // traemos la session para la lista de usuarios.
        HttpSession misession1 = request.getSession(true);

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
                    usuarioActual = usuario;
                    usuarioEncontrado = true;
                    break;
                }
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if(usuarioActual != null){
            if(usuarioActual.getUsu_rol().equals("administrador")){
                session.setAttribute("usuario", usuarioActual);
                json.addProperty("status", "success");
                response.getWriter().write(json.toString());
                return;
            }
            if(intentosLogin.hasKey(usuarioActual.getUsu_id())){
                intentosLogin.put(usuarioActual.getUsu_id(), intentosLogin.get(usuarioActual.getUsu_id()) + 1);
            }else {
                intentosLogin.put(usuarioActual.getUsu_id(),1);
            }
            if(intentosLogin.get(usuarioActual.getUsu_id()) > 1){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                json.addProperty("status","already_logged_in");
                response.getWriter().write(json.toString());
                return;
            }
        }

        if (usuarioEncontrado) {
            // Usuario válido
            json.addProperty("status","success");
            response.getWriter().write(json.toString());
        } else {
            // Usuario no válido
            json.addProperty("status","error");
            response.getWriter().write(json.toString());
        }
    }
}
