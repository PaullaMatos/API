package apisimples.example.api;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import apisimples.example.api.model.ClienteModel;
import apisimples.example.api.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test") // usa application-test.properties
@Transactional // garante rollback após cada teste
public class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    public void deveSalvarCliente() {
        ClienteModel cliente = new ClienteModel(null, null);
        cliente.setNome("Ana Paula");
        cliente.setEmail("ana@exemplo.com");

        ClienteModel salvo = repository.save(cliente);

        Assertions.assertThat(salvo.getId()).isNotNull();
        Assertions.assertThat(salvo.getNome()).isEqualTo("Ana Paula");
    }

    @Test
    public void deveListarClientes() {
        repository.save(new ClienteModel("Ana", "ana@exemplo.com"));
        repository.save(new ClienteModel("João", "joao@exemplo.com"));

        List<ClienteModel> clientes = repository.findAll();

        Assertions.assertThat(clientes).hasSize(2);
        Assertions.assertThat(clientes)
                  .extracting(ClienteModel::getNome)
                  .containsExactlyInAnyOrder("Ana", "João");
    }
}
