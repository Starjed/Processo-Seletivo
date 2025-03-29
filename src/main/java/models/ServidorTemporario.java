package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

    @Entity
    @Table(name = "servidor_temporario")
    public class ServidorTemporario {
    @Id
    private Long pesId;

    private LocalDate stDataAdmissao;
    private LocalDate stDataDemissao;

}
