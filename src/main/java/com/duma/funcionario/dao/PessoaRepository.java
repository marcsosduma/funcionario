package com.duma.funcionario.dao;

import com.duma.funcionario.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p " +
           "WHERE str(p.codigo) LIKE concat('%', :termo, '%') " +
           "   OR lower(p.nome) LIKE lower(concat('%', :termo, '%')) " +
           "   OR str(p.cpf) LIKE concat('%', :termo, '%') " +
           "   OR lower(p.email) LIKE lower(concat('%', :termo, '%'))")
    Page<Pessoa> buscarComFiltro(@Param("termo") String termo, Pageable pageable);

    @Query("SELECT count(p) FROM Pessoa p " +
           "WHERE str(p.codigo) LIKE concat('%', :termo, '%') " +
           "   OR lower(p.nome) LIKE lower(concat('%', :termo, '%')) " +
           "   OR str(p.cpf) LIKE concat('%', :termo, '%') " +
           "   OR lower(p.email) LIKE lower(concat('%', :termo, '%'))")
    long contarComFiltro(@Param("termo") String termo);
}
