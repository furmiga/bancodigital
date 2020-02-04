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
import br.com.bancodigital.gestordecontas.repository.ContaRepository;

@RunWith(MockitoJUnitRunner.class)
public class ContaResourceTests {

	@InjectMocks
	private ContaResource resource;

	@Mock
	private ContaRepository repository;

	@Mock
	protected ApplicationEventPublisher publisher;

	private MockMvc mock;

	private Conta conta;

	{
		conta = new Conta();
		conta.setNumeroConta(123);
		conta.setSenha("123");
		
		Cliente cliente = new Cliente();
		cliente.setClienteId(1);
		cliente.setCpf("353.427.910-73");
		cliente.setEmail("teste@teste");
		cliente.setEndereco("teste 156");
		cliente.setNome("victor");
		
		conta.setCliente(cliente);
		
	}

	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new BancoDigitalExceptionHandler())
				.build();
	}

	@Test
	public void deveCadastrarConta() throws Exception {
		when(repository.save(Mockito.any())).thenReturn(conta);

		String contaJson = new ObjectMapper().writeValueAsString(conta);

		mock.perform(
				MockMvcRequestBuilders.post("/contas").contentType(MediaType.APPLICATION_JSON).content(contaJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(contaJson));
	}

	@Test
	public void deveListarTodosAsContas() throws JsonProcessingException, Exception {

		List<Conta> listaDeContas = Arrays.asList(conta, conta, conta);
		when(repository.findAll()).thenReturn(listaDeContas);

		mock.perform(MockMvcRequestBuilders.get("/contas")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(listaDeContas)));

	}
	
	@Test
	public void deveBuscarContaFiltrandoPeloCodigo() throws JsonProcessingException, Exception {
		
		when(repository.findById(1)).thenReturn(Optional.of(conta));
		
		mock.perform(MockMvcRequestBuilders.get("/contas/1")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(conta)));
		
	}
	
	@Test
	public void deveDeletarConta() throws JsonProcessingException, Exception {
		
		doNothing().when(repository).deleteById(1);
		
		mock.perform(MockMvcRequestBuilders.delete("/contas/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
}
