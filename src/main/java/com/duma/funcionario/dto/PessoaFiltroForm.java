package com.duma.funcionario.dto;

import lombok.Data;

@Data
public class PessoaFiltroForm {
    private String nome;
    private String cpf;
    private Boolean soAssessores;
    private String palavraChave1;
    private String palavraChave2;
    private String palavraChave3;
    private String criterio; // QUALQUER, TODAS, MESMA
    private Integer area;
    private Integer subarea;
    private String unidade;
    private String ordenacao; // ALFA, OCUPACAO, TEMPO
}
