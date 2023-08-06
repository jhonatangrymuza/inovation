package com.br.inovation.services;

import com.br.inovation.dto.LoginDTO;
import com.br.inovation.models.Usuario;
import com.br.inovation.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    @Transactional
    public Object save(LoginDTO dto) {
        String encoder = new BCryptPasswordEncoder().encode(dto.getPassword());
        Usuario user = new Usuario();
        user.setLogin(dto.getLogin());
        user.setPassword(encoder);
        return repository.save(user);
    }
}
