package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.MovimentacaoPrevista;
import br.com.logtech.audit.model.PlanoConta;

@Repository
public interface MovimentacaoPrevistaRepository extends JpaRepository<MovimentacaoPrevista, Long>{

	MovimentacaoPrevista findByAnoAndMesAndPlano(Integer ano, Integer mes, PlanoConta plano);

}
