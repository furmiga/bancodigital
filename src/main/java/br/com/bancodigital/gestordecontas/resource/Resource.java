package br.com.bancodigital.gestordecontas.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class Resource <R extends JpaRepository<E, I>, E, I> {
	
	@Autowired
	private R repository;
	
	@Autowired
	protected ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<?> listar(){
		return repository.findAll();
	}
	
	public abstract void inserirLocalizacaoDeNovoRecursoCriado(E e, HttpServletResponse response);
	
	@PostMapping
	public ResponseEntity<E> criar(@Valid @RequestBody E e , HttpServletResponse response) {
		E eSalvo = repository.save(e);
		inserirLocalizacaoDeNovoRecursoCriado(e, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(eSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<E> buscarPeloCodigo(@PathVariable I codigo) {
		Optional<E> e = repository.findById(codigo);
		
		return e.isPresent() ?  ResponseEntity.ok(e.get()):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable I codigo) {
		repository.deleteById(codigo);
	}
	
}
