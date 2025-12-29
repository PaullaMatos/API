package apisimples.example.api.service;

import apisimples.example.api.model.ClienteModel;
import apisimples.example.api.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

   
    public List<ClienteModel> listarTodos() {
        return repository.findAll();
    }


    public Page<ClienteModel> listarPaginado(String nome, Pageable pageable) {
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        return repository.findAll(pageable);
    }

   
    public ClienteModel criar(ClienteModel cliente) {
        if (repository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Já existe um cliente com este e-mail: " + cliente.getEmail());
        }
        return repository.save(cliente);
    }

    public ClienteModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

   
    public ClienteModel atualizar(Long id, ClienteModel cliente) {
        ClienteModel existente = buscarPorId(id);

   
        boolean emailDuplicado = repository.existsByEmail(cliente.getEmail()) 
                && !existente.getEmail().equalsIgnoreCase(cliente.getEmail());
        if (emailDuplicado) {
            throw new RuntimeException("Já existe outro cliente com este e-mail: " + cliente.getEmail());
        }

        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());
     
        return repository.save(existente);
    }

   
   public void deletar(Long id) {
    ClienteModel cliente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    repository.delete(cliente);
}


    public ClienteModel salvar(ClienteModel cliente) {
     return repository.save(cliente);
}
}
