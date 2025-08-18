package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENTWB_ENTIDADE")
@Data
public class Entidade {

    @Id
    @Column(name = "COD_ENTWB")
    // No XML: generator-class="assigned" → ID atribuído manualmente
    private Integer codigo;

    @Column(name = "DES_ENTWB", nullable = false, length = 70)
    private String descricao;

    @Column(name = "SIT_ENTWB", nullable = false, length = 1)
    private String situacao;

    // No XML: java.sql.Timestamp → em JPA moderno usamos LocalDateTime
    @Column(name = "DAT_EXT_ENTWB")
    private LocalDateTime dataExtincao;

    @Column(name = "SIG_ENTWB", length = 15)
    private String sigla;

    // Associação ManyToOne com Grupo
    @ManyToOne(fetch = FetchType.EAGER) // no XML estava lazy="false"
    @JoinColumn(name = "GRUWB_COD_GRUWB")
    private Grupo grupo;
}
