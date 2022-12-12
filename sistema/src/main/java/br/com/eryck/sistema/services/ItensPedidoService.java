package br.com.eryck.sistema.services;

import br.com.eryck.sistema.repository.ItensPedidoRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItensPedidoService {

    @Autowired
    ItensPedidoRepsitory itensPedidoRepsitory;

}
