package com.br.inovation.services;

import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.Usuario;
import com.br.inovation.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    @Transactional
    public Object save(LoginDTO dto) {
        UserDetails userInDb = repository.findByLogin(dto.getLogin());
        if(Objects.nonNull(userInDb)){
            throw new RuntimeException("Usuario já cadastrado");
        }
        Usuario user = new Usuario();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode((dto.getPassword())));
        return repository.save(user);
    }
}
