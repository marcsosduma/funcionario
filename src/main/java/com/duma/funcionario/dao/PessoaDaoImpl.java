package com.duma.funcionario.dao;

import org.springframework.stereotype.Repository;

import com.duma.funcionario.domain.Pessoa;

@Repository
public class PessoaDaoImpl extends AbstractDao<Pessoa, Long> implements  PessoaDao{

}
