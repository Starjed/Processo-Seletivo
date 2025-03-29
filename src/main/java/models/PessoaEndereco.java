package models;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "end_id")
    private Endereco endereco;

}

