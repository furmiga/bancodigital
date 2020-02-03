package br.com.bancodigital.gestordecontas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancodigital.gestordecontas.model.Grupo;
import br.com.bancodigital.gestordecontas.model.Usuario;

public interface Grupos extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByUsuariosIn(List<Usuario> usuario);

}
