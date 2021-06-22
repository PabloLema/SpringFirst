package com.example.demo.modes.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Client;

public interface IClientDAO extends CrudRepository<Client, Long> {


}
