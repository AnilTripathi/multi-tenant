package com.multi.schema.clients.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.schema.clients.dto.ClientDto;
import com.multi.schema.clients.entity.client.Clients;
import com.multi.schema.clients.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Clients> findAllClients() {
		List<Clients> clientList = clientRepository.findAll();
		return clientList;
	}

	public Clients findClientById(String id) {
		Clients client = clientRepository.findById(id).orElse(null);
		return client;
	}

	@Transactional
	public Clients saveClients(ClientDto.ClientRequest payload) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		Clients clObj = Clients.builder().createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).id(id)
				.name(payload.getName()).email(payload.getEmail()).mobileNumber(payload.getMobileNumber()).build();
		Clients clientObj = clientRepository.save(clObj);
		clientRepository.createSchema(id);
		log.info("Schema Created Successfully");
		return clientObj;
	}
}
