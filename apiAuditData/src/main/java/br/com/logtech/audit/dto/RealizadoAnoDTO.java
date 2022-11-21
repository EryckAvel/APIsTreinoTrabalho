package br.com.logtech.audit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RealizadoAnoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	private Boolean total;
	private List<RealizadoAnoValoresDTO> valores = new ArrayList<>();

	public RealizadoAnoDTO() {		
	}

	public RealizadoAnoDTO(String codigo, String descricao, Boolean total, List<RealizadoAnoValoresDTO> valores) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.total = total;
		this.valores = valores;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getTotal() {
		return total;
	}

	public void setTotal(Boolean total) {
		this.total = total;
	}

	public List<RealizadoAnoValoresDTO> getValores() {
		return valores;
	}

	public void setValores(List<RealizadoAnoValoresDTO> valores) {
		this.valores = valores;
	}

}
