package com.duma.funcionario.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;

public interface PessoaDao{
    
    void save(Pessoa pessoa);

    void update(Pessoa pessoa);

    void delete(Long id);

    Pessoa findById(Long id);

    List<Pessoa> findAll();

    Page<Pessoa> buscarComFiltro(@Param("filtro") PessoaFiltroForm filtro, Pageable pageable);

    long contarComFiltro(@Param("filtro") PessoaFiltroForm filtro);

}
