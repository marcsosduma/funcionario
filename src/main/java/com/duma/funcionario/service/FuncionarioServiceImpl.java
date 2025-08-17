package com.duma.funcionario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duma.funcionario.dao.FuncionarioDao;
import com.duma.funcionario.domain.Funcionario;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioServiceImpl implements  FuncionarioService{

    @Autowired
    private FuncionarioDao dao;

    @Override @Transactional
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Override @Transactional
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Override @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        // TODO Auto-generated method stub
        return dao.findByNome(nome);
    }

    @Override
    public List<Funcionario> buscarPorCargo(Long id) {
        // TODO Auto-generated method stub
        return dao.findByCargo(id);
    }

    @Override
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
        // TODO Auto-generated method stub
        if(entrada!=null && saida!=null){
            return dao.findByDataEntradaDataSaida(entrada, saida);
        }else if(entrada!=null){
            return dao.findByDataEntrada(entrada);
        }else if(saida!=null){
            return dao.findByDataSaida(saida);
        }else{
            return new ArrayList<>();
        }
    }

    

}
