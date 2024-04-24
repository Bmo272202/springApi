package com.bmo.ec.controller;

import com.bmo.ec.model.dto.ClienteDto;
import com.bmo.ec.model.entity.Cliente;
import com.bmo.ec.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto create(@RequestBody ClienteDto clienteDto){
        Cliente clienteSave = clienteService.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombre())
                .apellido(clienteSave.getApellido())
                .correo(clienteSave.getCorreo())
                .fechaDeRegistro(clienteSave.getFechaDeRegistro())
                .build();
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(@RequestBody ClienteDto clienteDto){
        Cliente clienteUpdate = clienteService.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .correo(clienteUpdate.getCorreo())
                .fechaDeRegistro(clienteUpdate.getFechaDeRegistro())
                .build();
    }

    //metodo delete normal sin excepciones personalizadas
    /*
    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteService.findById(id);
        clienteService.delete(clienteDelete);
    }
    */

    //metoto delete con excepciones personalizadas configurando la respuesta http completa a nuestro gusto
    //este ejemplo es una forma cruda y basica ya que no está completamente estructurada
    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDT){
            response.put("mensaje", exDT.getMessage());
            response.put("cliente", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Error de la base datos
        }
    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaDeRegistro(cliente.getFechaDeRegistro())
                .build();
    }
}
