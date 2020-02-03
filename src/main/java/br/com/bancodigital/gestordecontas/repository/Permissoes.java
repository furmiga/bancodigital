package br.com.bancodigital.gestordecontas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancodigital.gestordecontas.model.Grupo;
import br.com.bancodigital.gestordecontas.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long> {
	
	List<Permissao> findByGruposIn(List<Grupo> grupo);

}