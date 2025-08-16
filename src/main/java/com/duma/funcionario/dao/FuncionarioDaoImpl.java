package com.duma.funcionario.dao;

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

}
