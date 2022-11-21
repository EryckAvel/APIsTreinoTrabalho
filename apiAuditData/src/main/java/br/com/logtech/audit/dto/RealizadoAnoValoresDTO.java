package br.com.logtech.audit.dto;

import java.io.Serializable;

public class RealizadoAnoValoresDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer mes;
	private Integer ano;
	private Double previsto;
	private Double realizado;

	public RealizadoAnoValoresDTO() {

	}

	public RealizadoAnoValoresDTO(Integer mes, Integer ano, Double previsto, Double realizado) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.previsto = previsto;
		this.realizado = realizado;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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
