package br.com.logtech.audit.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_meta_mes")
public class MetaMes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String descricao;
	private Double valor;
	private Integer ano;
	private Integer mes;
	private Instant createdAt;
	private Instant updatedAt;
	@ManyToOne
	@JoinColumn(name = "arquivo_id")
	private Arquivos arquivo;
	
	public MetaMes() {
	}
	
	public MetaMes(Integer id, String codigo, String descricao, Integer ano, Integer mes, Double valor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.ano = ano;
		this.mes = mes;	
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MetaMes other = (MetaMes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	public Instant getUpdatedAt() {
		return updatedAt;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "MetaMes [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + ", ano="
				+ ano + ", mes=" + mes + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", arquivo="
				+ arquivo + "]";
	}
	
}
