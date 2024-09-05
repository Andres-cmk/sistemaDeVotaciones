<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Registro</title>

    <link rel="shortcut icon" href="imagenes/database-solid.svg" type="image/x-icon">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="./javaScript/registro.js"></script>
    <link rel="stylesheet" href="./styles/style_registro.css" type="text/css">

</head>
<body>

<div class="principal">

  <div class="contenedor">
    <div class="formulario">
      <form class="formu" autocomplete="off" action="SvUsuarios" method="POST">
        <h2>Registro</h2>

        <div class="input-contenedor">
          <label for="name">Nombres</label>
          <input type="text" name="nombre" id="name" required />
        </div>

        <div class="input-contenedor">
          <label for="name">Apellidos</label>
          <input type="text" name="apellido" id="lastname" required />
        </div>

        <div class="input-contenedor">
          <label for="documento">Numero de Documento</label>
          <input type="text" name="documento" id="documento" required />
        </div>

        <div class="input-contenedor">
          <label for="email">Email</label>
          <input type="email" name="email" id="email" required />
        </div>

        <div class="input-contenedor">
          <label for="contra">Contraseña</label>
          <input type="password" name="password" id="contra" required />
        </div>

        <div class="input-contenedor">
          <label for="rol">Rol</label>
          <input type="text" name="rolEstudiante" id="rol"/>
        </div>


          <button type="submit">Registrar</button>

      </form>
    </div>
  </div>
</div>

</body>
</html>