package br.com.erudio.apigateway.services;

import br.com.erudio.apigateway.exception.ResouceNotFoundException;
import br.com.erudio.apigateway.model.Pessoa;
import br.com.erudio.apigateway.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PessoaServices {

    private Logger logger = Logger.getLogger(PessoaServices.class.getName());

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa findById(Long id){
        logger.info("Procurando uma pessoa!");

        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Não existe nada gravado nesse ID"));
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa create(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa){
        var entity = pessoaRepository.findById(pessoa.getId())
                .orElseThrow(() -> new ResouceNotFoundException("Não existe nada gravado nesse ID"));
        entity.setPrimeiroNome(pessoa.getPrimeiroNome());
        entity.setUltimoNome(pessoa.getUltimoNome());
        entity.setEndereco(pessoa.getEndereco());
        entity.setGenero(pessoa.getGenero());
        return pessoaRepository.save(pessoa);
    }

    public void delete(Long id){
        var entity = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Não existe nada gravado nesse ID"));
        pessoaRepository.delete(entity);
    }

}
