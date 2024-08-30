$(document).ready(function() {
    $('.formu').on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'SvUsuarios',
            data: $(this).serialize(),
            success: function(response) {
                if (response.status === 'success') {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'El usuario registrado con éxito',
                        showConfirmButton: false,
                        timer: 3000
                    }).then((result) => {
                        window.location.href = 'login.jsp';
                    });
                }else if (response.status === 'valueRepet') {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'El email o password ya existe',
                        showConfirmButton: false,
                        timer: 3000
                    }).then((result) =>{
                        window.location.href = 'login.jsp';
                    })
                }
                else if (response.status === 'error') {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'Error',
                        text: response.message,
                        showConfirmButton: false,
                        timer: 3000
                    });
                }
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    position: 'top-end',
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