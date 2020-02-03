package br.com.bancodigital.gestordecontas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO_FINANCEIRO")
public class ProdutoFinanceiro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUTO_FINANCEIRO_ID")
	protected Integer produtoFinanceiroId;

	@Column(name = "VALOR")
	protected float valor;
	
	@ManyToOne
	@JoinColumns(@JoinColumn(name = "FK_NUMERO_CONTA", referencedColumnName = "NUMERO_CONTA", nullable = false))
	protected Conta conta;

	@ManyToOne
	@JoinColumn(name = "FK_TIPO_PRODUTO_FINANCEIRO_ID", referencedColumnName = "TIPO_PRODUTO_FINANCEIRO_ID", nullable = false)
	protected TipoProdutoFinanceiro tipoProdutoFinanceiro;
	
	
	public Integer getProdutoFinanceiroId() {
		return produtoFinanceiroId;
	}


	public void setProdutoFinanceiroId(Integer produtoFinanceiroId) {
		this.produtoFinanceiroId = produtoFinanceiroId;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}


	public TipoProdutoFinanceiro getTipoProdutoFinanceiro() {
		return tipoProdutoFinanceiro;
	}


	public void setTipoProdutoFinanceiro(TipoProdutoFinanceiro tipoProdutoFinanceiro) {
		this.tipoProdutoFinanceiro = tipoProdutoFinanceiro;
	}

}
