package com.vanbarone.vbcadcli.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanbarone.vbcadcli.dto.ClientDTO;
import com.vanbarone.vbcadcli.entities.Client;
import com.vanbarone.vbcadcli.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		List<Client> list = repo.findAll();
		
		List<ClientDTO> listDTO = new ArrayList<>();
		
		listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repo.findById(id);
		
		ClientDTO dto = new ClientDTO(obj.get());
		
		//Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return dto;
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		
		setarCampos(entity, dto);
		
		entity = repo.save(entity);
		
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		//try {
			Client entity = repo.getOne(id);
			
			setarCampos(entity, dto);
			
			entity = repo.save(entity);
			
			return new ClientDTO(entity);
			
		//} catch (EntityNotFoundException e) {
		//	throw new ResourceNotFoundException("Id not found " + id);
		//}
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	private void setarCampos(Client entity, ClientDTO dto) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
}
