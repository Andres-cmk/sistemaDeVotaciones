<%@ page import="Logica.ControladoraJPA" %>
<%@ page import="java.util.List" %>
<%@ page import="Logica.Usuario" %>
<%@ page import="Logica.Eleccion" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interfaz de Elecciones</title>
    <link rel="stylesheet" href="styles/resultados.css">
    <link rel="stylesheet" href="styles/elecciones.css">
    <link rel="stylesheet" href="styles/usuarios.css">
    <link rel="stylesheet" href="styles/footer.css">
    <script src="./javaScript/script.js" defer></script>
    <script src="https://kit.fontawesome.com/8234d7916b.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="imagenes/server-solid.svg" type="image/x-icon">
</head>

<!--Validacion-->
<%
     Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    if(usuario == null){
        response.sendRedirect("loginError.jsp");
        return;
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
            <li><a href="SvCerrarSesion?param=salir">Salir</a></li>
        </ul>
    </nav>
</header>

<!-- Contenedor Principal -->
<!-------------------------------------------------------------------------------------------------------------------------------------------->
<div class="container">
    <div data-content id="Principal">
        <div class="promo"><h3>Bienvenido a nuestro sistema de votaciones</h3></div>
        <!--
        <video muted autoplay loop controls>
            <source src="./videos/Fndo.mp4" type="video/mp4">
        </video>
        --->
    </div>

 <!------------------------------------------------------------------------------------------------------------------------------------------->

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

<!----------------------------------------------------------------------------------------------------------------------------------------->
        <!-- Sección de Resultados -->
        <section class="resultados">
            <h2>Resultados</h2>
            <div id="">
                <!-- Resultados dinámicos -->
            </div>
        </section>
    </div>

    <!----------------------------------------------------------------------------------------------------------------------------------->

    <div data-content id="usuarios">
        <h1>Usuarios</h1>
        <h2>Lista de usuarios</h2>


        <table>

            <%
                ControladoraJPA controladoraJPA = new ControladoraJPA();
                List<Usuario> listaUSuarios = controladoraJPA.getUsuarios();
                 usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario.getUsu_rol().equals("administrador")){
            %>

            <thead>
            <tr>
                <th>Documento de identidad</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Correo</th>
                <th>Rol</th>
            </tr>
            </thead>
            <%
                for (Usuario usu: listaUSuarios){
            %>
                <tbody>
                <tr>
                    <td><%= usu.getUsu_NumeroDocumento()%></td>
                    <td><%= usu.getUsu_nombre()%></td>
                    <td><%= usu.getUsu_apellido()%></td>
                    <td><%= usu.getUsu_correo()%></td>
                    <td><%= usu.getUsu_rol()%></td>
                </tr>
                </tbody>

            <%}%>
            <%} else {%>
            <thead>
            <tr>

                <th>Nombre</th>
                <th>Apellido</th>
                <th>Correo</th>
                <th>Rol</th>
            </tr>
            </thead>
                <%
                    for (Usuario usu: listaUSuarios){
                        if(!usu.getUsu_rol().equals("administrador")){
                %>
            <tbody>
            <tr>
                <td><%= usu.getUsu_nombre()%></td>
                <td><%= usu.getUsu_apellido()%></td>
                <td><%= usu.getUsu_correo()%></td>
                <td><%= usu.getUsu_rol()%></td>
            </tr>
            </tbody>

            <%}%>
            <%}%>
            <%}%>
        </table>
    </div>

    <!----------------------------------------------------------------------------------------------------------------------------->

    <div data-content id="eleccion">
        <h1>Elecciones</h1>
        <h2>Lista de elecciones</h2>
        <div class="table-options">

            <script>
            <%if (usuario.getUsu_rol().equals("administrador")){%>
                document.getElementById("openModalBtn").style.display = "";
            <%} else{%>
                document.getElementById("openModalBtn").style.display = "none";
            <%}%>
            </script>

        </div>

        <!--Tabla de Elecciones-->

        <table>

            <%
                List<Eleccion> eleccionList = controladoraJPA.getELeccion();
                if(usuario.getUsu_rol().equals("administrador")){
            %>

            <thead>
            <tr>
                <th>Estado de Eleccion</th>
                <th>Fecha-Inicio</th>
                <th>Fecha-Final</th>
                <th>Nombre-Eleccion</th>
            </tr>
            </thead>

            <%
                for (Eleccion ele: eleccionList){
            %>

                <tbody>
                    <tr>
                        <td><%=ele.getEle_estado()%></td>
                        <td><%=ele.getEle_fechaInicio()%></td>
                        <td><%=ele.getEle_fechaFinal()%></td>
                        <td><%=ele.getEle_nombre()%></td>
                        <td>
                            <div class="Pagination" style="display: flex; gap: 10px">
                                <form id="formEditEleccion" method="POST" action="">
                                    <button type="submit" style="background-color: #14213d">Editar  <i class="fa-solid fa-pencil"></i></button>
                                </form>

                                <form id="formRemoveEleccion" method="POST">
                                    <button type="submit" style="background-color: #b7b3b3">Eliminar  <i class="fa-solid fa-trash"></i></button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </tbody>

            <%}%>
            <%} else {%>

            <thead>
            <tr>
                <th>Estado de Eleccion</th>
                <th>Fecha-Inicio</th>
                <th>Fecha-Final</th>
                <th>Nombre-Eleccion</th>
                <th>Incripccion</th>
            </tr>
            </thead>

            <%
                for (Eleccion ele: eleccionList){
            %>

            <tbody>
            <tr>
                <td><%=ele.getEle_estado()%></td>
                <td><%=ele.getEle_fechaInicio()%></td>
                <td><%=ele.getEle_fechaFinal()%></td>
                <td><%=ele.getEle_nombre()%></td>
                <td>
                    <div class="Pagination" style="display: flex; gap: 10px">
                        <form id="inscribirPerson">
                            <button type="reset">Incribirse  <i class="fa-solid fa-check"></i> </button>
                        </form>
                    </div>
                </td>
            </tr>
            </tbody>
            <%}%>
            <%}%>
        </table>
    </div>


    <!-------------------------------------------------------------------------------------------------------------------------->

    <!--Modal (formulario para agregar eleccion)-->

    <!-- El fondo oscuro (overlay) -->
    <div class="modal-overlay" id="modalOverlay"></div>

    <!-- El contenido de la ventana modal con formulario -->
    <div class="modal" id="myModal" style="display: none">
        <span class="close-btn" id="closeModalBtn">&times;</span>
        <h2>Formulario de Registro</h2>
        <form action="SvEleccion" method="POST" id="formuEleccion" autocomplete="off" >
            <label for="estado">Estado:</label>
            <input type="text" id="estado" name="estadoEleccion" required>

            <label for="fechaInicial">Fecha Inicial:</label>
            <input type="date" id="fechaInicial" name="fechainicial" required>

            <label for="fechaFinal">Fecha Final:</label>
            <input type="date" id="fechaFinal" name="fechafinal" required>

            <label for="nombreEleccion">Nombre Eleccion</label>
            <input type="text" id="nombreEleccion" name="nombreEleccion" required>

            <button type="submit">Registrar</button>
            
        </form>
    </div>

    <!--Falta por implementar-->
    <!------------------------------------------------------------------------------------------------------------------------------->
    <div data-content id="candidatos">
        <h1>Candidatos</h1>
    </div>

    <div data-content id="votantes">
        <h1>Votatntes</h1>
    </div>

    <div data-content id="reportes">
        <h1>Reportes</h1>
    </div>

    <!---------------------------------------------------------------------------------------------------------------------------------------->
</div>
<script src="javaScript/modal.js"></script>
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