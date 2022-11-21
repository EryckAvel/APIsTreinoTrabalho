package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.MovimentacaoPrevista;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.MovimentacaoPrevistaRepository;

@Service
public class MovimentacaoPrevistaService {

	@Autowired
	private MovimentacaoPrevistaRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;

	@Transactional
	public void save(MultipartFile file, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "MovimentacaoPrevista", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<MovimentacaoPrevista> movimentacao = ExcelHelper.excelToMovimentacaoPrevista(file.getInputStream(), ano, arquivo);
			for (MovimentacaoPrevista entity : movimentacao) {
				MovimentacaoPrevista newEntity = repo.findByAnoAndMesAndPlano(ano, entity.getMes(), entity.getPlano());
				if (newEntity != null && newEntity.getId() > 0) {
					newEntity.setValor(entity.getValor());
					newEntity.setArquivo(arquivo);
				} else {
					newEntity.setAno(ano);
					newEntity.setArquivo(arquivo);
					newEntity.setPlano(entity.getPlano());
					newEntity.setMes(entity.getMes());
					newEntity.setValor(entity.getValor());
				}
				repo.save(newEntity);
			}
			//repo.saveAll(movimentacao);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}
}
