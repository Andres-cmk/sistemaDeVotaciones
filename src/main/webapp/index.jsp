<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="estilos/style_login.css">
    <link rel="shortcut icon" href="imagenes/hive.svg" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8234d7916b.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="principal">
    <div class="form">
        <div class="contenedor">
            <div class="formulario">
                <form class="formu" autocomplete="off" action="SvVerificar" method="POST">
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

                    <div>
                        <button type="submit">Acceder</button>
                    </div>
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    Swal.fire({
        title: "Bienvenido"
    });
</script>
</body>
</html>