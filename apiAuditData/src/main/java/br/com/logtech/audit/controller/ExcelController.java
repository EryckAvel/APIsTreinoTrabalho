package br.com.logtech.audit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.dto.PlanoContaMovimentoDTO;
import br.com.logtech.audit.dto.RealizadoAnoDTO;
import br.com.logtech.audit.helper.ExcelHelper;
import br.com.logtech.audit.message.ResponseMessage;
import br.com.logtech.audit.service.EvolucaoService;
import br.com.logtech.audit.service.MetaAnoService;
import br.com.logtech.audit.service.MetaMesService;
import br.com.logtech.audit.service.MovimentacaoPrevistaService;
import br.com.logtech.audit.service.MovimentacaoService;
import br.com.logtech.audit.service.PlanoContaService;
import br.com.logtech.audit.service.PremissaFinanceiraService;
import br.com.logtech.audit.service.PremissaPercentualService;
import br.com.logtech.audit.service.RealizadoMesAnoService;


@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

	@Autowired
	private PlanoContaService planoService;
	@Autowired
	private MetaMesService metaMesService;
	@Autowired
	private MetaAnoService metaAnoService;
	@Autowired
	private EvolucaoService evolucaoService;
	@Autowired
	private PremissaFinanceiraService premFinanceiroService;
	@Autowired
	private PremissaPercentualService premPercentualService;
	@Autowired
	private MovimentacaoService movService;
	@Autowired
	private RealizadoMesAnoService realizadoMesAnoService;
	@Autowired
	private MovimentacaoPrevistaService movPrevService;
	
	@PostMapping("/upload/{tipo}")
	public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String tipo, @RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				switch(tipo) {
					case "planoContas":
						planoService.save(file);
						break;
					default:
						//fileService.save(file);
						break;
				}				
				message = "Arquivo importado com sucesso: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Não foi possível subir o arquivo: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Por favor, informe um documento excel!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@PostMapping("/upload/{tipo}/{mes}/{ano}")
	public ResponseEntity<ResponseMessage> uploadFileMesAno(@PathVariable String tipo, 
			@PathVariable Integer mes, @PathVariable Integer ano,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				switch(tipo) {
					case "metaMes":
						metaMesService.save(file, mes, ano);
						break;
					case "realizadoMesAno":
						realizadoMesAnoService.save(file, mes, ano);
						break;
					default:
						//fileService.save(file);
						break;
				}				
				message = "Arquivo importado com sucesso: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Não foi possível subir o arquivo: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Por favor, informe um documento excel!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@PostMapping("/upload/{tipo}/{ano}")
	public ResponseEntity<ResponseMessage> uploadFileAno(@PathVariable String tipo, 
			@PathVariable Integer ano,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				switch(tipo) {
					case "metaAno":
						metaAnoService.save(file, ano);
						break;
					case "evolucao":
						evolucaoService.save(file, ano);
						break;
					case "premFinanceiro":
						premFinanceiroService.save(file, ano);
						break;
					case "premPercentual":
						premPercentualService.save(file, ano);
						break;
					case "movimentacao":
						movService.save(file, ano);
						break;
					case  "movimentacaoPrevista":
						movPrevService.save(file, ano);
						break;
					default:
						//fileService.save(file);
						break;
				}				
				message = "Arquivo importado com sucesso: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Não foi possível subir o arquivo: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Por favor, informe um documento excel!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@GetMapping(value = "/movimentacao/plano/{ano}")
	public ResponseEntity<List<PlanoContaMovimentoDTO>> movimentacaoPlano (@PathVariable Integer ano){
		List<PlanoContaMovimentoDTO> list = movService.movimentacao(ano);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping(value = "/realizacao/{ano}")
	public ResponseEntity<List<RealizadoAnoDTO>> realizacaoAno (@PathVariable Integer ano){
		List<RealizadoAnoDTO> list = realizadoMesAnoService.realizacaoAno(ano);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}

