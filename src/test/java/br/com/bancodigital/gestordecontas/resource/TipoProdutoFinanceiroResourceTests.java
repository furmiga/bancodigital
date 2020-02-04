package br.com.bancodigital.gestordecontas.resource;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bancodigital.gestordecontas.exceptionhandler.BancoDigitalExceptionHandler;
import br.com.bancodigital.gestordecontas.model.TipoProdutoFinanceiro;
import br.com.bancodigital.gestordecontas.repository.TipoProdutoFinanceiroRepository;

@RunWith(MockitoJUnitRunner.class)
public class TipoProdutoFinanceiroResourceTests {

	@InjectMocks
	private TipoProdutoFinanceiroResource resource;

	@Mock
	private TipoProdutoFinanceiroRepository repository;

	@Mock
	protected ApplicationEventPublisher publisher;

	private MockMvc mock;

	private TipoProdutoFinanceiro tpProdutoFinanceiro;

	{
		tpProdutoFinanceiro = new TipoProdutoFinanceiro();
		tpProdutoFinanceiro.setDescricao("Conta corrente");
	}

	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new BancoDigitalExceptionHandler())
				.build();
	}

	@Test
	public void deveCadastrarTpProdutoFinanceiro() throws Exception {
		when(repository.save(Mockito.any())).thenReturn(tpProdutoFinanceiro);

		String tpProdutoFinanceiroJson = new ObjectMapper().writeValueAsString(tpProdutoFinanceiro);

		mock.perform(
				MockMvcRequestBuilders.post("/tp-produto-financeiro").contentType(MediaType.APPLICATION_JSON).content(tpProdutoFinanceiroJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(tpProdutoFinanceiroJson));
	}

	@Test
	public void deveListarTodosOsTiposDeProdutoFinanceiro() throws JsonProcessingException, Exception {

		List<TipoProdutoFinanceiro> listaDeTiposDeProdutoFinanceiro = Arrays.asList(tpProdutoFinanceiro, tpProdutoFinanceiro, tpProdutoFinanceiro);
		when(repository.findAll()).thenReturn(listaDeTiposDeProdutoFinanceiro);

		mock.perform(MockMvcRequestBuilders.get("/tp-produto-financeiro")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(listaDeTiposDeProdutoFinanceiro)));

	}
	
	@Test
	public void deveBuscarTpProdutoFinanceiroFiltrandoPeloCodigo() throws JsonProcessingException, Exception {
		
		when(repository.findById(1)).thenReturn(Optional.of(tpProdutoFinanceiro));
		
		mock.perform(MockMvcRequestBuilders.get("/tp-produto-financeiro/1")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(tpProdutoFinanceiro)));
		
	}
	
	@Test
	public void deveDeletarTpProdutoFinanceiro() throws JsonProcessingException, Exception {
		
		doNothing().when(repository).deleteById(1);
		
		mock.perform(MockMvcRequestBuilders.delete("/tp-produto-financeiro/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
}
