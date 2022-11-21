package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.PremissaPercentual;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.PremissaPercentualRepository;

@Service
public class PremissaPercentualService {

	@Autowired
	private PremissaPercentualRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;

	@Transactional
	public void save(MultipartFile file, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "PremPercentual", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<PremissaPercentual> premissa = ExcelHelper.excelToPremissaPercentual(file.getInputStream(), ano,
					arquivo);
			for (PremissaPercentual entity: premissa) {
				PremissaPercentual newEntity = repo.findByAnoAndCodigo(ano, entity.getCodigo());
				if (newEntity != null && newEntity.getId() > 0) {
					newEntity.setValor(entity.getValor());
					newEntity.setArquivo(arquivo);
				} else {
					newEntity.setAno(ano);
					newEntity.setArquivo(arquivo);
					newEntity.setCodigo(entity.getCodigo());
					newEntity.setDescricao(entity.getDescricao());
					newEntity.setValor(entity.getValor());
				}
				repo.save(newEntity);
			}
			//repo.saveAll(premissa);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}
}
