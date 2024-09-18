<%@ page import="java.util.List" %>
<%@ page import="Logica.Usuario" %>
<%@ page import="Logica.Eleccion" %>
<%@ page import="Logica.ControladoraJPA" %>
<%@ page import="Logica.Candidato" %>
<%@ page import="DS.HashMap" %>
<%@ page import="java.util.stream.Collectors" %>
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
    <link rel="stylesheet" href="styles/candidatos.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://kit.fontawesome.com/8234d7916b.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="shortcut icon" href="imagenes/server-solid.svg" type="image/x-icon">
</head>

<!--Validacion-->
<%
     Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    if(usuario == null){
        response.sendRedirect("loginError.jsp");
        return;
    }

    ControladoraJPA control = new ControladoraJPA();
    List<Usuario> listaUSuarios = (List<Usuario>) request.getSession().getAttribute("ListaUsuarios");
    List<Candidato> listaCandidatos = control.getCandidatos();
    List<Candidato> listaFinalCandidatos = null;

    if(usuario.getUsu_ele_id() != 0){
        listaFinalCandidatos = listaCandidatos.stream().filter(x -> x.getCad_ele_id() == usuario.getUsu_ele_id()).collect(Collectors.toList());
    }

%>

<body>

<%
    boolean bandera = false;
    if (usuario.getUsu_rol().equals("administrador")){
        bandera = true;
    }
%>

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
                    <%
                        List<Eleccion> listaEleccion = control.getELeccion();
                        for (Eleccion ele: listaEleccion){
                    %>
                        <option value="<%=ele.getEle_nombre()%>"><%=ele.getEle_nombre()%></option>
                    <%}%>

                </select>
                <button type="submit">Buscar Resultados</button>
            </form>
        </section>

        <!-- Sección de Resultados -->
        <div class="resultados">
            <h2>Resultados</h2>
                <!-- Resultados dinámicos -->
            <canvas id="pieChart"></canvas>
        </div>
    </div>




    <!----------------------------------------------------------------------------------------------------------------------------------->

    <div data-content id="usuarios">
        <h1>Usuarios</h1>
        <h2>Lista de usuarios</h2>


        <table>
            <%if (bandera){%>

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
                for (Usuario usu: listaUSuarios){
            %>

                <tr>
                    <td><%= usu.getUsu_NumeroDocumento()%></td>
                    <td><%= usu.getUsu_nombre()%></td>
                    <td><%= usu.getUsu_apellido()%></td>
                    <td><%= usu.getUsu_correo()%></td>
                    <td><%= usu.getUsu_rol()%></td>
                </tr>


            <%}%>
            </tbody>
            <%} else {%>
            <thead>
            <tr>

                <th>Nombre</th>
                <th>Apellido</th>
                <th>Correo</th>
                <th>Rol</th>
            </tr>
            </thead>
            <tbody>
                <%
                    for (Usuario usu: listaUSuarios){
                        if(!bandera && !(usu.getUsu_rol().equals("administrador"))){
                %>

            <tr>
                <td><%= usu.getUsu_nombre()%></td>
                <td><%= usu.getUsu_apellido()%></td>
                <td><%= usu.getUsu_correo()%></td>
                <td><%= usu.getUsu_rol()%></td>
            </tr>


            <%}%>
            <%}%>
            <%}%>
            </tbody>
        </table>
    </div>

    <!----------------------------------------------------------------------------------------------------------------------------->

    <div data-content id="eleccion">

        <h1>Elecciones</h1>
        <h2>Lista de elecciones</h2>
        <div class="table-options">

            <button  id="editar" style="background-color: #14213d; margin-right: 10px" onclick="getMotalEditar()">Editar  <i class="fa-solid fa-pencil"></i></button>
            <button id="openModalBtn" onclick="getMotal()">Agregar Eleccion   <i class="fa-solid fa-plus"></i></button>



        </div>

        <!--Tabla de Elecciones-->

        <table id="table_elecciones">

            <%
                List<Eleccion> eleccionList = control.getELeccion();
                if(bandera){
            %>

            <thead>
            <tr>
                <th>id de Eleccion</th>
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
                        <td><%=ele.getEle_id()%></td>
                        <td><%=ele.getEle_estado()%></td>
                        <td><%=ele.getEle_fechaInicio()%></td>
                        <td><%=ele.getEle_fechaFinal()%></td>
                        <td><%=ele.getEle_nombre()%></td>
                        <td>
                            <div class="Pagination" style="display: flex; gap: 10px">
                                <form action="SvEliminarEleccion" id="formRemoveEleccion" method="POST">
                                    <button type="submit" style="background-color: #b7b3b3">Eliminar  <i class="fa-solid fa-trash"></i></button>
                                    <label for="id"><input type="hidden" id="id" name="eliminarEleccion" value="<%=ele.getEle_id()%>"></label>
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
                    <div class="Pagination" style="display: flex;">
                        <button onclick="window.location.href='SvInscribir?usu_id=<%=usuario.getUsu_id()%>&ele_id=<%=ele.getEle_id()%>'">Incribirse  <i class="fa-solid fa-check"></i> </button>
                    </div>
                </td>
            </tr>
            </tbody>
            <%}%>
            <%}%>
        </table>

        <!--Ventana Modal para editar Usuario-->
        <div class="modal-overlay" id="modalEleccion"></div>


        <div class="modalEdit" id="myModalEleccion" style="display: none">
            <span class="close-btn" id="Clase-btn">&times;</span>
            <h2>Editar Eleccion</h2>

            <form action="SvEditarEleccion" id="formuEditarEleccion" method="POST" autocomplete="off">


                <label for="idEleccion">Id de la Eleccion:</label>
                <input type="text" id="idEleccion" name="idEle" required>


                <label for="estadoEditar">Estado:</label>
                <input type="text" id="estadoEditar" class="estado" name="estadoEleccion" required>

                <label for="fechaInicialEditar">Fecha Inicial:</label>
                <input type="date" id="fechaInicialEditar" class="fechaInicial" name="fechainicial" required>

                <label for="fechaFinalEditar">Fecha Final:</label>
                <input type="date" id="fechaFinalEditar"  class="fechaFinal" name="fechafinal" required>

                <label for="nombreEleccionEditar">Nombre Eleccion</label>
                <input type="text" id="nombreEleccionEditar" class="nombreEleccion" name="nombreEleccion" required>

                <button type="submit" class="btnEdit">Guardar</button>
            </form>
                <button class="btnEdit" style="display: grid" type="button" onclick="getData()">Traer Datos</button>
        </div>
    </div>


    <!-------------------------------------------------------------------------------------------------------------------------->

    <!--Modal (formulario para agregar eleccion)-->

    <!-- El fondo oscuro (overlay) -->
    <div class="modal-overlay" id="modalOverlay"></div>

    <!-- El contenido de la ventana modal con formulario -->
    <div class="modal" id="myModal" style="display: none">
        <span class="close-btn" id="closeModalBtn">&times;</span>
        <h2>Formulario de Registro</h2>
        <form action="SvEleccion" method="POST" id="formuEleccion" autocomplete="off">

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


    <!------------------------------------------------------------------------------------------------------------------------------->
    <div data-content id="candidatos">
        <h1>Candidatos</h1>
        <div class="table-options">
            <button id="openModalCandidato" onclick="viewModal()">Agregar Candidato   <i class="fa-solid fa-plus"></i></button>
        </div>

        <table id="tabla-candidatos">
            <thead>
            <tr>
                <th>id Candidato</th>
                <th>Fecha de Registro</th>
                <th>Nombre del Candidato</th>
                <th>Rol del Candidato</th>
            </tr>
            </thead>
            <tbody>
                <%
                    if(!bandera){
                        if(listaFinalCandidatos != null){
                        for (Candidato candidato: listaFinalCandidatos){%>

                            <tr>
                                <td><%=candidato.getCan_id()%></td>
                                <td><%=candidato.getCan_fechaDeRegistro()%></td>
                                <td><%=candidato.getCan_nombre()%></td>
                                <td><%=candidato.getCan_rol()%></td>
                                <td>
                                    <div class="Pagination" style="display: flex">
                                        <form action="SvVotar" method="POST" id="votar">
                                            <label for="candidato">
                                                <input type="hidden" id="candidato" value="<%=candidato.getCan_id()%>" name="candidato">
                                         </label>
                                            <button type="submit">Votar   <i class="fa-solid fa-check-to-slot"></i></button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        <%}
                        } else {%>
                        <tr>
                            <td colspan="4">No hay Candidatos por mostrar</td>

                            </tr>
                        <%}
                    } else {
                        if(listaCandidatos == null){%>
                            <tr>
                                <td colspan="4">No hay Candidatos por mostrar</td>
                            </tr>

                        <%} else {
                            for (Candidato candidato: listaCandidatos){%>
                                <tr>
                                    <td><%=candidato.getCan_id()%></td>
                                    <td><%=candidato.getCan_fechaDeRegistro()%></td>
                                    <td><%=candidato.getCan_nombre()%></td>
                                    <td><%=candidato.getCan_rol()%></td>
                                </tr>
                    <%
                             }
                        }
                    }%>
            </tbody>
        </table>
    </div>


    <div class="modal-overlay" id="modalCandidato"></div>


    <div class="modalEdit" id="myModalCandidato" style="display: none">
        <span class="close-btn" id="btnCloseCandidato">&times;</span>
        <h2>Agrega un Candidato</h2>

        <form action="SvCandidatos" id="formuAgregarCan" method="POST" autocomplete="off">

            <label for="fechaRegistro">Fecha de Registro:</label>
            <input type="date" id="fechaRegistro" name="fechaCandidato" required>

            <label for="nombreCandidato">Nombre del Candidato:</label>
            <input type="text" id="nombreCandidato" name="nombreCandidato" required>

            <label for="rolCandidato">Rol del Candidato:</label>
            <input type="text" id="rolCandidato"  name="rolCandidato" required>

            <label for="id-Eleccion">El candidato es de la Eleccion:</label>
            <input type="text" id="id-Eleccion" name="id_eleccion" required>


            <button type="submit" class="btnAgregar">Agregar</button>
        </form>
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
<script src="javaScript/ediatModal.js"></script>
<script src="javaScript/cargarDatos.js"></script>
<script src="javaScript/agregarCanModal.js"></script>
<script src="javaScript/diagrama.js"></script>
<script src="javaScript/script.js"></script>

<script>
    <%if(!bandera){%>
    document.getElementById("openModalBtn").style.display = "none";
    document.getElementById("editar").style.display = "none";
    document.getElementById("openModalCandidato").style.display = "none";
    <%}%>
</script>

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