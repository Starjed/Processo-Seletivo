package com.processo.seletivo.models;

import jakarta.persistence.*;

    @Entity
    @Table(name = "unidade")
    public class Unidade {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long unidId;

        public Long getUnidId() {
            return unidId;
        }

        public void setUnidId(Long unidId) {
            this.unidId = unidId;
        }

        public String getUnidNome() {
            return unidNome;
        }

        public void setUnidNome(String unidNome) {
            this.unidNome = unidNome;
        }

        public String getUnidSigla() {
            return unidSigla;
        }

        public void setUnidSigla(String unidSigla) {
            this.unidSigla = unidSigla;
        }

        private String unidNome;
        private String unidSigla;
}
