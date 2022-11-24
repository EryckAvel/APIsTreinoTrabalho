package br.com.erudio.apigateway.services;

import br.com.erudio.apigateway.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PessoaServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PessoaServices.class.getName());

    public Pessoa findById(String id){
        logger.info("Procurando uma pessoa!");
        Pessoa pessoa = new Pessoa();
        pessoa.setId(counter.incrementAndGet());
        pessoa.setPrimeiroNome("Eryck");
        pessoa.setUltimoNome("Avelino");
        pessoa.setEndereco("Av E");
        pessoa.setGenero("Masculino");
        return pessoa;
    }

    public List<Pessoa> findAll(){
        List<Pessoa> pessoas = new ArrayList<>();
        for (int i = 0; i < 8; i++){
           Pessoa pessoa = mockPerson(i);
           pessoas.add(pessoa);
        }
        return pessoas;
    }

    private Pessoa mockPerson(int i) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(counter.incrementAndGet());
        pessoa.setPrimeiroNome("Nome da pessoa " + i);
        pessoa.setUltimoNome("Utilmo nome da pessoa" + i);
        pessoa.setEndereco("Endereço " + i);
        pessoa.setGenero("Genero " + i);
        return pessoa;
    }

    public Pessoa create(Pessoa pessoa){

        return pessoa;
    }

    public Pessoa update(Pessoa pessoa){
        return pessoa;
    }

    public void delete(String id){

    }

}
