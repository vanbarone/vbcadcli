package com.vanbarone.vbcadcli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanbarone.vbcadcli.entities.Client;
import com.vanbarone.vbcadcli.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll(){
		List<Client> list = repository.findAll();
		return list;
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		
		Client entity = obj.get();
		
		//Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return entity;
	}
	
}
