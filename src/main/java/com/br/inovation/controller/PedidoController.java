package com.br.inovation.controller;


import com.br.inovation.dto.PedidoDTO;
import com.br.inovation.models.Cliente;
import com.br.inovation.models.Pedido;
import com.br.inovation.services.ClienteService;
import com.br.inovation.services.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService service;
    @Autowired
    ClienteService clienteService;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<Pedido> pedido  = service.findById(id);
        if(!pedido.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido informado não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody @Valid PedidoDTO dto) {
        try{
            Optional<Cliente> cliente  = clienteService.findById(dto.getCliente().getId());
            if(!cliente.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
            }

            Pedido pedido = new Pedido();
            BeanUtils.copyProperties(dto, pedido);
            pedido.setCliente(cliente.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pedido));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody @Valid PedidoDTO dto) {
        try {
            Optional<Cliente> cliente  = clienteService.findById(dto.getCliente().getId());
            if(!cliente.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente informado não existe");
            }

            Optional<Pedido> pedido  = service.findById(id);
            if(!pedido.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido informado não existe");
            }
            Pedido upPedido = pedido.get();
            upPedido.setDescricao(dto.getDescricao());
            upPedido.setCliente(cliente.get());

            return ResponseEntity.status(HttpStatus.OK).body(service.save(upPedido));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<Pedido> pedido  = service.findById(id);
        if(!pedido.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido informado não existe");
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");

    }
}
