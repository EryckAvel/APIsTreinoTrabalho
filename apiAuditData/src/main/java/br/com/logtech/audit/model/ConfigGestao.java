package br.com.logtech.audit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_config_gestao")
public class ConfigGestao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String descricao;
	private Integer grupo;
	private Boolean soma;
	private Boolean diminui;
	private Boolean total;
	private String formula;

	public ConfigGestao() {

	}

	public ConfigGestao(Integer id, String codigo, String descricao, Integer grupo, Boolean soma, Boolean diminui,
			Boolean total, String formula) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.grupo = grupo;
		this.soma = soma;
		this.diminui = diminui;
		this.total = total;
		this.formula = formula;
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
		ConfigGestao other = (ConfigGestao) obj;
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

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Boolean getSoma() {
		return soma;
	}

	public void setSoma(Boolean soma) {
		this.soma = soma;
	}

	public Boolean getDiminui() {
		return diminui;
	}

	public void setDiminui(Boolean diminui) {
		this.diminui = diminui;
	}

	public Boolean getTotal() {
		return total;
	}

	public void setTotal(Boolean total) {
		this.total = total;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}