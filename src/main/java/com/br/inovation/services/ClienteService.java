package com.br.inovation.services;

import com.br.inovation.dto.ClienteDTO;
import com.br.inovation.models.Cliente;
import com.br.inovation.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }
    @Transactional
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
