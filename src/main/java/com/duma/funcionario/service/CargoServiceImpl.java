package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duma.funcionario.dao.CargoDao;
import com.duma.funcionario.domain.Cargo;

import jakarta.transaction.Transactional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao dao;

    @Override @Transactional
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override  @Transactional
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Override  @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean cargoTemFuncionarios(Long id){
        if(buscarPorId(id).getFuncionarios().isEmpty()){
            return false;
        }
        return true;
    }

}
