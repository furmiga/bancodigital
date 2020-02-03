package br.com.bancodigital.gestordecontas.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.gestordecontas.event.RecursoCriadoEvent;
import br.com.bancodigital.gestordecontas.model.Conta;
import br.com.bancodigital.gestordecontas.repository.ContaRepository;


@RestController
@RequestMapping("/contas")
public class ContaResource  extends Resource<ContaRepository, Conta, Integer>{

	@Override
	public void inserirLocalizacaoDeNovoRecursoCriado(Conta e, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, e.getNumeroConta()));
	}
}
