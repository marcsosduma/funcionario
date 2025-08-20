package com.duma.funcionario.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;

public interface PessoaService {

    void salvar(Pessoa pessoa);

    void editar(Pessoa pessoa);

    void excluir(Long id);

    Pessoa buscarPorId(Long id);

    List<Pessoa> buscarTodos();

    Page<Pessoa> buscarComFiltro(PessoaFiltroForm filtro, Pageable pageable);
}
