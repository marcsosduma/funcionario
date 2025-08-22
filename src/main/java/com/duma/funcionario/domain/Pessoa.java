package com.duma.funcionario.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "PESWB_PESSOA")
@Data
public class Pessoa {
    
    @Id
    @Column(name = "COD_PESWB")
    private Long codigo;

    @Column(name = "IDC_ASS_PESWB", nullable = true, length = 1)
    private String indicadorAssessor;

    @Column(name = "NOM_PESWB", length = 70, nullable = true)
    private String nome;

    @Column(name = "OBS_PESWB", nullable = true, length = 400)
    private String observacao;

    @Column(name = "EMA_PESWB", nullable = true, length = 70)
    private String email;

    @Column(name = "CPF_PESWB", nullable = true)
    private Long cpf;

    @Column(name = "RG_PESWB", nullable = true, length = 15)
    private String rg;

    @Column(name = "SEX_PESWB", nullable = true, length = 1)
    private String sexo;

    @Column(name = "DAT_NAS_PESWB", nullable = true)
    private LocalDate dataNascimento;

    @Column(name = "NCL_PESWB", nullable = true, length = 1)
    private String nacionalidade;

    @Column(name = "EST_CIV_PESWB", nullable = true, length = 1)
    private String estadoCivil;

    @Column(name = "SIT_PESWB", length = 1, nullable = true)
    private String situacao;

    @Column(name = "IDC_DSP_PESWB", nullable = true, length = 3)
    private String indicadorDisponibilidade;

    @Column(name = "IDC_ENV_PESWB", nullable = true, length = 1)
    private String tipoEnvioPreferencial;

    @Column(name = "DAT_ASS_PESWB", nullable = true)
    private LocalDate dataAssessoramento;

    @Column(name = "OBS_ASS_PESWB", nullable = true, length = 400)
    private String observacaoAssessor;

    @Column(name = "LGR_PESWB", nullable = true, length = 60)
    private String logradouro;

    @Column(name = "CPL_PESWB", nullable = true, length = 60)
    private String complementoEndereco;

    @Column(name = "CEP_PESWB", nullable = true, length = 8)
    private String cep;

    @Column(name = "NUM_CXP_PESWB", nullable = true, length = 8)
    private String numeroCaixaPostal;

    @Column(name = "TXT_CUI_PESWB", nullable = true, length = 60)
    private String aosCuidados;

    @Column(name = "CID_PESWB", nullable = true, length = 30)
    private String cidade;

    @Column(name = "UF_PESWB", nullable = true, length = 2)
    private String estado;

    @Column(name = "IDC_END_PFR_PESWB", nullable = true, length = 1)
    private String enderecoPreferencial;

    @Column(name = "TEL_PESWB", nullable = true, length = 19)
    private String telefoneResidencial;

    @Column(name = "RAM_PESWB", nullable = true, length = 10)
    private String ramalResidencial;

    @Column(name = "TEL_EME_PESWB", nullable = true, length = 19)
    private String telefoneEmergencial;

    @Column(name = "FAX_PESWB", nullable = true, length = 19)
    private String fax;

    @Column(name = "RAM_FAX_PESWB", nullable = true, length = 4)
    private String ramalFax;

    @Column(name = "END_INT_PESWB", nullable = true, length = 400)
    private String enderecoInternacional;

    @Column(name = "RGI_TRA_PESWB", nullable = true, length = 10)
    private String regimeTrabalho;

    @Column(name = "FCA_ATU_PESWB", nullable = true, length = 40)
    private String funcaoAtual;

    // Associação ManyToOne com Unidade
    @ManyToOne(fetch = FetchType.EAGER) // lazy="false"
    @JoinColumn(name = "UNIWB_COD_UNIWB")
    private Unidade unidadeVinculo;

    @Column(name = "DEP_PESWB", nullable = true, length = 70)
    private String departamento;

    @Column(name = "QTD_PCE_ABT_PESWB", nullable = true)
    private Integer pareceresAbertos;

    @Column(name = "QTD_PCE_DVL_PESWB", nullable = true)
    private Integer pareceresEmitidosUltimos12Meses;

    @Column(name = "TMP_MED_PCE_PESWB", nullable = true)
    private Integer tempoMedioParecerInicial;

    @Column(name = "TMP_MED_ACP_PESWB", nullable = true)
    private Integer tempoMedioParecerAcompanhamento;

    @Column(name = "CFT_PESWB", nullable = true, length = 1)
    private String conflitoInteresse;

    @Column(name = "NOM_FNT_PESWB", nullable = true, length = 70)
    private String nomeFonetico;

    @Column(name = "QTD_PCE_ABT_SAG_PESWB", nullable = true)
    private Integer pareceresAbertosSag;

    @Column(name = "QTD_PCE_DVL_SAG_PESWB", nullable = true)
    private Integer pareceresEmitidosSag;

    @Column(name = "NOM_SGE_PESWB", nullable = true)
    private String nomeSAGe;

    @Column(name = "GSCH_PESWB", nullable = true, length = 550)
    private String GSCH;

    @Column(name = "URL_PUBLONS_PESWB", nullable = true, length = 550)
    private String urlPublons;

    @Column(name = "ORCID_PESWB", nullable = true, length = 550)
    private String orcid;

    @Column(name = "TIP_END_PESWB", nullable = true)
    private String tipoEndereco;

    @Column(name = "NOM_SCL_PESWB", nullable = true)
    private String nomeSocial;

    @Column(name = "DAT_CAD_PESWB", nullable = true)
    private LocalDate dataCadastro;

    @Column(name = "DAT_ATU_PESWB", nullable = true)
    private LocalDate dataAtualizacao;

    @Column(name = "ANO_INI_UNI_PESWB", nullable = true)
    private Integer anoInicioUnidade;

    @Column(name = "ANO_INI_FCA_PESWB", nullable = true)
    private Integer anoInicioFuncao;

    @Column(name = "PAI_NAS_PESWB", nullable = true)
    private String paisNascimento;

    @Column(name = "UNIWB_COD_TMP_UNIWB", nullable = true)
    private Long unidadeCodTmp;

    public String getCpfFormatado(){
        String vCpf = "";        
        if(this.cpf!=null){
            vCpf = "00000000000" + this.cpf;
            vCpf = vCpf.substring(vCpf.length()-11,vCpf.length());
            vCpf = vCpf.substring(0, 3)+"."+vCpf.substring(3, 6)+"."+vCpf.substring(6, 9)+"-"+vCpf.substring(9, 11);
        }
        return vCpf;
    }
}
