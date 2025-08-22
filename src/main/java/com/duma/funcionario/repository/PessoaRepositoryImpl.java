package com.duma.funcionario.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import com.duma.funcionario.domain.Pessoa;
import com.duma.funcionario.dto.PessoaFiltroForm;
import com.duma.funcionario.util.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Pessoa> buscarComFiltro(PessoaFiltroForm filtro, Pageable pageable) {
        String sql = "FROM Pessoa p WHERE 1=1";
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            sql += " AND p.nomeFonetico LIKE :nome";
        }
        if (filtro.getSoAssessores()) {
            sql += " AND p.indicadorAssessor='S'";
        }
        if(filtro.getCpf()!=null && !filtro.getCpf().trim().isEmpty()){
            sql += " AND p.cpf = :cpf";
        }
        if(filtro.getArea()!=null){
            sql += " AND p.codigo in (select a.pessoa.codigo from Atuacao a where a.area.area.codigo = :area and p.codigo = a.pessoa.codigo)";
        }
        if(filtro.getSubarea()!=null){
            sql += " AND p.codigo in (select a.pessoa.codigo from Atuacao a where a.area.codigo = :subArea and p.codigo = a.pessoa.codigo)";
        }
        if(filtro.getUnidade()!= null && !filtro.getUnidade().trim().isEmpty()) {
            sql += " AND p.unidadeVinculo.codigo in (select u.codigo from Unidade u where u.nome like :nomeUnidade)";
        }

        TypedQuery<Pessoa> query = em.createQuery("SELECT p " + sql, Pessoa.class);

        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + Utils.pesquisaFonetica(filtro.getNome()) + "%");
        }
        if(filtro.getCpf()!=null && !filtro.getCpf().trim().isEmpty()){
            query.setParameter("cpf", filtro.getCpf().replaceAll("\\D", ""));
        }
        if(filtro.getArea()!=null){
            query.setParameter("area", filtro.getArea());
        }
        if(filtro.getSubarea()!=null){
            query.setParameter("subArea", filtro.getSubarea());
        }
        if(filtro.getUnidade()!= null && !filtro.getUnidade().trim().isEmpty()) {
            query.setParameter("nomeUnidade", "%" + Utils.toUpper(filtro.getUnidade().trim()).replaceAll(" ", "%")+ "%");
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Pessoa> resultList = query.getResultList();

        TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(p) " + sql, Long.class);
        // repetir os setParameter igual acima
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            countQuery.setParameter("nome", "%" + Utils.pesquisaFonetica(filtro.getNome()) + "%");
        }
        if(filtro.getCpf()!=null && !filtro.getCpf().trim().isEmpty()){
            countQuery.setParameter("cpf", filtro.getCpf().replaceAll("\\D", ""));
        }
        if(filtro.getArea()!=null){
            countQuery.setParameter("area", filtro.getArea());
        }
        if(filtro.getSubarea()!=null){
            countQuery.setParameter("subArea", filtro.getSubarea());
        }
        if(filtro.getUnidade()!= null && !filtro.getUnidade().trim().isEmpty()) {
            countQuery.setParameter("nomeUnidade", "%" + Utils.toUpper(filtro.getUnidade().trim()).replaceAll(" ", "%")+ "%");
        }

        long total = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, total);
    }

    @Override
    public long contarTodos() {
        TypedQuery<Long> countQuery = em.createQuery(
            "SELECT COUNT(p) FROM Pessoa p", Long.class);
        return countQuery.getSingleResult();
    }
}
