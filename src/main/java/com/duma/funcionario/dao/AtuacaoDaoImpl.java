package com.duma.funcionario.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.duma.funcionario.domain.Atuacao;

@Repository
public class AtuacaoDaoImpl extends AbstractDao<Atuacao, Long> implements AtuacaoDao{

    @Override
    public List<Atuacao> bucarPorCodigoPessoa(Long codigo) {
         return createQuery("select a from Atuacao a where a.pessoa.codigo = ?1",  codigo);
    }
}
