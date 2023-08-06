package com.br.inovation.rest;

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
public class ClienteRest {

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
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody @Valid ClienteDTO dto) {
        Optional<Cliente> cliente  = service.findById(id);
        if(!cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
        }
        Cliente upCliente = cliente.get();
        upCliente.setNome(dto.getNome());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(upCliente));
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
