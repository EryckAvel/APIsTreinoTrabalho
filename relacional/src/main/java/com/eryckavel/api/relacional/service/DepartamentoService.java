package com.eryckavel.api.relacional.service;

import com.eryckavel.api.relacional.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

}
