package com.duma.funcionario.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.duma.funcionario.domain.Atuacao;


public interface AtuacaoRepository extends CrudRepository<Atuacao, Long>{

    @Query("select a from Atuacao a where a.pessoa.codigo = :id order by a.area.descricao")
    List<Atuacao> findByPessoaId(@Param("id") Long id);
}
