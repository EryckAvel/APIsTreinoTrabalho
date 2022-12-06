package br.com.eryck.sistema.services;

import br.com.eryck.sistema.model.Cliente;
import br.com.eryck.sistema.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Object save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(UUID id) {
        return clienteRepository.findById(id);
    }
}
