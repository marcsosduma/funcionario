package com.duma.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duma.funcionario.dao.AreaDao;
import com.duma.funcionario.domain.Area;

import jakarta.transaction.Transactional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao dao;

    @Override @Transactional
    public void salvar(Area area) {
        dao.save(area);
    }

    @Override  @Transactional
    public void editar(Area area) {
        dao.update(area);
    }

    @Override  @Transactional
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Area buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Area> buscarTodas() {
        return dao.findAll();
    }

    @Override
    public List<Area> buscarPorSubAreaPorArea(Long areaId) {
       return dao.listSubAreas(areaId);
    }

    @Override
    public List<Area> buscarArea() {
        return dao.listAreas();
    }

}
