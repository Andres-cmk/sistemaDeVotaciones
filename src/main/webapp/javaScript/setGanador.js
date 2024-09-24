document.getElementById("form-ganador").addEventListener("submit", (e) => {
    e.preventDefault();

    const eleccionIdGanador = document.getElementById("eleccionesGanador").value;

    if (eleccionIdGanador === "-1") {
        alert("Por favor, seleccione una elección válida.");
        return;
    }

    // Realiza la solicitud al servlet para obtener el ganador
    fetch(`SvGetGanador?id=${eleccionIdGanador}`)
        .then(response => response.json())
        .then(data => {
            // Obtén el nombre del ganador desde la respuesta
            const nombreGanador = data.ganador;

            // Selecciona el div de la tarjeta
            const div = document.getElementById("card");

            // Actualiza el contenido del div con el nombre del ganador
            div.innerHTML = `
                <h3 style="text-align: center;">¡Ganador!</h3>
                <p style="text-align: center; font-size: 18px;">${nombreGanador}</p>
            `;
        })
        .catch(error => {
            console.error('Error al obtener el ganador:', error);
        });
});
