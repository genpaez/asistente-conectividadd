package com.oesia.controller;


import com.jcraft.jsch.JSchException;
import com.oesia.model.Canal;
import com.oesia.model.Cliente;
import com.oesia.model.Compositor;
import com.oesia.model.Executor;
import com.oesia.model.MyRepository;
import com.oesia.model.PortFR;
import com.oesia.model.SearchCriteria;
import com.oesia.model.Sede;
import com.oesia.model.Ciudad;
import com.oesia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class SearchController {
	
	@Autowired
	private MyRepository myRepository;
    //UserService userService;
    

    /*
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
	*/
	
    @PostMapping(path = "/api/clientes")
   // public @ResponseBody List<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) 
    public @ResponseBody List<?> getClientes() {

    	List<Canal> clientes = myRepository.findByIdcanal();
        return clientes;

    }

    @PostMapping("/api/ciudades")
    public @ResponseBody List<?> getCiudades(@RequestBody Canal canal){
		
    	List<Canal> ciudades = myRepository.findBySede(canal.getCliente());
    	return ciudades;
    }
    
    @PostMapping("/api/sedes")
    public @ResponseBody List<?> findSedes(@RequestBody Canal canal){
		
    	List<Canal> sedes = myRepository.findBySedeCliente(canal.getCliente(), canal.getCiudad());
    	return sedes;
    }
     
    @PostMapping("/api/canales")
    public @ResponseBody List<?> findCanal(@RequestBody Canal canal){
		
    	List<Canal> canales = myRepository.findBySedeClienteCanal(canal.getCliente(), canal.getCiudad(), canal.getSede());
    	return canales;
    }
    
    @PostMapping("/api/vias")
    public Canal findVias(@RequestBody Canal canal){
		
    	Canal vias = myRepository.findVias(canal.getCliente(), canal.getCiudad(), canal.getSede(), canal.getCanal());
    	return vias;
    }
    
    @PostMapping("/api/pruebasmpls")
    public List<String> pruebasMpls(@RequestBody Canal canal) throws IOException, JSchException, InterruptedException{
    	
    	PortFR man = new PortFR();
    //	Compositor myComposer = new Compositor(); 
    //	Executor myExecutor = new Executor(canal.getIp_pe()); // Para portforwarding  
        Compositor myComposer = new Compositor(); 
        man.conectar();

    	List<String> comandos = myComposer.crearComandos(canal.getNombre_pe(), canal.getVprn(), canal.getIpwan_pe(), canal.getIpwan_router(), canal.getPuerto_pe(), canal.getEnrutamiento());
    	List<String> respuestape = man.Execute(comandos);
    	
    	man.close();
    	return respuestape;
    }
    
}