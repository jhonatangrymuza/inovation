package com.br.inovation.rest;

import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.Cliente;
import com.br.inovation.models.Usuario;
import com.br.inovation.services.TokenService;
import com.br.inovation.services.UsuarioService;
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
public class LoginRest {

    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public String getById(@RequestBody @Valid LoginDTO data) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.getToken((Usuario) auth.getPrincipal());

        return token;
    }
}
