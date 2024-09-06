function getMotal(){
    // Obtener elementos
    const openModalBtn = document.getElementById('openModalBtn');
    const closeModalBtn = document.getElementById('closeModalBtn');
    const modalOverlay = document.getElementById('modalOverlay');
    const myModal = document.getElementById('myModal');

    // Abrir la ventana modal
    openModalBtn.addEventListener('click', () => {
        modalOverlay.style.display = 'block';
        myModal.style.display = 'block';
    });

    // Cerrar la ventana modal
    closeModalBtn.addEventListener('click', () => {
        modalOverlay.style.display = 'none';
        myModal.style.display = 'none';
    });

    // Cerrar al hacer clic fuera de la ventana modal
    modalOverlay.addEventListener('click', () => {
        modalOverlay.style.display = 'none';
        myModal.style.display = 'none';
    });
}