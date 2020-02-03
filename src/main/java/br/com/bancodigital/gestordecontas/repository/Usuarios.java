package br.com.bancodigital.gestordecontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancodigital.gestordecontas.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}