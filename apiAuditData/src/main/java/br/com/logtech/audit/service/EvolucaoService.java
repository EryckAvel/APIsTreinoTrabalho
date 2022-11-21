package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.Evolucao;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.EvolucaoRepository;

@Service
public class EvolucaoService {

	@Autowired
	private EvolucaoRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;

	@Transactional
	public void save(MultipartFile file, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "Evolucao", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<Evolucao> evolucao = ExcelHelper.excelToEvolucao(file.getInputStream(), ano, arquivo);
			for (Evolucao entity: evolucao) {
				Evolucao newEntity = repo.findByAnoAndCodigo(ano, entity.getCodigo());
				if (newEntity != null && newEntity.getId() > 0) {
					newEntity.setMeta(entity.getMeta());
					newEntity.setArquivo(arquivo);
				} else {
					newEntity.setAno(ano);
					newEntity.setArquivo(arquivo);
					newEntity.setCodigo(entity.getCodigo());
					newEntity.setDescricao(entity.getDescricao());
					newEntity.setMeta(entity.getMeta());
				}
				repo.save(newEntity);
			}
			//repo.saveAll(evolucao);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}
}
