package com.duma.funcionario.service;

import java.util.List;

import com.duma.funcionario.domain.Funcionario;

public interface FuncionarioService {

    void salvar(Funcionario funcionario);    
    void editar(Funcionario funcionario);
    void excluir(Long id);
    Funcionario buscarPorId(Long id);
    List<Funcionario> buscarTodos();
}
