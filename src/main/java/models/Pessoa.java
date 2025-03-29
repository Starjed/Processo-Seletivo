package models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pesId;

    @Column(nullable = false, length = 200)
    private String pesNome;

    @Column(nullable = false)
    private LocalDate pesDataNascimento;

    @Column(length = 9)
    private String pesSexo;

    @Column(length = 200)
    private String pesMae;

    @Column(length = 200)
    private String pesPai;
}