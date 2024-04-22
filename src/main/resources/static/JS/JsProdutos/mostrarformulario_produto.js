function mostrarFormulario(idFormulario) {
    var formularios = document.getElementsByClassName("formulario");
    for (var i = 0; i < formularios.length; i++) {
        formularios[i].style.display = "none";
    }
    document.getElementById(idFormulario).style.display = "block";
}
