package br.com.bancodigital.gestordecontas.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.gestordecontas.event.RecursoCriadoEvent;
import br.com.bancodigital.gestordecontas.model.TipoProdutoFinanceiro;
import br.com.bancodigital.gestordecontas.repository.TipoProdutoFinanceiroRepository;

@RestController
@RequestMapping("/tp-produto-financeiro")
public class TipoProdutoFinanceiroResource extends Resource<TipoProdutoFinanceiroRepository, TipoProdutoFinanceiro, Integer>{

	@Override
	public void inserirLocalizacaoDeNovoRecursoCriado(TipoProdutoFinanceiro e, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, e.getTipoProdutoFinanceiroId()));
	}
}
