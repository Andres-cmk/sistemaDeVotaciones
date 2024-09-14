$(document).ready(function() {
    $('.login').on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'SvVerificar',
            data: $(this).serialize(),
            dataType: 'json',
            success: function(response) {
                if (response.status === 'error') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: "Usuario invalido",
                        timer: 3000
                    })
                } else if (response.status === 'success') {
                    // Redirigir a la página principal
                    window.location.href = 'Pagina_Principal.jsp';
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
