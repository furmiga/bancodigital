package br.com.bancodigital.gestordecontas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="TIPO_PRODUTO_FINANCEIRO")
public class TipoProdutoFinanceiro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIPO_PRODUTO_FINANCEIRO_ID")
	private Integer tipoProdutoFinanceiroId;
	
	@Column(name = "DESCRICAO",  nullable = false)
	@NotNull
	@Size(min = 1, max = 20)
	private String descricao;

	public Integer getTipoProdutoFinanceiroId() {
		return tipoProdutoFinanceiroId;
	}

	public void setTipoProdutoFinanceiroId(Integer tipoProdutoFinanceiroId) {
		this.tipoProdutoFinanceiroId = tipoProdutoFinanceiroId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
