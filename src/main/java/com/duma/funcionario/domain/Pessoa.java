package com.duma.funcionario.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PESWB_PESSOA")
public class Pessoa  extends AbstractEntity<Long>  {
    @Id
    @Column(name = "COD_PESWB", nullable = false)
    private Long codigo;

    @Column(name = "IDC_ASS_PESWB", length = 1)
    private String serAssessor;

    @Column(name = "NOM_PESWB", nullable = false, length = 70)
    private String nome;

    @Column(name = "OBS_PESWB", length = 310)
    private String obs;

    @Column(name = "EMA_PESWB", length = 70)
    private String email;

    @Column(name = "CPF_PESWB", length = 11)
    private Long cpf;

    @Column(name = "RG_PESWB", length = 15)
    private String rg;

    @Column(name = "SEX_PESWB", length = 1)
    private String sexo;

    @Column(name = "DAT_NAS_PESWB")
    private Timestamp dataNascimento;

    @Column(name = "NCL_PESWB", length = 1)
    private String nacionalidade;

    @Column(name = "EST_CIV_PESWB", length = 1)
    private String estadoCivil;

    @Column(name = "SIT_PESWB", nullable = false, length = 1)
    private String situacao;

    @Column(name = "IDC_DSP_PESWB", length = 3)
    private String estarDisponivel;

    @Column(name = "IDC_ENV_PESWB", length = 1)
    private String tipoEnvio;

    @Column(name = "DAT_ASS_PESWB")
    private Timestamp dataAssessor;

    @Column(name = "OBS_ASS_PESWB", length = 244)
    private String obsAssessor;

    @Column(name = "LGR_PESWB", length = 60)
    private String logradouro;

    @Column(name = "CPL_PESWB", length = 60)
    private String complemento;

    @Column(name = "CEP_PESWB", length = 8)
    private String cep;

    @Column(name = "NUM_CXP_PESWB", length = 8)
    private String caixaPostal;

    @Column(name = "TXT_CUI_PESWB", length = 60)
    private String aosCuidados;

    @Column(name = "CID_PESWB", length = 30)
    private String cidade;

    @Column(name = "UF_PESWB", length = 2)
    private String uf;

    @Column(name = "IDC_END_PFR_PESWB", length = 1)
    private String enderecoPreferencial;

    @Column(name = "TEL_PESWB", length = 19)
    private String telefoneResidencial;

    @Column(name = "RAM_PESWB", length = 10)
    private String ramalResidencial;

    @Column(name = "TEL_EME_PESWB", length = 19)
    private String telefoneEmergencial;

    @Column(name = "FAX_PESWB", length = 19)
    private String fax;

    @Column(name = "RAM_FAX_PESWB", length = 4)
    private String ramalFax;

    @Column(name = "END_INT_PESWB", length = 400)
    private String enderecoInternacional;

    @Column(name = "RGI_TRA_PESWB", length = 10)
    private String regimeTrabalho;

    @Column(name = "ANO_INI_FCA_PESWB", length = 4)
    private Integer anoInicioFuncao;

    // Getters and Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getSerAssessor() {
        return serAssessor;
    }

    public void setSerAssessor(String serAssessor) {
        this.serAssessor = serAssessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Timestamp getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Timestamp dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEstarDisponivel() {
        return estarDisponivel;
    }

    public void setEstarDisponivel(String estarDisponivel) {
        this.estarDisponivel = estarDisponivel;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public Timestamp getDataAssessor() {
        return dataAssessor;
    }

    public void setDataAssessor(Timestamp dataAssessor) {
        this.dataAssessor = dataAssessor;
    }

    public String getObsAssessor() {
        return obsAssessor;
    }

    public void setObsAssessor(String obsAssessor) {
        this.obsAssessor = obsAssessor;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public String getAosCuidados() {
        return aosCuidados;
    }

    public void setAosCuidados(String aosCuidados) {
        this.aosCuidados = aosCuidados;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEnderecoPreferencial() {
        return enderecoPreferencial;
    }

    public void setEnderecoPreferencial(String enderecoPreferencial) {
        this.enderecoPreferencial = enderecoPreferencial;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getRamalResidencial() {
        return ramalResidencial;
    }

    public void setRamalResidencial(String ramalResidencial) {
        this.ramalResidencial = ramalResidencial;
    }

    public String getTelefoneEmergencial() {
        return telefoneEmergencial;
    }

    public void setTelefoneEmergencial(String telefoneEmergencial) {
        this.telefoneEmergencial = telefoneEmergencial;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRamalFax() {
        return ramalFax;
    }

    public void setRamalFax(String ramalFax) {
        this.ramalFax = ramalFax;
    }

    public String getEnderecoInternacional() {
        return enderecoInternacional;
    }

    public void setEnderecoInternacional(String enderecoInternacional) {
        this.enderecoInternacional = enderecoInternacional;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

    public Integer getAnoInicioFuncao() {
        return anoInicioFuncao;
    }

    public void setAnoInicioFuncao(Integer anoInicioFuncao) {
        this.anoInicioFuncao = anoInicioFuncao;
    }
}
