package repository;

import jakarta.persistence.*;
import models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
interface PessoaRepository extends JpaRepository<Pessoa, Long> {}

@Entity
@Table(name = "foto_pessoa")
class FotoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fpId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(nullable = false)
    private LocalDate fpData;

    @Column(length = 50)
    private String fpBucket;

    @Column(length = 50)
    private String fpHash;
}

