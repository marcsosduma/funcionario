package com.duma.funcionario.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;

public interface PessoaRepository {
    Page<Pessoa> buscarComFiltro(PessoaFiltroForm filtro, Pageable pageable);
    long contarTodos();
}
