package com.duma.funcionario.service;
import java.util.List;

import com.duma.funcionario.domain.Area;

public interface AreaService {

    void salvar(Area area);
    
    void editar(Area area);
    void excluir(Long id);

    Area buscarPorId(Long id);

    List<Area> buscarTodas();

    List<Area> buscarPorSubAreaPorArea(Long areaId);

    List<Area> buscarArea();

}
