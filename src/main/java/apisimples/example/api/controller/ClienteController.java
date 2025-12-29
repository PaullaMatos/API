package apisimples.example.api.controller;

import apisimples.example.api.model.ClienteModel;
import apisimples.example.api.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

   
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.ok(service.listarTodos());
    }

   
    @GetMapping("/buscar")
    public ResponseEntity<Page<ClienteModel>> getClientesPaginado(
         String nome,
         Pageable pageable) {
        Page<ClienteModel> page = service.listarPaginado(nome, pageable);
        return ResponseEntity.ok(page);
    }

  
    @GetMapping("/id")
    public ResponseEntity<Object> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

  
    @PostMapping("/criar")
    public ResponseEntity<ClienteModel> createCliente(@Valid @RequestBody ClienteModel cliente) {
        ClienteModel saved = service.criar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    
    @PutMapping("/atualizar")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteModel cliente) {
        return ResponseEntity.ok(service.atualizar(id, cliente));
    }

    
    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
