package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.PlanoConta;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.PlanoContaRepository;

@Service
public class PlanoContaService {

	@Autowired
	private PlanoContaRepository repo;
	@Autowired 
	private ArquivoRepository arquivoRepo;

	public void save(MultipartFile file) {
		try {
			Arquivos arquivo = new Arquivos(null, "PlanoConta", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<PlanoConta> planoConta = ExcelHelper.excelToPlanoConta(file.getInputStream(), arquivo);
			repo.saveAll(planoConta);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}
	
}
