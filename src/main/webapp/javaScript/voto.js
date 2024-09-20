$(document).ready(function(){
    $('.vote').on('submit', function(e){
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'SvVotar',
            data: $(this).serialize(),
            dataType: 'json',
            success: function (response){
                console.log("Response", response);
                if(response.status === 'aceptado'){
                    Swal.fire({
                        icon: 'success',
                        text: 'Voto Registrado',
                        timer: 3000
                    })
                }else if(response.status === 'rechazado'){
                    Swal.fire({
                        icon:'info',
                        text:'Ya no puedes votar',
                        timer:3000
                    })
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX error:", status, error);  // Para debugging
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: error,
                    timer: 3000
                });
            }
        });
    });
});