package com.vanbarone.vbcadcli.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanbarone.vbcadcli.dto.ClientDTO;
import com.vanbarone.vbcadcli.entities.Client;
import com.vanbarone.vbcadcli.repositories.ClientRepository;
import com.vanbarone.vbcadcli.services.exceptions.ExceptionDatabase;
import com.vanbarone.vbcadcli.services.exceptions.ExceptionEntityNotFound;

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
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> list = repo.findAll(pageRequest);
		
		return list.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repo.findById(id);
		
		Client entity = obj.orElseThrow(() -> new ExceptionEntityNotFound("Entity not found"));
		
		return new ClientDTO(entity);
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
		try {
			Client entity = repo.getReferenceById(id);
			
			setarCampos(entity, dto);
			
			entity = repo.save(entity);
			
			return new ClientDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ExceptionEntityNotFound("Id not found " + id);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ExceptionEntityNotFound("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionDatabase("Integrity violation");
		}
	}
	
	private void setarCampos(Client entity, ClientDTO dto) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
}
