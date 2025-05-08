package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duma.funcionario.dao.PessoaDao;
import com.duma.funcionario.domain.Pessoa;

import jakarta.transaction.Transactional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaDao dao;

    @Override @Transactional
    public void salvar(Pessoa pessoa) {
        dao.save(pessoa);
    }

    @Override  @Transactional
    public void editar(Pessoa pessoa) {
        dao.update(pessoa);
    }

    @Override  @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return dao.findAll();
    }

}
