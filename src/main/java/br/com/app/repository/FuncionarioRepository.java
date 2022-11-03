package br.com.app.repository;

import br.com.app.entity.Funcionario;
import br.com.app.entity.FuncionarioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, FuncionarioId> {
}