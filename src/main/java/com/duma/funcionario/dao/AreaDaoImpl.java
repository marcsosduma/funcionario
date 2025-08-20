package com.duma.funcionario.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.duma.funcionario.domain.Area;
import jakarta.persistence.TypedQuery;

@Repository
public class AreaDaoImpl extends AbstractDao<Area, Long> implements  AreaDao{

    @Override
    public List<Area> listAreas() {
        StringBuilder hql = new StringBuilder();
        hql.append("from Area area where area.tipo = 'A'");
        hql.append(" order by area.descricao");

        TypedQuery<Area> query = getEntityManager()
            .createQuery(hql.toString(), Area.class);

        return query.getResultList();
    }

    @Override
    public List<Area> listSubAreas(Long codigoPai){
        
        StringBuilder hql = new StringBuilder();
        hql.append("from Area area where area.tipo='S'");
        if (codigoPai != null) {
            hql.append(" and area.area.codigo= :pai");
        }
        hql.append(" order by area.descricao");
        TypedQuery<Area> query = getEntityManager()
            .createQuery(hql.toString(), Area.class);
        if (codigoPai != null)
              query.setParameter("pai", codigoPai);
        return query.getResultList();
    }
}
