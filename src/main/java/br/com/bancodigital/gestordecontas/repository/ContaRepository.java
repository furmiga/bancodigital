package br.com.bancodigital.gestordecontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancodigital.gestordecontas.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{}
