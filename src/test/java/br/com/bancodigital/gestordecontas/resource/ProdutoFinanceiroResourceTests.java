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
import br.com.bancodigital.gestordecontas.model.Cliente;
import br.com.bancodigital.gestordecontas.model.Conta;
import br.com.bancodigital.gestordecontas.model.ProdutoFinanceiro;
import br.com.bancodigital.gestordecontas.model.TipoProdutoFinanceiro;
import br.com.bancodigital.gestordecontas.repository.ProdutoFinanceiroRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoFinanceiroResourceTests {

	@InjectMocks
	private ProdutoFinanceiroResource resource;

	@Mock
	private ProdutoFinanceiroRepository repository;

	@Mock
	protected ApplicationEventPublisher publisher;

	private MockMvc mock;

	private ProdutoFinanceiro produtorFinanceiro;

	{
		produtorFinanceiro = new ProdutoFinanceiro();
		
		Conta conta = new Conta();
		conta.setNumeroConta(123);
		conta.setSenha("123");
		
		Cliente cliente = new Cliente();
		cliente.setClienteId(1);
		cliente.setCpf("353.427.910-73");
		cliente.setEmail("teste@teste");
		cliente.setEndereco("teste 156");
		cliente.setNome("victor");
		
		conta.setCliente(cliente);
		
		produtorFinanceiro.setConta(conta);
		produtorFinanceiro.setValor(11);
		TipoProdutoFinanceiro tipoProdutoFinanceiro = new TipoProdutoFinanceiro();
		tipoProdutoFinanceiro.setDescricao("Conta Corrente");
		produtorFinanceiro.setTipoProdutoFinanceiro(tipoProdutoFinanceiro);
		
		
	}

	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new BancoDigitalExceptionHandler())
				.build();
	}

	@Test
	public void deveCadastrarProdutoFinanceiro() throws Exception {
		when(repository.save(Mockito.any())).thenReturn(produtorFinanceiro);

		String produtoFinanceiroJson = new ObjectMapper().writeValueAsString(produtorFinanceiro);

		mock.perform(
				MockMvcRequestBuilders.post("/produtor-financeiro").contentType(MediaType.APPLICATION_JSON).content(produtoFinanceiroJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(produtoFinanceiroJson));
	}

	@Test
	public void deveListarTodosOsProdutosFinanceiros() throws JsonProcessingException, Exception {

		List<ProdutoFinanceiro> listaDeProdutosFinanceiros = Arrays.asList(produtorFinanceiro, produtorFinanceiro, produtorFinanceiro);
		when(repository.findAll()).thenReturn(listaDeProdutosFinanceiros);

		mock.perform(MockMvcRequestBuilders.get("/produtor-financeiro")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(listaDeProdutosFinanceiros)));

	}
	
	@Test
	public void deveBuscarProdutoFinanceiroFiltrandoPeloCodigo() throws JsonProcessingException, Exception {
		
		when(repository.findById(1)).thenReturn(Optional.of(produtorFinanceiro));
		
		mock.perform(MockMvcRequestBuilders.get("/produtor-financeiro/1")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(produtorFinanceiro)));
		
	}
	
	@Test
	public void deveDeletarProdutoFinanceiro() throws JsonProcessingException, Exception {
		
		doNothing().when(repository).deleteById(1);
		
		mock.perform(MockMvcRequestBuilders.delete("/produtor-financeiro/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
}
