package com.bmo.ec.model.dao;

import com.bmo.ec.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
