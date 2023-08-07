package com.br.inovation.controller;

import com.br.inovation.dto.EntregaDTO;
import com.br.inovation.models.Entrega;
import com.br.inovation.models.Pedido;
import com.br.inovation.services.EntregaService;
import com.br.inovation.services.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    @Autowired
    EntregaService service;

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<Entrega> entrega  = service.findById(id);
        if(!entrega.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega informado não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(entrega.get());
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody @Valid EntregaDTO dto) {
        try{
            Optional<Pedido> pedido  = pedidoService.findById(dto.getPedido().getId());
            if(!pedido.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido informado não existe");
            }

            Entrega entrega = new Entrega();
            BeanUtils.copyProperties(dto, entrega);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entrega));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @PutMapping("/{id}")

    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody @Valid EntregaDTO dto) {
        try{
            Optional<Pedido> pedido  = pedidoService.findById(dto.getPedido().getId());
            if(!pedido.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido informado não existe");
            }

            Optional<Entrega> entrega  = service.findById(id);
            if(!pedido.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega informada não existe");
            }
            Entrega upEntrega = entrega.get();
            upEntrega.setEndereco(dto.getEndereco());
            upEntrega.setPedido(pedido.get());

            return ResponseEntity.status(HttpStatus.OK).body(service.save(upEntrega));
        } catch (Exception e){
            throw new RuntimeException("Ocorreu erros ao processar a requisicao" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Entrega> entrega  = service.findById(id);
        if(!entrega.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega informada não existe");
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Entrega deletado com sucesso!");
    }
}
