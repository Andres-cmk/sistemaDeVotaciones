function viewModal(){
    const btnModal = document.getElementById("openModalCandidato");
    const closeModalBtnCan = document.getElementById("btnCloseCandidato");
    const modalOverlayCandidato = document.getElementById("modalCandidato");
    const myModalCandidato = document.getElementById("myModalCandidato");

    btnModal.addEventListener('click', () => {
        modalOverlayCandidato.style.display = 'block';
        myModalCandidato.style.display = 'block';
    });

    // Cerrar la ventana modal
    closeModalBtnCan.addEventListener('click', () => {
        modalOverlayCandidato.style.display = 'none';
        myModalCandidato.style.display = 'none';
    });

    // Cerrar al hacer clic fuera de la ventana modal
    modalOverlayCandidato.addEventListener('click', () => {
        modalOverlayCandidato.style.display = 'none';
        myModalCandidato.style.display = 'none';
    });
}