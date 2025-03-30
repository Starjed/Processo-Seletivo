package com.processo.seletivo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pesId;

    @Column(length = 200)
    private String pesNome;

    private LocalDate pesDataNascimento;

    @Column(length = 9)
    private String pesSexo;

    @Column(length = 200)
    private String pesMae;

    @Column(length = 200)
    private String pesPai;

    public Integer getPesId() {
        return pesId;
    }

    public void setPesId(Integer pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public String getPesSexo() {
        return pesSexo;
    }

    public void setPesSexo(String pesSexo) {
        this.pesSexo = pesSexo;
    }

    public String getPesMae() {
        return pesMae;
    }

    public void setPesMae(String pesMae) {
        this.pesMae = pesMae;
    }

    public String getPesPai() {
        return pesPai;
    }

    public void setPesPai(String pesPai) {
        this.pesPai = pesPai;
    }
}
