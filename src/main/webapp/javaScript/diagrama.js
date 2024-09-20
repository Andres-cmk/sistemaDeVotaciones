document.getElementById("form-eleccion").addEventListener("submit", function(event) {
    event.preventDefault();

    const eleccionId = document.getElementById("elecciones").value;

    // Realiza la solicitud AJAX al servlet
    fetch(`SvResultados?valor=${eleccionId}`)
        .then(response => response.json())
        .then(data => {
            // Extrae los datos del JSON (HashMap devuelto)
            const candidatos = Object.keys(data);  // Nombres de los candidatos
            const porcentajes = Object.values(data);  // Porcentajes de votos
            const colors = ['#FF6384', '#36A2EB', '#FFCE56', '#e9c46a', '#2a9d8f', '#e63946'];  // Colores del gráfico

            // Actualizar la gráfica
            const pieCanvas = document.getElementById('pieChart');
            pieCanvas.style.display = 'block';  // Mostrar el canvas

            const pieCtx = pieCanvas.getContext('2d');
            new Chart(pieCtx, {
                type: 'pie',
                data: {
                    labels: candidatos,
                    datasets: [{
                        data: porcentajes,
                        backgroundColor: colors
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
        });
});
