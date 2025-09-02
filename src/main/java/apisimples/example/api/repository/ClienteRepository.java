package apisimples.example.api.repository;

import apisimples.example.api.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Page<ClienteModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    boolean existsByEmail(String email);
}