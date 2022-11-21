package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.PremissaFinanceira;

@Repository
public interface PremissaFinanceiraRepository extends JpaRepository<PremissaFinanceira, Integer>{

	PremissaFinanceira findByAnoAndCodigo(Integer ano, String codigo);

}
