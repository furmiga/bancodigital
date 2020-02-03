package br.com.bancodigital.gestordecontas.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.gestordecontas.event.RecursoCriadoEvent;
import br.com.bancodigital.gestordecontas.model.ProdutoFinanceiro;
import br.com.bancodigital.gestordecontas.repository.ProdutoFinanceiroRepository;

@RestController
@RequestMapping("/produtor-financeiro")
public class ProdutoFinanceiroResource extends Resource<ProdutoFinanceiroRepository, ProdutoFinanceiro, Integer>{

	@Override
	public void inserirLocalizacaoDeNovoRecursoCriado(ProdutoFinanceiro e, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response,  e.getProdutoFinanceiroId()));
	}
}
