package br.com.logtech.audit.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class PlanoConta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codigo;
	private String codigoPai;
	private String descricao;
	private Instant createdAt;
	private Instant updatedAt;
	@ManyToOne
	@JoinColumn(name = "arquivo_id")
	private Arquivos arquivo;
	
	public PlanoConta() {
	}

	public PlanoConta(String codigo, String codigoPai, String descricao) {
		super();
		this.codigo = codigo;
		this.codigoPai = codigoPai;
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoConta other = (PlanoConta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}
	
	public Arquivos getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivos arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return "PlanoConta [codigo=" + codigo + ", codigoPai=" + codigoPai + ", descricao=" + descricao
				+ "]";
	}
	
}
