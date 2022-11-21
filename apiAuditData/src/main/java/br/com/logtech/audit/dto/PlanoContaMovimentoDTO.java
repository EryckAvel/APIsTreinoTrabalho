package br.com.logtech.audit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.logtech.audit.projection.PlanoContaValoresProjection;

public class PlanoContaMovimentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String codigoPai;
	private String descricao;
	private List<PlanoContaValoresDTO> valores = new ArrayList<>();

	public PlanoContaMovimentoDTO() {

	}

	public PlanoContaMovimentoDTO(String codigo, String codigoPai, String descricao,
			List<PlanoContaValoresProjection> valores) {
		super();
		this.codigo = codigo;
		this.codigoPai = codigoPai;
		this.descricao = descricao;
		valores.forEach(
				val -> this.valores.add(new PlanoContaValoresDTO(val.getMes(), val.getPrevisto(), val.getRealizado())));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPai() {
		return codigoPai;
	}

	public void setCodigoPai(String codigoPai) {
		this.codigoPai = codigoPai;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<PlanoContaValoresDTO> getValores() {
		return valores;
	}

	public void setValores(List<PlanoContaValoresDTO> valores) {
		this.valores = valores;
	}

}
