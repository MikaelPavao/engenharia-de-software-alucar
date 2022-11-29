package br.com.app.service;

import br.com.app.entity.Funcionario;
import org.springframework.stereotype.Service;

public interface FuncionarioService {

    void cadastrarFuncionario(Funcionario funcionario);

    Funcionario buscarByRg(String rg);
}
