package com.processo.seletivo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpId;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fp_data", nullable = false)
    private LocalDate fpData;

    @Column(name = "fp_bucket", length = 50, nullable = false)
    private String fpBucket;

    @Column(name = "fp_hash", length = 50, nullable = false)
    private String fpHash;

    public Integer getFpId() {
        return fpId;
    }

    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getFpData() {
        return fpData;
    }

    public void setFpData(LocalDate fpData) {
        this.fpData = fpData;
    }

    public String getFpBucket() {
        return fpBucket;
    }

    public void setFpBucket(String fpBucket) {
        this.fpBucket = fpBucket;
    }

    public String getFpHash() {
        return fpHash;
    }

    public void setFpHash(String fpHash) {
        this.fpHash = fpHash;
    }
}
