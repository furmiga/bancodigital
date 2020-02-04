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
import br.com.bancodigital.gestordecontas.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteResourceTests {

	@InjectMocks
	private ClienteResource resource;

	@Mock
	private ClienteRepository repository;

	@Mock
	protected ApplicationEventPublisher publisher;

	private MockMvc mock;

	private Cliente cliente;

	{
		cliente = new Cliente();
		cliente.setClienteId(1);
		cliente.setCpf("353.427.910-73");
		cliente.setEmail("teste@teste");
		cliente.setEndereco("teste 156");
		cliente.setNome("victor");
	}

	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new BancoDigitalExceptionHandler())
				.build();
	}

	@Test
	public void deveCadastrarCliente() throws Exception {
		when(repository.save(Mockito.any())).thenReturn(cliente);

		String clienteJson = new ObjectMapper().writeValueAsString(cliente);

		mock.perform(
				MockMvcRequestBuilders.post("/cliente").contentType(MediaType.APPLICATION_JSON).content(clienteJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(clienteJson));
	}

	@Test
	public void deveListarTodosOsClientes() throws JsonProcessingException, Exception {

		List<Cliente> listaDeClientes = Arrays.asList(cliente, cliente, cliente);
		when(repository.findAll()).thenReturn(listaDeClientes);

		mock.perform(MockMvcRequestBuilders.get("/cliente")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(listaDeClientes)));

	}
	
	@Test
	public void deveBuscarClienteFiltrandoPeloCodigo() throws JsonProcessingException, Exception {
		
		when(repository.findById(1)).thenReturn(Optional.of(cliente));
		
		mock.perform(MockMvcRequestBuilders.get("/cliente/1")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(cliente)));
		
	}
	
	@Test
	public void deveDeletarCliente() throws JsonProcessingException, Exception {
		
		doNothing().when(repository).deleteById(1);
		
		mock.perform(MockMvcRequestBuilders.delete("/cliente/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
