package br.com.app.service;

import br.com.app.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ClienteService {
    void salvar(Cliente cliente);

    Cliente buscar(String rg);

    List<Cliente> buscarTodos();
    Page<Cliente> buscarTodosPaginado(Pageable pageable);
}
