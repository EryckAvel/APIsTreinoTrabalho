package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.PremissaPercentual;

@Repository
public interface PremissaPercentualRepository extends JpaRepository<PremissaPercentual, Integer>{

	PremissaPercentual findByAnoAndCodigo(Integer ano, String codigo);

}
