<%@ page import="Logica.ControladoraJPA" %>
<%@ page import="java.util.List" %>
<%@ page import="Logica.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interfaz de Elecciones</title>
    <link rel="stylesheet" href="estilos/resultados.css">
    <link rel="stylesheet" href="estilos/elecciones.css">
    <link rel="stylesheet" href="estilos/usuarios.css">
    <link rel="stylesheet" href="estilos/footer.css">
    <script src="./javaScript/script.js" defer></script>
    <script src="https://kit.fontawesome.com/8234d7916b.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="imagenes/server-solid.svg" type="image/x-icon">
</head>

<!--Validacion-->
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    if(usuario == null){
        response.sendRedirect("loginError.jsp");
    }
%>
<body>

<!-- Barra de Navegación -->
<header>
    <div class="logo">
        <img src="imagenes/vote.png" alt="logo votaciones">
        <a data-target="#Principal">Sistema de votaciones</a>
    </div>
    <nav>
        <ul>
            <li><a data-target="#resultados">Resultados</a></li>
            <li><a data-target="#usuarios">Usuarios</a></li>
            <li><a data-target="#eleccion">Elecciones</a></li>
            <li><a data-target="#candidatos">Candidatos</a></li>
            <li><a data-target="#votantes">Votantes</a></li>
            <li><a data-target="#reportes">Reportes</a></li>
            <li><a href="SvCerrarSesion?param=salir">Salir de Session</a></li>
        </ul>
    </nav>
</header>

<!-- Contenedor Principal -->
<div class="container">
    <div data-content id="Principal">
        <div class="promo"><h3>Bienvenido a nuestro sistema de votaciones</h3></div>
        <!--
        <video muted autoplay loop controls>
            <source src="./videos/Fndo.mp4" type="video/mp4">
        </video>
        --->
    </div>

    <div data-content id="resultados">
        <h1>Resultados</h1>
        <!-- Sección de Selección de Elección -->
        <section class="seleccion-eleccion">
            <h2>Seleccione una elección</h2>
            <form id="form-eleccion">
                <label for="elecciones">Elecciones:</label>
                <select id="elecciones">
                    <option value="">-- Seleccione --</option>
                    <!-- Opciones dinámicas -->
                </select>
                <button type="submit">Buscar Resultados</button>
            </form>
        </section>

        <!-- Sección de Resultados -->
        <section class="resultados">
            <h2>Resultados</h2>
            <div id="resultados">
                <!-- Resultados dinámicos -->
            </div>
        </section>
    </div>

    <div data-content id="usuarios">
        <h1>Usuarios</h1>
        <h2>Lista de usuarios</h2>
        <div class="table-options">
            <label for="records">Mostrar</label>
            <select id="records">
                <option value="10">10</option>
            </select>
            <label for="records">Registros</label>
            <input type="text" id="search" placeholder="Buscar">
        </div>
        <table>
            <thead>
            <tr>
                <th>Documento de identidad</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Correo</th>
                <th>Rol</th>


            </tr>
            </thead>
            <tbody>

            <%
                ControladoraJPA controladoraJPA = new ControladoraJPA();
                List<Usuario> usuarioList = controladoraJPA.getUsuarios();
            %>
            <%for (Usuario usu: usuarioList){
                if(!usu.getUsu_rol().equalsIgnoreCase("administrador")){
            %>
            <tr>
                <td><%= usu.getUsu_NumeroDocumento()%></td>
                <td><%= usu.getUsu_nombre()%></td>
                <td><%= usu.getUsu_apellido()%></td>
                <td><%= usu.getUsu_correo()%></td>
                <td><%= usu.getUsu_rol()%></td>
            </tr>
            <%}%>
            <%}%>
            </tbody>
        </table>


    </div>

    <div data-content id="eleccion">
        <h1>Elecciones</h1>
        <h2>Lista de elecciones</h2>
        <div class="table-options">
            <label for="records">Mostrar</label>
            <select id="records">
                <option value="10">10</option>
            </select>
            <label for="records">Registros</label>
            <input type="text" id="search" placeholder="Buscar">
        </div>
        <table>
            <thead>
            <tr>
                <th>Descripcion</th>
                <th>Cargo</th>
                <th>Fecha registrado</th>
                <th>Creado</th>
                <th>Acciones</th>
            </tr>
            </thead>
        </table>
        <div class="Pagination">
            <button class="previous">Anterior</button>
            <span>1</span>
            <button class="next">Siguiente</button>
        </div>
    </div>

    <div data-content id="candidatos">
        <h1>Candidatos</h1>
    </div>

    <div data-content id="votantes">
        <h1>Votatntes</h1>
    </div>

    <div data-content id="reportes">
        <h1>Reportes</h1>
    </div>
</div>
</body>

<footer id="footer">
    <div class="contain">
        <div class="footer-row">
            <div class="footer-links">
                <h4>Creadores</h4>
                <ul>
                    <li><a href="https://www.youtube.com/watch?v=D1NdGBldg3w">Andres Ramirez</a></li>
                    <li><a href="https://www.youtube.com/watch?v=hMS8RtYVouc">Frank Olmos</a></li>
                    <li><a href="https://www.youtube.com/watch?v=esSMPZIs6rM">Misael Florez</a></li>
                    <li><a href="https://www.youtube.com/watch?v=TcePkwagNFA">Diego Pinzon</a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</html>

