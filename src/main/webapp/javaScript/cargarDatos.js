function formatDate(dateString) {
    if (!dateString) {
        return "";
    }
    const date = new Date(dateString);
    if (isNaN(date.getTime())) {
        console.error("Fecha inválida:", dateString);
        return "";
    }
    const year = date.getFullYear().toString();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}


function getData(){
    let id = $("#idEleccion").val();
    $.ajax(
        {
            url: 'SvEditarEleccion',
            type: 'GET',
            dataType: 'json',
            data: {ele_id: id},
            success: function (response){
                console.log("fecha Inicio", response.ele_fechaInicio)
                console.log("fecha final", response.ele_fechaFinal)

                let fechaInicioFormateada = formatDate(response.ele_fechaInicio);
                let fechaFinalFormateada = formatDate(response.ele_fechaFinal);

                $("#estadoEditar").val(response.ele_estado);
                $("#fechaInicialEditar").val(fechaInicioFormateada);
                $("#fechaFinalEditar").val(fechaFinalFormateada);
                $("#nombreEleccionEditar").val(response.ele_nombre);
            },
            error: function(xhr, status, error) {
                // Aquí se captura el error y se muestra en un alert
                alert("Error: " + xhr.status + " - " + xhr.responseText);
            }
        }
    );

}