package br.com.app.service;

import br.com.app.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ClienteService {
    void salvar(Cliente cliente);

    Cliente buscar(String rg);

    List<Cliente> buscarTodos();
    Page<Cliente> buscarTodosPaginado(Pageable pageable);
}
