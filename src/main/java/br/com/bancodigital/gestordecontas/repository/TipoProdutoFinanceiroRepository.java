package br.com.bancodigital.gestordecontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancodigital.gestordecontas.model.TipoProdutoFinanceiro;

@Repository
public interface TipoProdutoFinanceiroRepository extends JpaRepository<TipoProdutoFinanceiro, Integer>{}
