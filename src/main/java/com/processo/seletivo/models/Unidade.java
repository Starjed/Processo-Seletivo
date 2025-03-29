package com.processo.seletivo.models;

import jakarta.persistence.*;

    @Entity
    @Table(name = "unidade")
    public class Unidade {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long unidId;

        private String unidNome;
        private String unidSigla;
}
