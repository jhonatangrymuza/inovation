package com.br.inovation.rest;

import com.br.inovation.dto.ClienteDTO;
import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.Cliente;
import com.br.inovation.models.Usuario;
import com.br.inovation.services.ClienteService;
import com.br.inovation.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

    @Autowired
    UsuarioService service;

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody @Valid LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

}
