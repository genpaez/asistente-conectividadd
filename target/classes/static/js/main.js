$(document).ready(function () {

    $("#searchmpls").submit(function (event) {
        
        event.preventDefault();
        //stop submit the form, we will post it manually.
        $('#boton-pruebasmpls').attr('disabled','disabled');
        $('#boton-pruebasmpls').css('cursor','wait');
        event.preventDefault();
        ajax_pruebasmpls();       

    });
    
    $("#searchrouter").submit(function (event) {
    	
        event.preventDefault();
        //stop submit the form, we will post it manually.
        $('#boton-pruebasrouter').attr('disabled','disabled');
        $('#boton-pruebasrouter').css('cursor','wait');
        ajax_pruebasrouter();

    }); 

});





function ajax_cliente() {      // onchange en select boxes
		
  $("#loadmpls").hide();  // inicializando vista
  $("#loadrouter").hide();  
	
	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/clientes",
        data: JSON.stringify(),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function() {
            $("#clipboard").hide();
            $("#clipboardrouter").hide();
        		},
    		}).then(function(data) {
    			$('#cliente').html('');   // Deja en blanco antes de cargar desde server
    			$('#cliente').append('<option value="">' + 'Seleccione un cliente...' + '</option>');
    			$.each(data, function(i, optionHtml){	
  	            $('#cliente').append('<option value="'+optionHtml.cliente+'">' + optionHtml.cliente + '</option>');  // Recorre array e inserta opciones
  	           //       dropdown.append('<option value="' + v.id + '">' + v.name + '</option>'); Notación
  	           });
    		});
    
//    $("#bth-pruebasmpls").prop("disabled", false);
    
}


function ajax_ciudad(cliente) {      // onchange en select boxes
	var search = {}
    search["cliente"] = cliente;
//	event.preventDefault();
//	$("#bth-pruebasmpls").prop("disabled", true);  // <<--
	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/ciudades",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
    		}).then(function(data) {
    			$('#ciudad').html('');   // Deja en blanco antes de cargar desde server 
    			$('#ciudad').append('<option value="">' + 'Seleccione una ciudad...' + '</option>');
    			$.each(data, function(i, optionHtml){	
  	            $('#ciudad').append('<option value="'+optionHtml.ciudad+'">' + optionHtml.ciudad + '</option>');  // Recorre array e inserta opciones
  	            //       dropdown.append('<option value="' + v.id + '">' + v.name + '</option>'); Notación
  	           });
    		});
    
//    $("#bth-pruebasmpls").prop("disabled", false);
}



function ajax_sede(cliente, ciudad) {      // onchange en select boxes

	var sede = {}
	sede["cliente"] = cliente;
	sede["ciudad"] = ciudad;
/*	var search = {
			["cliente"]:cliente,
			["ciudad"]:ciudad
			}*/
//	search["cliente"] = cliente;
//	search["ciudad"] = ciudad;
	
//	event.preventDefault();
//	$("#bth-pruebasmpls").prop("disabled", true);  // <<--
	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/sedes",
        data: JSON.stringify(sede),
        dataType: 'json',
        cache: false,
        timeout: 600000,
    		}).then(function(data) {
    			$('#sede').html('');   // Deja en blanco antes de cargar desde server
    			$('#sede').append('<option value="">' + 'Seleccione una sede...' + '</option>');
    			$.each(data, function(i, optionHtml){	
  	            $('#sede').append('<option value="'+ optionHtml.sede +'">' + optionHtml.sede + '</option>');  // Recorre array e inserta opciones
  	           //       dropdown.append('<option value="' + v.id + '">' + v.name + '</option>'); Notación
  	           });
    		});

}



function ajax_canal(cliente, ciudad, sede) {      // onchange en select boxes
	
	
	var canal = {}
	canal["cliente"] = cliente;
	canal["ciudad"] = ciudad;
	canal["sede"] = sede;
//	var search = {}
//    search["nombre"] = $("#username").val();	
//	event.preventDefault();
//	$("#bth-pruebasmpls").prop("disabled", true);  // <<--
	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/canales",
        data: JSON.stringify(canal),
        dataType: 'json',
        cache: false,
        timeout: 600000,
    		}).then(function(data) {
    			$('#canal').html('');   // Deja en blanco antes de cargar desde server
    			$('#canal').append('<option value="">' + 'Seleccione un canal...' + '</option>');
    			$.each(data, function(i, optionHtml){	
  	            $('#canal').append('<option value="' + optionHtml.canal + '">' + optionHtml.canal + '</option>');  // Recorre array e inserta opciones
  	           //       dropdown.append('<option value="' + v.id + '">' + v.name + '</option>'); Notación
  	           });
    		});
    
}



function ajax_vias(cliente, ciudad, sede, canal) {      // onchange en select boxes
	
	var vias = {}
	vias["cliente"] = cliente;
	vias["ciudad"] = ciudad;
	vias["sede"] = sede;
	vias["canal"] = canal;
	
//	var search = {}
//    search["nombre"] = $("#username").val();	
//	event.preventDefault();
//	$("#bth-pruebasmpls").prop("disabled", true);  // <<--
	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/vias",
        data: JSON.stringify(vias),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function() {
            $("#clipboard").hide();
            $("#clipboardrouter").hide();
        		},
    		}).then(function(data) {
    			$('#nombrepe').html('');
    			$('#nombrepe').text(data.nombre_pe);
    			$('#ippe').html('');
    			$('#ippe').append(data.ip_pe);
    			$('#ipwanpe').html('');
    			$('#ipwanpe').append(data.ipwan_pe);
    			$('#ipwanrouter').html('');
    			$('#ipwanrouter').append(data.ipwan_router);
    			$('#puertope').html('');
    			$('#puertope').append(data.puerto_pe);
    			$('#enrutamiento').html('');
    			$('#enrutamiento').append(data.enrutamiento);
    			$('#routerwan').html('');
    			$('#routerwan').append(data.ipwan_router);
    			$('#loopback').html('');
    			$('#loopback').append(data.loopback);
    			$('#vprn').html('');
    			$('#vprn').append(data.vprn);
    		});
    

}




function ajax_pruebasmpls() {     // boton pruebas mpls

	var pruebampls = {}
	pruebampls["nombre_pe"] = $('#nombrepe').text();
	pruebampls["ip_pe"] = $('#ippe').text();
	pruebampls["ipwan_pe"] = $('#ipwanpe').text();
	pruebampls["vprn"] = $('#vprn').text();
	pruebampls["ipwan_router"] = $('#ipwanrouter').text();
	pruebampls["puerto_pe"] = $('#puertope').text();
	pruebampls["enrutamiento"] = $('#enrutamiento').text();

	
    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/pruebasmpls",
        data: JSON.stringify(pruebampls),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function() {
            $("#loadmpls").show();
        },
        success: function() {
            $("#clipboard").show();
            $("#loadmpls").hide();
        },
    		}).then(function(data) {
    			
    			$('#mpls_response').html('');
    			$.each(data, function(i, optionHtml){	
    				
      	            $('#mpls_response').append(optionHtml);  // Recorre array e inserta opciones
      	           });
    			$('#boton-pruebasmpls').removeAttr('disabled');
    			$('#boton-pruebasmpls').css('cursor','pointer');
    		});
}



function ajax_pruebasrouter() {     // boton pruebas router
	
	var vias = {}
	vias["pe"] = $("#pe").val();
	vias["ipwanpe"] = $("#ipwanpe").val();
	vias["ipwanrouter"] = $("#ipwanrouter").val();
	vias["puertope"] = $("#puertope").val();
	vias["enrutamiento"] = $("#enrutamiento").val();

    $.ajax({
    	type: "POST",
        contentType: "application/json",
        url: "/api/pruebasmpls",
        data: JSON.stringify(vias),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function() {
            $("#loadrouter").show();
            $("#clipboardrouter").show();
        },
        success: function() {
            $("#loadrouter").hide();
      //      $("#clipboardrouter").show();
        },
    		}).then(function(data) {
    			
				$('#router_response').html('');
    			$.each(data, function(i, optionHtml){	
      	            $('#router_response').append(optionHtml.i);  // Recorre array e inserta opciones
      	           });
    			$('#boton-pruebasrouter').removeAttr('disabled'); 
    			$('#boton-pruebasrouter').css('cursor','pointer');
    		});
}




function openPest(evt, pestnombre) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(pestnombre).style.display = "block";
    evt.currentTarget.className += " active";

}


function CopyToClipboard (containerid) {
	 
	  var textarea = document.createElement('textarea')
	  textarea.id = 'temp_element'
	 
	  textarea.style.height = 0
	  
	  document.body.appendChild(textarea)
	  
	  id=containerid
	  textarea.value = document.getElementById(containerid).innerText
	  
	  var selector = document.querySelector('#temp_element')
	  selector.select()
	  document.execCommand('copy')
	 
	  document.body.removeChild(textarea)
	}
