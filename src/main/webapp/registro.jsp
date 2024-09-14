<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>

    <title>Registro</title>
    <link rel="shortcut icon" href="imagenes/database-solid.svg" type="image/x-icon">
    <link rel="stylesheet" href="./styles/style_registro.css" type="text/css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

</head>
<body>

<div class="principal">

  <div class="contenedor">
    <div class="formulario">
      <form class="register" autocomplete="off" action="SvUsuarios" method="POST">
        <h2>Registro</h2>

        <div class="input-contenedor">
          <label for="name">Nombres</label>
          <input type="text" name="nombre" id="name" required />
        </div>

        <div class="input-contenedor">
          <label for="name">Apellidos</label>
          <label for="lastname"></label><input type="text" name="apellido" id="lastname" required />
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
          <input type="text" name="rol" id="rol"/>
        </div>

          <button type="submit">Registrar</button>
      </form>

    </div>

  </div>

</div>

<script>
  $(document).ready(function() {
    $('.register').on('submit', function(e) {
      e.preventDefault();
      $.ajax({
        type: 'POST',
        url: 'SvUsuarios',
        data: $(this).serialize(),
        dataType: 'json',  // Especifica que esperamos JSON
        success: function(response) {
          console.log("Response received:", response);  // Para debugging
          if (response.status === "success") {
            Swal.fire({
              icon: 'success',
              title: 'El usuario registrado con éxito',
              showConfirmButton: false,
              timer: 3000
            }).then((result) => {
              window.location.href = 'login.jsp';
            });
          } else if (response.status === "valueRepet") {
            Swal.fire({
              icon: 'error',
              title: 'El email o password ya existe',
              showConfirmButton: false,
              timer: 3000
            }).then((result) => {
              window.location.href = 'login.jsp';
            });
          } else if (response.status === "error") {
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: response.message,
              showConfirmButton: false,
              timer: 3000
            });
          } else if (response.status === "errorAdmi") {
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: "El usuario administrador ya existe en el sistema",
              timer: 3000
            }).then((result) => {
              window.location = "registro.jsp";
            });
          }
        },
        error: function(xhr, status, error) {
          console.error("AJAX error:", status, error);  // Para debugging
          Swal.fire({
            icon: 'error',
            title: 'Error!',
            text: error,
            showConfirmButton: false,
            timer: 3000
          });
        }
      });
    });
  });
</script>

</body>
</html>