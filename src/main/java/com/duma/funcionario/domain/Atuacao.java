package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ATUWB_ATUACAO")
@Data
public class Atuacao {

    @Id
    @Column(name = "IDE_ATUWB")
    private Long id;

    // Associação Many-to-One com Area
    @ManyToOne(fetch = FetchType.EAGER) // lazy="false"
    @JoinColumn(name = "AREWB_COD_AREWB")
    private Area area;

    // Associação Many-to-One com Pessoa
    @ManyToOne(fetch = FetchType.EAGER) // lazy="false"
    @JoinColumn(name = "PESWB_COD_PESWB")
    private Pessoa pessoa;
}
