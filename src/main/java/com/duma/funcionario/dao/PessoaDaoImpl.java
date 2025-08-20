package com.duma.funcionario.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;

import jakarta.persistence.TypedQuery;

@Repository
public class PessoaDaoImpl extends AbstractDao<Pessoa, Long> implements  PessoaDao{

    @Override
    public Page<Pessoa> buscarComFiltro(PessoaFiltroForm filtro, Pageable pageable) {
        String sql = "FROM Pessoa p WHERE 1=1";
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            sql += " AND lower(p.nome) LIKE lower(:nome)";
        }
        if (filtro.getSoAssessores()) {
            sql += " AND indicadorAssessor='S'";
        }

        String jpql = "SELECT p "+sql;
        TypedQuery<Pessoa> query = getEntityManager().createQuery(jpql, Pessoa.class);
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        // Paginação
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Pessoa> resultList = query.getResultList();
        // Para Page, precisamos do total
        jpql = "SELECT COUNT(p) " + sql;
        TypedQuery<Long> countQuery = getEntityManager().createQuery(jpql, Long.class);
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            countQuery.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        long total = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, total);
    }

    @Override
    public long contarComFiltro(@Param("filtro") PessoaFiltroForm filtro){
        TypedQuery<Long> countQuery = getEntityManager().createQuery(
            "SELECT COUNT(p) FROM Pessoa p WHERE lower(p.nome) LIKE lower(:nome)", Long.class);
        countQuery.setParameter("nome", "%" + filtro.getNome() + "%");
        return countQuery.getSingleResult();
    }
}