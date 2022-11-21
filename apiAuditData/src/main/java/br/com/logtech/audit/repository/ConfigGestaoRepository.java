package br.com.logtech.audit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.ConfigGestao;

@Repository
public interface ConfigGestaoRepository extends JpaRepository<ConfigGestao, Integer>{
	
	List<ConfigGestao> findByGrupo(Integer grupo);
}
