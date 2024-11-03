package com.multi.schema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.schema.clients.dto.ClientDto;
import com.multi.schema.clients.entity.client.Clients;
import com.multi.schema.clients.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@GetMapping
	public List<Clients> getAllClients() {
		return clientService.findAllClients();
	}

	@GetMapping("/{id}")
	public Clients getAllClients(@PathVariable String id) {
		return clientService.findClientById(id);
	}

	@PostMapping
	public Clients saveClient(@RequestBody ClientDto.ClientRequest payload) {
		return clientService.saveClients(payload);
	}

}
