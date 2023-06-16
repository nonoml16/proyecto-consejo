alert("Hello World!");
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input')

const expresiones = {
	username: /^[a-zA-Z0-9\_\-]{4,16}$/, 
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, 
	password: /^.{4,12}$/,
	apellidos: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
	dni: /^[a-zA-Z0-9]{9,11}$/
}

const validarFormulario = (e) => {
    switch (e.target.name){
        case 'dni':
            if(expresiones.dni.test(e.target.value)){
                document.getElementById('grupo-dni_usuario').classList.remove('formulario-grupo-error');
                document.getElementById('grupo-dni_usuario').classList.add('formulario-grupo-correcto');
            }else{
                document.getElementById('grupo-dni_usuario').classList.add('formulario-grupo-error');
                document.getElementById('grupo-dni_usuario').classList.remove('formulario-grupo-correcto');
                document.querySelector('#grupo-dni_usuario .form-input-error').classList.add('form-input-error-activo');
            }
        break;
        case 'username':

        break;
        case 'password':

        break;
        case 'nombre':

        break;
        case 'apellidos':

        break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validarFormulario)
    input.addEventListener('blur', validarFormulario)
});

formulario.addEventListener('submit', (e) => {
    e.preventDefault();
});