<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Login</title>
    <link rel="shortcut icon" href="imagenes/hive.svg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./styles/style_login.css">
    <script src="https://kit.fontawesome.com/8234d7916b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="principal">
    <div class="form">
        <div class="contenedor">
            <div class="formulario">
                <form class="login" autocomplete="off" action="SvVerificar" method="POST">
                    <h2>Iniciar Sesión</h2>
                    <div class="input-contenedor">
                        <label for="documento">Numero de documento</label>
                        <input type="text" name="documet" id="documento" required />
                        <i class="fa-regular fa-address-card"></i>
                    </div>
                    <div class="input-contenedor">
                        <label for="email">Email</label>
                        <input type="email" name="correo" id="email" required />
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <div class="input-contenedor">
                        <label for="contraseña">Contraseña</label>
                        <input type="password" name="password" id="contraseña" required/>
                        <i class="fa-solid fa-lock"></i>
                    </div>
                        <button type="submit">Acceder</button>
                </form>

                <div class="registrar">
                    <p>
                        ¿Aun no estas registrado? <a href="registro.jsp" class="register" >Registrate</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="img">
        <img src="./imagenes/undraw_voting_nvu7.png" alt="Imagen de logo" class="imagen">
    </div>
</div>


<script>
    Swal.fire({
        title: "Bienvenido"
    });
</script>

<script>

    $(document).ready(function() {
        $('.login').on('submit', function(e) {
            e.preventDefault();
            $.ajax({
                type: 'POST',
                url: 'SvVerificar',
                data: $(this).serialize(),
                dataType: 'json',
                success: function(response) {
                    console.log(response);  // Añadir esta línea para inspeccionar la respuesta en la consola
                    if (response.status === 'error') {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: "Usuario invalido",
                            timer: 3000
                        });
                    }else if(response.status === "already_logged_in"){
                        Swal.fire({
                            icon: 'error',
                            title: 'Ya votaste',
                            text:'Ya no puedes ingresar'
                        })
                    }else if (response.status === 'success') {
                        // Redirigir a la página principal
                        window.location.href = 'Pagina_Principal.jsp';
                    }else {
                        console.log("Estado no reconocido: ", response.status);
                    }
                },
                error: function(xhr, status, error) {
                    // Manejo de errores en la solicitud AJAX
                    Swal.fire({
                        icon: 'error',
                        title: 'Error en la solicitud',
                        text: error,
                        timer: 3000
                    });
                }
            });
        });
    });

</script>

</body>
</html>