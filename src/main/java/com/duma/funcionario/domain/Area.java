package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AREWB_AREA")
@Data
public class Area {

    @Id
    @Column(name = "COD_AREWB")
    // No Hibernate XML estava "generator-class=assigned"
    // Em JPA isso significa que o id é atribuído manualmente, então não usamos @GeneratedValue
    private Integer codigo;

    @Column(name = "TIP_AREWB", nullable = false, length = 1)
    private String tipo;

    @Column(name = "DES_AREWB", nullable = false, length = 60)
    private String descricao;

    @Column(name = "SIT_AREWB", nullable = false, length = 1)
    private String situacao;

    @Column(name = "COOWB_COD_COOWB", length = 2)
    private Integer coordenacao;

    @ManyToOne(fetch = FetchType.EAGER) // no XML estava lazy="false"
    @JoinColumn(name = "AREWB_COD_AREWB")
    private Area area;
}