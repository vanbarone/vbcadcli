package com.vanbarone.vbcadcli.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanbarone.vbcadcli.entities.Client;
import com.vanbarone.vbcadcli.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		
		List<Client> lista = service.findAll();
		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client cliente = service.findById(id);
		
		return ResponseEntity.ok().body(cliente);
	}
}
