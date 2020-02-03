package br.com.bancodigital.gestordecontas.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.gestordecontas.event.RecursoCriadoEvent;
import br.com.bancodigital.gestordecontas.model.Cliente;
import br.com.bancodigital.gestordecontas.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteResource extends Resource<ClienteRepository, Cliente, Integer>{

	@Override
	public void inserirLocalizacaoDeNovoRecursoCriado(Cliente e, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, e.getClienteId()));
	}

}
