package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.duma.funcionario.dao.PessoaDao;
import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;

import jakarta.transaction.Transactional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaDao dao;

    @Override @Transactional
    public void salvar(Pessoa pessoa) {
        dao.save(pessoa);
    }

    @Override @Transactional
    public void editar(Pessoa pessoa) {
        dao.save(pessoa); // save já atualiza se o ID existir
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public Page<Pessoa> buscarComFiltro(PessoaFiltroForm filtro, Pageable pageable) {
        return dao.buscarComFiltro(filtro, pageable);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }
}
