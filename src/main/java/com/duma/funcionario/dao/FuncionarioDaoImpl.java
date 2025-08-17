package com.duma.funcionario.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.duma.funcionario.domain.Funcionario;

import jakarta.persistence.TypedQuery;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements  FuncionarioDao{

    @Override
    public List<Funcionario> findByNome(String nome) {
        /*
        TypedQuery<Funcionario> query = getEntityManager()
            .createNamedQuery("select f from Funcionario f where f.nome like :nome", Funcionario.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
        */
        return createQuery("select f from Funcionario f where f.nome like concat('%', ?1, '%')",  nome);
    }

    @Override
    public List<Funcionario> findByCargo(Long id) {
        // TODO Auto-generated method stub
        return createQuery("select f from Funcionario f where f.cargo.id = ?1",  id);
    }

    @Override
    public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
        // TODO Auto-generated method stub
        String sql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2 ")
                .append("order by f.dataEntrada asc")
                .toString();
        return createQuery(sql, entrada, saida);
    }

    @Override
    public List<Funcionario> findByDataEntrada(LocalDate entrada) {
        // TODO Auto-generated method stub
        String sql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataEntrada = ?1")
                .append("order by f.dataEntrada asc")
                .toString();
        return createQuery(sql, entrada);
    }

    @Override
    public List<Funcionario> findByDataSaida(LocalDate saida) {
        String sql = new StringBuilder("select f from Funcionario f ")
                .append("where f.dataSaida = ?1 ")
                .append("order by f.dataSaida asc")
                .toString();
        return createQuery(sql, saida);
    }

}
