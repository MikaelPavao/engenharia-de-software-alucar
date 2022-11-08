package br.com.app.service;

import br.com.app.entity.Cliente;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ClienteService {
    void cadastrar(Cliente cliente);

    Cliente buscar(String rg);

    List<Cliente> buscarTodos();
    Page<Cliente> buscarTodosPaginado(Pageable pageable);
}
