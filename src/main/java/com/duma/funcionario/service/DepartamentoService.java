package com.duma.funcionario.service;
import java.util.List;
import com.duma.funcionario.domain.Departamento;

public interface DepartamentoService {

    void salvar(Departamento departamento);
    
    void editar(Departamento departamento);
    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();

    boolean departamentoTemCargos(Long id);

}
