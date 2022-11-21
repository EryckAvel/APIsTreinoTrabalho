package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.logtech.audit.model.MetaAno;

@Repository
public interface MetaAnoRepository extends JpaRepository<MetaAno, Long>{

	@Transactional(readOnly = true)
	MetaAno findByAnoAndCodigo(Integer ano, String codigo);

}
