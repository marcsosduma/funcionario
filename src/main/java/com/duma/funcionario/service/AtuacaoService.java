package com.duma.funcionario.service;
import java.util.List;

import com.duma.funcionario.domain.Atuacao;

public interface AtuacaoService {

    void salvar(Atuacao atuacao);
    
    void editar(Atuacao atuacao);
    void excluir(Long id);

    Atuacao buscarPorId(Long id);

    List<Atuacao> buscarTodos();

    List<Atuacao> bucarPorCodigoPessoa(Long codigo);

}
