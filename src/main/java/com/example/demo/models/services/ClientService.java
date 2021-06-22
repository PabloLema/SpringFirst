package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Client;
import com.example.demo.modes.dao.IClientDAO;

@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientDAO clientDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		// TODO Auto-generated method stub
		return clientDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return clientDAO.save(client);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		clientDAO.deleteById(id);
	}

}
