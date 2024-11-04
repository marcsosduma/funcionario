package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duma.funcionario.dao.DepartamentoDao;
import com.duma.funcionario.domain.Departamento;

import jakarta.transaction.Transactional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    private DepartamentoDao dao;

    @Override @Transactional
    public void salvar(Departamento departamento) {
        dao.save(departamento);
    }

    @Override @Transactional
    public void editar(Departamento departamento) {
        dao.update(departamento);
    }

    @Override @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Departamento> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean departamentoTemCargos(Long id) {
        if(buscarPorId(id).getCargos().isEmpty()){
            return false;
        }
        return true;
    }  

}
