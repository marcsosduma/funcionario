package com.duma.funcionario.domain;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

@Column(name = "nome", nullable = false, length = 60)
private String nome;

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

@ManyToOne
@JoinColumn(name = "id_departamento_fk")
private Departamento departamento;

public Departamento getDepartamento() {
    return departamento;
}

public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
}

@OneToMany(mappedBy = "cargo")
private List<Funcionario> funcionarios;

public List<Funcionario> getFuncionarios() {
    return funcionarios;
}

public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
}

}
