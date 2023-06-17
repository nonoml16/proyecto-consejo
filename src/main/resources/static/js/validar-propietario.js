//AOS.init();
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input')

const expresiones = {
	username: /^[a-zA-Z0-9\_\-]{4,16}$/, 
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, 
	password: /^.{4,12}$/,
	apellidos: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
	dni: /^[a-zA-Z0-9]{9,11}$/
}

const campos = {
	username: false, 
	nombre: false, 
	password: false,
	apellidos: false,
	dni: false
}

const validarFormulario = (e) => {
    switch (e.target.name){
        case 'dni':
            if(expresiones.dni.test(e.target.value)){
                document.getElementById('grupo-dni_usuario').classList.remove('formulario-grupo-error');
                document.getElementById('grupo-dni_usuario').classList.add('formulario-grupo-correcto');
                document.querySelector('#grupo-dni_usuario .form-input-error').classList.remove('form-input-error-activo');
                campos['dni'] = true;
            }else{
                document.getElementById('grupo-dni_usuario').classList.add('formulario-grupo-error');
                document.getElementById('grupo-dni_usuario').classList.remove('formulario-grupo-correcto');
                document.querySelector('#grupo-dni_usuario .form-input-error').classList.add('form-input-error-activo');
                campos['dni'] = false;
            }
        break;
        case 'username':
            if(expresiones.username.test(e.target.value)){
                document.getElementById('grupo-usuario').classList.remove('formulario-grupo-error');
                document.getElementById('grupo-usuario').classList.add('formulario-grupo-correcto');
                document.querySelector('#grupo-usuario .form-input-error').classList.remove('form-input-error-activo');
                campos['username'] = true;
            }else{
                document.getElementById('grupo-usuario').classList.add('formulario-grupo-error');
                document.getElementById('grupo-usuario').classList.remove('formulario-grupo-correcto');
                document.querySelector('#grupo-usuario .form-input-error').classList.add('form-input-error-activo');
                campos['username'] = false;
            }
        break;
        case 'password':
            if(expresiones.password.test(e.target.value)){
                document.getElementById('contrasena_usuario').classList.remove('formulario-grupo-error');
                document.getElementById('contrasena_usuario').classList.add('formulario-grupo-correcto');
                document.querySelector('#contrasena_usuario .form-input-error').classList.remove('form-input-error-activo');
                campos['password'] = true;
            }else{
                document.getElementById('contrasena_usuario').classList.add('formulario-grupo-error');
                document.getElementById('contrasena_usuario').classList.remove('formulario-grupo-correcto');
                document.querySelector('#contrasena_usuario .form-input-error').classList.add('form-input-error-activo');
                campos['password'] = false;
            }
        break;
        case 'nombre':
            if(expresiones.nombre.test(e.target.value)){
                document.getElementById('nombre').classList.remove('formulario-grupo-error');
                document.getElementById('nombre').classList.add('formulario-grupo-correcto');
                document.querySelector('#nombre .form-input-error').classList.remove('form-input-error-activo');
                campos['nombre'] = true;
            }else{
                document.getElementById('nombre').classList.add('formulario-grupo-error');
                document.getElementById('nombre').classList.remove('formulario-grupo-correcto');
                document.querySelector('#nombre .form-input-error').classList.add('form-input-error-activo');
                campos['nombre'] = false;
            }
        break;
        case 'apellidos':
            if(expresiones.apellidos.test(e.target.value)){
                document.getElementById('apellidos').classList.remove('formulario-grupo-error');
                document.getElementById('apellidos').classList.add('formulario-grupo-correcto');
                document.querySelector('#apellidos .form-input-error').classList.remove('form-input-error-activo');
                campos['apellidos'] = true;
            }else{
                document.getElementById('apellidos').classList.add('formulario-grupo-error');
                document.getElementById('apellidos').classList.remove('formulario-grupo-correcto');
                document.querySelector('#apellidos .form-input-error').classList.add('form-input-error-activo');
                campos['apellidos'] = false;
            }
        break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validarFormulario)
    input.addEventListener('blur', validarFormulario)
});

formulario.addEventListener('submit', (e) => {
    e.preventDefault();

    if(campos.username && campos.nombre && campos.password && campos.apellidos && campos.dni){
        formulario.submit();
        document.getElementById('form-mensaje').classList.remove('form-mensaje-activo')
    }else{
        document.getElementById('form-mensaje').classList.add('form-mensaje-activo')
    }
});