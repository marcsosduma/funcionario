package com.duma.funcionario.dao;

import org.springframework.stereotype.Repository;

import com.duma.funcionario.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements  FuncionarioDao{

}
