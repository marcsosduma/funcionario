package com.duma.funcionario.dao;

import java.util.List;
import com.duma.funcionario.domain.Area;

public interface AreaDao {

    void save(Area area);

    void update(Area area);

    void delete(Long id);

    Area findById(Long id);

    List<Area> findAll();
}
