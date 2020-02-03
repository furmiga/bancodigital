package br.com.bancodigital.gestordecontas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NUMERO_CONTA")
	private Integer numeroConta;

	//Devido ser uma banco digital a agencia sera sempre 1, porém é importante a flexibilidade de poder alterar isso no futuro
	//por isso ele sera um atributo e quando necessario SE necessario, poderemos altera-lo
	@Column(name = "AGENCIA")
	private Integer agencia = 1;
	
	@Column(name = "SENHA")
	@NotNull
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "FK_CLIENTE_ID", referencedColumnName = "CLIENTE_ID", nullable = false)
	private Cliente cliente;

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public Integer getAgencia() {
		return agencia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
