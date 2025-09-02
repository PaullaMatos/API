package apisimples.example.api;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import apisimples.example.api.model.ClienteModel;
import apisimples.example.api.repository.ClienteRepository;
import apisimples.example.api.service.ClienteService;

@ExtendWith(MockitoExtension.class) // habilita Mockito no JUnit 5
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository; // simulado

    @InjectMocks
    private ClienteService service; // vai receber o repository mockado

    @Test
    void deveSalvarCliente() {
        ClienteModel cliente = new ClienteModel("Ana", "ana@exemplo.com");

        // quando chamar repository.save(cliente), retorne o mesmo cliente
        when(repository.save(cliente)).thenReturn(cliente);

        ClienteModel salvo = service.salvar(cliente);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Ana");
        verify(repository, times(1)).save(cliente); // garante que foi chamado 1 vez
    }

    @Test
    void deveListarClientes() {
        List<ClienteModel> clientes = Arrays.asList(
                new ClienteModel("Ana", "ana@exemplo.com"),
                new ClienteModel("João", "joao@exemplo.com")
        );

        when(repository.findAll()).thenReturn(clientes);

        List<ClienteModel> resultado = service.listarTodos();

        assertThat(resultado).hasSize(2);
        assertThat(resultado)
                .extracting(ClienteModel::getNome)
                .containsExactlyInAnyOrder("Ana", "João");

        verify(repository, times(1)).findAll();
    }
}
