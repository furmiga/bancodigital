package br.com.bancodigital.gestordecontas.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.bancodigital.gestordecontas.model.Grupo;
import br.com.bancodigital.gestordecontas.model.Permissao;
import br.com.bancodigital.gestordecontas.model.Usuario;
import br.com.bancodigital.gestordecontas.repository.Grupos;
import br.com.bancodigital.gestordecontas.repository.Permissoes;
import br.com.bancodigital.gestordecontas.repository.Usuarios;

@Component
public class ComercialUserDetailsService implements UserDetailsService {
	
	@Autowired
	private Usuarios usuarios;

	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Permissoes permissoes;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return new UsuarioSistema(usuario.getNome(), usuario.getLogin(), usuario.getSenha(), authorities(usuario));
	}
	
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
		return authorities(grupos.findByUsuariosIn(Arrays.asList(usuario)));
	}
	
	public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos) {
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		List<Permissao> lista = permissoes.findByGruposIn(grupos);
		
		for (Permissao permissao: lista) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
		}
		
		return auths;
	}
}
