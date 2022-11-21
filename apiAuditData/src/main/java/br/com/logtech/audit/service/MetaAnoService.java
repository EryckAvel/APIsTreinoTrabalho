package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.MetaAno;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.MetaAnoRepository;

@Service
public class MetaAnoService {

	@Autowired
	private MetaAnoRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;

	@Transactional
	public void save(MultipartFile file, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "MetaAno", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<MetaAno> metaAno = ExcelHelper.excelToMetaAno(file.getInputStream(), ano, arquivo);
			for (MetaAno meta : metaAno) {
				MetaAno newMeta = repo.findByAnoAndCodigo(ano, meta.getCodigo());
				if (newMeta != null && newMeta.getId() > 0) {
					newMeta.setValor(meta.getValor());
					newMeta.setArquivo(arquivo);
				} else {
					newMeta.setAno(ano);
					newMeta.setArquivo(arquivo);
					newMeta.setCodigo(meta.getCodigo());
					newMeta.setDescricao(meta.getDescricao());
					newMeta.setValor(meta.getValor());
				}
				repo.save(newMeta);
			}
			//repo.saveAll(metaAno);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}
}
