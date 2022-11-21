package br.com.logtech.audit.dto;

import java.io.Serializable;

public class PlanoContaValoresDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer mes;
	private Double previsto;
	private Double realizado;

	public PlanoContaValoresDTO() {
	}

	public PlanoContaValoresDTO(Integer mes, Double previsto, Double realizado) {
		super();
		this.mes = mes;
		this.previsto = previsto;
		this.realizado = realizado;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Double getPrevisto() {
		return previsto;
	}

	public void setPrevisto(Double previsto) {
		this.previsto = previsto;
	}

	public Double getRealizado() {
		return realizado;
	}

	public void setRealizado(Double realizado) {
		this.realizado = realizado;
	}

}
