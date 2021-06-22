package com.example.demo.controllers;

import java.util.HashMap;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entity.Client;
import com.example.demo.models.services.IClientService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ClientResController {

	@Autowired
	private IClientService clientService;

	@GetMapping("/clients")
	@ResponseStatus(HttpStatus.OK)
	public List<Client> index() {
		return clientService.findAll();
	}

//	@GetMapping("/client/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public Client show(@PathVariable Long id) {
//		return clientService.findById(id);
//	}
	@GetMapping("/client/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		try {
			client = clientService.findById(id);
		} catch (Exception e) {
			response.put("message", "Error");
			response.put("error", e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (client == null) {
			response.put("message", "Client id: ".concat(id.toString().concat(" doesn't exist!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
//		return clientService.findById(id);
	}

	@PostMapping("/client")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {
//		client.setCreatedAT(new Date());
		return clientService.save(client);
	}

	@PutMapping("/client/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client, @PathVariable Long id) {
//		client.setCreatedAT(new Date());
		Client uClient = clientService.findById(id);
		uClient.setApellido(client.getApellido());
		uClient.setNombre(client.getNombre());
		uClient.setEmail(client.getEmail());
		return clientService.save(uClient);
	}

	@DeleteMapping("/client/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		clientService.deleteById(id);
	}


}
