function getMotalEditar(){
    // Obtener elementos
    const openModalBtnEditar = document.getElementById("editar");
    const closeModalBtnEditar = document.getElementById('Clase-btn');
    const modalOverlayEditar = document.getElementById('modalEleccion');
    const myModalEditar = document.getElementById('myModalEleccion');

    // Abrir la ventana modal
    openModalBtnEditar.addEventListener('click', () => {
        modalOverlayEditar.style.display = 'block';
        myModalEditar.style.display = 'inline-block';
        myModalEditar.style.alignItems = "center";
        myModalEditar.style.justifyContent = "center";
    });

    // Cerrar la ventana modal
    closeModalBtnEditar.addEventListener('click', () => {
        modalOverlayEditar.style.display = 'none';
        myModalEditar.style.display = 'none';
    });

    // Cerrar al hacer clic fuera de la ventana modal
    modalOverlayEditar.addEventListener('click', () => {
        modalOverlayEditar.style.display = 'none';
        myModalEditar.style.display = 'none';
    });
}