package com.bmo.ec.service;

import com.bmo.ec.model.dto.ClienteDto;
import com.bmo.ec.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}
