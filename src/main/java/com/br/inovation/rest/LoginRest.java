package com.br.inovation.rest;

import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.Usuario;
import com.br.inovation.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        SecurityContextHolder.getContext().setAuthentication(usernamePassword);

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.getToken((Usuario) auth.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(usernamePassword);
        return token;


    }
}
