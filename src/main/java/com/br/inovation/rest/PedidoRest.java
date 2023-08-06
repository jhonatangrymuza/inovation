package com.br.inovation.rest;

import com.br.inovation.models.Cliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoRest {

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable String id) {
        return null;
    }

    @PostMapping()
    public Cliente post() {
        return null;
    }

    @PutMapping("/{id}")
    public Cliente put(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Cliente delete(@PathVariable String id) {
        return null;
    }
}
