window.addEventListener('load', function () {

    const formulario = document.querySelector('#delete_odontologo');

    formulario.addEventListener('submit', function (event) {
        const id = document.querySelector('#ID').value;
        console.log(id);
        const url = '/odontologos';
        const settings = {
            method: 'DELETE'
        }

        fetch(`${url}/${id}`, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Odontologo eliminado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#ID').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "../delete_odontologo.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});