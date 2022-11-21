package br.com.logtech.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.Arquivos;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivos, Long>{

}
