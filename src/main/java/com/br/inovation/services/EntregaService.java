package com.br.inovation.services;

import com.br.inovation.models.Entrega;
import com.br.inovation.repositories.EntregaRepository;
import com.br.inovation.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    EntregaRepository repository;

    @Transactional
    public Entrega save(Entrega cliente) {
        return repository.save(cliente);
    }
    @Transactional
    public Optional<Entrega> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
