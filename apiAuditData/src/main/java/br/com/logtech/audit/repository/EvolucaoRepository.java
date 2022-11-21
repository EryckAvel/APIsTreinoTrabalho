package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.Evolucao;

@Repository
public interface EvolucaoRepository extends JpaRepository<Evolucao, Integer>{

	Evolucao findByAnoAndCodigo(Integer ano, String codigo);

}
