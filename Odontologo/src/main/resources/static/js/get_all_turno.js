window.addEventListener('load', function () {
    (function(){
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         console.log(data);
         for(turno of data){
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            turnoRow.innerHTML =
                    '<td class=\"td_id_odontologo\">' + turno.odontologo.id + '</td>' +
                    '<td class=\"td_nombre_odontologo\">' + turno.odontologo.nombre + '</td>' +
                    '<td class=\"td_apellido_odontologo\">' + turno.odontologo.apellido + '</td>' +
                    '<td class=\"td_id_paciente\">' + turno.paciente.id + '</td>' +
                    '<td class=\"td_nombre_paciente\">' + turno.paciente.nombre + '</td>' +
                    '<td class=\"td_apellido_paciente\">' + turno.paciente.apellido + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>';
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "../get_all_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})