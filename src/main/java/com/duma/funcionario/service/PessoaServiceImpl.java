package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.duma.funcionario.dao.PessoaRepository;
import com.duma.funcionario.domain.Pessoa;

import jakarta.transaction.Transactional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository dao;

    @Override @Transactional
    public void salvar(Pessoa pessoa) {
        dao.save(pessoa);
    }

    @Override @Transactional
    public void editar(Pessoa pessoa) {
        dao.save(pessoa); // save já atualiza se o ID existir
    }

    @Override @Transactional
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public Page<Pessoa> buscarComFiltro(String termo, Pageable pageable) {
        if (termo == null) termo = "";
        return dao.buscarComFiltro(termo, pageable);
    }
}
