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
@Table(name = "tb_movimentacao_prevista")
public class MovimentacaoPrevista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer mes;
	private Integer ano;
	private Double valor;
	@ManyToOne
	@JoinColumn(name = "plano_codigo")
	private PlanoConta plano;
	@ManyToOne
	@JoinColumn(name = "arquivo_id")
	private Arquivos arquivo;
	private Instant createdAt;
	private Instant updatedAt;
	
	public MovimentacaoPrevista() {
	}

	public MovimentacaoPrevista(Long id, Integer mes, Integer ano, Double valor, PlanoConta plano, Arquivos arquivo) {
		super();
		this.id = id;
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
		this.plano = plano;
		this.arquivo = arquivo;
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
		MovimentacaoPrevista other = (MovimentacaoPrevista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public PlanoConta getPlano() {
		return plano;
	}

	public void setPlano(PlanoConta plano) {
		this.plano = plano;
	}

	public Arquivos getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivos arquivo) {
		this.arquivo = arquivo;
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

	@Override
	public String toString() {
		return "MovimentacaoPrevista [id=" + id + ", mes=" + mes + ", ano=" + ano + ", valor=" + valor + ", plano=" + plano
				+ ", arquivo=" + arquivo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
