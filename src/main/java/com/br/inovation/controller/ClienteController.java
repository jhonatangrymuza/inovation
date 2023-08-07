package com.br.inovation.controller;

import com.br.inovation.dto.ClienteDTO;
import com.br.inovation.models.Cliente;
import com.br.inovation.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) throws Exception {
        Optional<Cliente> cliente  = service.findById(id);
        if(!cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody @Valid ClienteDTO dto) {
        try {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(dto, cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody @Valid ClienteDTO dto) {
        try {
            Optional<Cliente> cliente  = service.findById(id);
            if(!cliente.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
            }
            Cliente upCliente = cliente.get();
            upCliente.setNome(dto.getNome());
            return ResponseEntity.status(HttpStatus.OK).body(service.save(upCliente));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Cliente> cliente  = service.findById(id);
        if(!cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
