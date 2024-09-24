let pieChart;  // Variable global para almacenar la instancia del gráfico

document.getElementById("form-eleccion").addEventListener("submit", function(event) {
    event.preventDefault();  // Prevenir el envío del formulario

    const eleccionId = document.getElementById("elecciones").value;

    // Verifica que se haya seleccionado una elección válida
    if (eleccionId === "-1") {
        alert("Por favor, seleccione una elección.");
        return;
    }

    // Realiza la solicitud AJAX al servlet
    fetch(`SvResultados?valor=${eleccionId}`)
        .then(response => response.json())
        .then(data => {
            // Extrae los datos del JSON (HashMap devuelto)
            const candidatos = Object.keys(data);  // Nombres de los candidatos
            const porcentajes = Object.values(data);  // Porcentajes de votos
            const colors = ['#FF6384', '#36A2EB', '#FFCE56', '#e9c46a', '#2a9d8f', '#e63946'];  // Colores del gráfico

            // Si ya existe un gráfico previo, destrúyelo
            if (pieChart) {
                pieChart.destroy();
            }

            // Reinicia el canvas reemplazándolo en el DOM
            const pieContainer = document.getElementById('pieContainer');
            pieContainer.innerHTML = '<canvas id="pieChart"></canvas>';  // Reemplaza el canvas
            const pieCanvas = document.getElementById('pieChart');
            const pieCtx = pieCanvas.getContext('2d');

            // Crear el nuevo gráfico
            pieChart = new Chart(pieCtx, {
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
