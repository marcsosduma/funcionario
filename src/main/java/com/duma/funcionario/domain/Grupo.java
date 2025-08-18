package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GRUWB_GRUPO")
@Data
public class Grupo {

    @Id
    @Column(name = "COD_GRUWB")
    // No XML: generator-class="assigned" → ID atribuído manualmente
    private Integer codigo;

    @Column(name = "DES_GRUWB", nullable = false, length = 70)
    private String descricao;
}
