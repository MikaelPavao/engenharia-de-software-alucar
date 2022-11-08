package br.com.app.service;

import br.com.app.entity.Cliente;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.function.Consumer;

public interface ExceptionService {

    default Consumer<? super Cliente> clienteEncontradoException() {
        throw new EntityExistsException("Cliente já cadastrado na base de dados");
    }

    default String clienteNaoEncontradoException() {
        throw new EntityNotFoundException("Cliente não encontrado na base de dados");
    }


}
