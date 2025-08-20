package com.duma.funcionario.dao;

import java.util.List;
import com.duma.funcionario.domain.Atuacao;

public interface AtuacaoDao {

    void save(Atuacao atuacao);

    void update(Atuacao atuacao);

    void delete(Long id);

    Atuacao findById(Long id);

    List<Atuacao> findAll();

    List<Atuacao> bucarPorCodigoPessoa(Long codigo);
}
