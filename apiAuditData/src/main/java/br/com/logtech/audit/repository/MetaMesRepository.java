package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.logtech.audit.model.MetaMes;

@Repository
public interface MetaMesRepository extends JpaRepository<MetaMes, Long>{

	@Transactional(readOnly = true)
	MetaMes findByMesAndAnoAndCodigo(Integer mes, Integer ano, String codigo);

}
