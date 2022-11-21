package br.com.logtech.audit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.dto.RealizadoAnoDTO;
import br.com.logtech.audit.dto.RealizadoAnoValoresDTO;
import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.ConfigGestao;
import br.com.logtech.audit.model.RealizadoMesAno;
import br.com.logtech.audit.projection.RealizadoAnoProjection;
import br.com.logtech.audit.repository.ArquivoRepository;
import br.com.logtech.audit.repository.ConfigGestaoRepository;
import br.com.logtech.audit.repository.RealizadoMesAnoRepository;

@Service
public class RealizadoMesAnoService {

	@Autowired
	private RealizadoMesAnoRepository repo;
	@Autowired
	private ArquivoRepository arquivoRepo;
	@Autowired
	private ConfigGestaoRepository configRepo;

	@Transactional
	public void save(MultipartFile file, Integer mes, Integer ano) {
		try {
			Arquivos arquivo = new Arquivos(null, "RealziadoMesAno", file.getOriginalFilename());
			arquivoRepo.save(arquivo);
			List<RealizadoMesAno> realizado = ExcelHelper.excelToRealizadoMesAno(file.getInputStream(), mes, ano,
					arquivo);
			for (RealizadoMesAno realMes: realizado) {
				RealizadoMesAno newRealizado = repo.findByMesAndAnoAndCodigo(mes, ano, realMes.getCodigo());
				if (newRealizado != null && newRealizado.getId() > 0) {
					newRealizado.setValor(realMes.getValor());
					newRealizado.setArquivo(arquivo);
				} else {
					newRealizado.setAno(ano);
					newRealizado.setArquivo(arquivo);
					newRealizado.setCodigo(realMes.getCodigo());
					newRealizado.setDescricao(realMes.getDescricao());
					newRealizado.setMes(mes);
					newRealizado.setValor(realMes.getValor());
				}
				repo.save(newRealizado);
			}
			//repo.saveAll(realizado);
		} catch (IOException e) {
			throw new RuntimeException("falha ao gravar excel: " + e.getMessage());
		}
	}

	public List<RealizadoAnoDTO> realizacaoAno(Integer ano) {
		List<RealizadoAnoDTO> listResultDTO = new ArrayList<>();
		List<ConfigGestao> listDTO = configRepo.findAll();
		for (ConfigGestao config : listDTO) {
			RealizadoAnoDTO realMes = new RealizadoAnoDTO();
			realMes.setCodigo(config.getCodigo());
			realMes.setDescricao(config.getDescricao());
			realMes.setTotal(config.getTotal());
			List<RealizadoAnoValoresDTO> listValores = new ArrayList<>();
			for (int i = 1; i < 13; i++) {
				if (config.getTotal()) {
					if (config.getGrupo() == 3) {
						RealizadoAnoProjection proj = repo.SaldoEntradaSaidaByMesAnoCodigo(ano, i);
						RealizadoAnoValoresDTO valores = new RealizadoAnoValoresDTO(i, ano, proj.getPrevisto(),
								proj.getRealizado());
						listValores.add(valores);
					} else if (config.getGrupo() == 5) {
						RealizadoAnoProjection proj = repo.SaldoEntradaSaidaByMesAnoCodigo(ano, i);
						RealizadoAnoProjection projEmp = repo.SomaRealizadoByMesAnoCodigo(ano, i, 4);
						RealizadoAnoValoresDTO valores = new RealizadoAnoValoresDTO(i, ano, 
								(proj.getPrevisto() + projEmp.getPrevisto()),
								(proj.getRealizado()+ projEmp.getRealizado()));
						listValores.add(valores);
					} else {
						RealizadoAnoProjection proj = repo.SomaRealizadoByMesAnoCodigo(ano, i, config.getGrupo());
						RealizadoAnoValoresDTO valores = new RealizadoAnoValoresDTO(i, ano, proj.getPrevisto(),
								proj.getRealizado());
						listValores.add(valores);
					}
				} else {
					RealizadoAnoProjection proj = repo.findRealziadoByMesAnoCodigo(ano, i, config.getCodigo());
					RealizadoAnoValoresDTO valores = new RealizadoAnoValoresDTO(i, ano, proj.getPrevisto(),
							proj.getRealizado());
					listValores.add(valores);
				}
			}
			realMes.setValores(listValores);
			listResultDTO.add(realMes);
		}
		return listResultDTO;
	}
}
