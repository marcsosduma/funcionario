package com.duma.funcionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.duma.funcionario.domain.Area;

public interface AreaRepository extends CrudRepository<Area, Long> {

    List<Area> findByArea(Area area);

    @Query("select a from Area a where a.area.codigo = :id and a.tipo='A' order by a.descricao")
    List<Area> listGrandeArea(@Param("id") Long id);

    @Query("select a from Area a where a.tipo='A' order by a.descricao")
    List<Area> listGrandeArea();

    @Query("select a from Area a inner join a.area g where g.codigo = :id and a.tipo='S' order by a.descricao")
    List<Area> listSubAreaPorGrandArea(@Param("id") Long id);

    @Query("select a from Area a where a.tipo='S' order by a.descricao")
    List<Area> listSubArea();

}
