package com.vanbarone.vbcadcli.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanbarone.vbcadcli.dto.ClientDTO;
import com.vanbarone.vbcadcli.entities.Client;
import com.vanbarone.vbcadcli.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public List<ClientDTO> findAll(){
		List<Client> list = repository.findAll();
		
		List<ClientDTO> listDTO = new ArrayList<>();
		
		listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		return listDTO;
	}
	
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		
		ClientDTO dto = new ClientDTO(obj.get());
		
		//Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return dto;
	}
	
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		
		setarCampos(entity, dto);
		
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
	}
	
	private void setarCampos(Client entity, ClientDTO dto) {
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
}
