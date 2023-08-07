package com.br.inovation.services;

import com.br.inovation.models.Pedido;

import com.br.inovation.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository repository;

    @Transactional
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }
    @Transactional
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
