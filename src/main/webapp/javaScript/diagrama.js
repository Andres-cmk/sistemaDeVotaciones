document.getElementById("form-eleccion").addEventListener("submit", function(event) {
    event.preventDefault();

    const candidatos = ['Candidato 1', 'Candidato 2', 'Candidato 3'];
    const porcentajes = [45, 35, 20]; // Porcentaje de votos por candidato
    const colors = ['#FF6384', '#36A2EB', '#FFCE56'];

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
});