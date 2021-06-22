package com.example.demo.models.services;

import java.util.List;

import com.example.demo.models.entity.Client;

public interface IClientService {
	public List<Client> findAll();

	public Client findById(Long id);

	public Client save(Client client);

	public void deleteById(Long id);
}
