package apisimples.example.api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import apisimples.example.api.model.ClienteModel;
import apisimples.example.api.repository.ClienteRepository;


class ClienteServiceTest {

   
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

@Test
void deveSalvarCliente() {
    ClienteModel cliente = new ClienteModel("Ana", "ana@exemplo.com");
    final ClienteModel salvoEsperado = new ClienteModel(1L, "Ana", "ana@exemplo.com"); // 👈 agora com ID e nome

    when(repository.save(any(ClienteModel.class))).thenReturn(salvoEsperado);

    ClienteModel salvo = service.salvar(cliente);

    assertThat(salvo).isNotNull();
    assertThat(salvo.getId()).isEqualTo(1L);
    assertThat(salvo.getNome()).isEqualTo("Ana");

    verify(repository, times(1)).save(any(ClienteModel.class));
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

    @Test
    void deveBuscarClientePorId() {
        ClienteModel cliente = new ClienteModel("Ana", "ana@exemplo.com");

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<ClienteModel> resultado = Optional.ofNullable(service.buscarPorId(1L));

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("Ana");
        verify(repository, times(1)).findById(1L);
    }

    @Test
void deveDeletarCliente() {
    Long id = 1L;
    ClienteModel cliente = new ClienteModel("Ana", "ana@exemplo.com");

    when(repository.findById(id)).thenReturn(Optional.of(cliente));
    doNothing().when(repository).delete(cliente);

    service.deletar(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).delete(cliente);
}
}
