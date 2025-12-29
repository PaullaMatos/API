package apisimples.example.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;


    public ClienteModel(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}
