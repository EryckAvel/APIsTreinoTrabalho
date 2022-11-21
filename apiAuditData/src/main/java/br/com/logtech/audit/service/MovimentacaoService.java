package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.dto.PlanoContaMovimentoDTO;
import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.Movimentacao;
import br.com.logtech.audit.model.PlanoConta;
import br.com.logtech.audit.projection.PlanoContaValoresProjection;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.MovimentacaoRepository;
import br.com.logtech.audit.repository.PlanoContaRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;
	@Autowired
	private PlanoContaRepository planoRepo;

	@Transactional
	public void save(MultipartFile file, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "Movimentacao", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<Movimentacao> movimentacao = ExcelHelper.excelToMovimentacao(file.getInputStream(), ano, arquivo);
			for (Movimentacao entity : movimentacao) {
				Movimentacao newEntity = repo.findByAnoAndMesAndPlano(ano, entity.getMes(), entity.getPlano());
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

	public List<PlanoContaMovimentoDTO> movimentacao(Integer ano) {
		List<PlanoContaMovimentoDTO> listResultDTO = new ArrayList<>();
		List<PlanoConta> listPlano = planoRepo.findAll();
		for (PlanoConta plano : listPlano) {
			List<PlanoContaValoresProjection> valoresPlano = repo.findMovByAnoCodigo(ano, plano.getCodigo());
			PlanoContaMovimentoDTO entity = new PlanoContaMovimentoDTO(plano.getCodigo(), plano.getCodigoPai(),
					plano.getDescricao(), valoresPlano);
			listResultDTO.add(entity);
		}
		return listResultDTO;
	}

}
