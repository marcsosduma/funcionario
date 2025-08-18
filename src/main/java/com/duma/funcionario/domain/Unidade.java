package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "UNIWB_UNIDADE")
@Data
public class Unidade {

    @Id
    @Column(name = "COD_UNIWB")
    // No XML: generator-class="assigned" → ID definido manualmente
    private Integer codigo;

    @Column(name = "NOM_UNIWB", nullable = false, length = 70)
    private String nome;

    @Column(name = "SIT_UNIWB", nullable = false, length = 1)
    private String situacao;

    @Column(name = "SIG_UNIWB", length = 15)
    private String sigla;

    @Column(name = "EMA_UNIWB", length = 200)
    private String email;

    // No XML: java.sql.Timestamp → em JPA podemos usar LocalDateTime
    @Column(name = "DAT_EXT_UNIWB")
    private LocalDateTime dataExtincao;

    @Column(name = "DAT_ATV_UNIWB")
    private LocalDateTime dataAtivacao;

    @Column(name = "LGR_UNIWB", length = 60)
    private String logradouro;

    @Column(name = "CPL_UNIWB", length = 60)
    private String complemento;

    @Column(name = "CEP_UNIWB", length = 8)
    private Integer cep;

    @Column(name = "NUM_CXP_UNIWB", length = 8)
    private String caixaPostal;

    @Column(name = "TXT_CUI_UNIWB", length = 60)
    private String aosCuidados;

    @Column(name = "CID_UNIWB", length = 30)
    private String cidade;

    @Column(name = "UF_UNIWB", length = 2)
    private String uf;

    @Column(name = "PAI_UNIWB", length = 30)
    private String pais;

    @Column(name = "TEL_UNIWB", length = 19)
    private String fone;

    @Column(name = "RAM_UNIWB", length = 4)
    private String ramal;

    @Column(name = "FAX_UNIWB", length = 19)
    private String fax;

    @Column(name = "RAM_FAX_UNIWB", length = 4)
    private String ramalFax;

    // Associação ManyToOne com Entidade
    @ManyToOne(fetch = FetchType.EAGER) // lazy="false" no XML
    @JoinColumn(name = "ENTWB_COD_ENTWB")
    private Entidade entidade;
}
