package com.br.inovation.rest;

import com.br.inovation.models.Cliente;
import com.br.inovation.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {

    @Autowired
    ClienteService service;

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable String id) {
        //service.save();

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
