var toggle = true;

function myFunction() {
    document.getElementById('demo').innerHTML = 'Parrafo cambiado';

}

function hideIndex() {
    document.getElementById('index').style.visibility = 'hidden';
}

function toggleIndex() {
    var visibility = '';
    if (toggle) {
        visibility = 'hidden';
        buttonText = 'Mostrar indice';
    } else {
        visibility = 'visible';
        buttonText = 'Ocultar indice';
    }
    document.getElementById('index').style.visibility = visibility;
    document.getElementById('btnIndice').innerHTML = buttonText;
    toggle = !toggle;
}

document.addEventListener('DOMContentLoaded', function() {
    toggleIndex();
});

