package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duma.funcionario.dao.AtuacaoDao;
import com.duma.funcionario.domain.Atuacao;

import jakarta.transaction.Transactional;

@Service
public class AtuacaoServiceImpl implements AtuacaoService {

    @Autowired
    private AtuacaoDao dao;

    @Override @Transactional
    public void salvar(Atuacao atuacao) {
        dao.save(atuacao);
    }

    @Override  @Transactional
    public void editar(Atuacao atuacao) {
        dao.update(atuacao);
    }

    @Override  @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Atuacao buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Atuacao> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Atuacao> bucarPorCodigoPessoa(Long codigo) {
        return dao.bucarPorCodigoPessoa(codigo);
    }
}
