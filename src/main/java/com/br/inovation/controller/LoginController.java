package com.br.inovation.controller;

import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.BearerModel;
import com.br.inovation.models.Usuario;
import com.br.inovation.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<Object> getById(@RequestBody @Valid LoginDTO data) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.getToken((Usuario) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.CREATED).body(new BearerModel(token));


    }
}
