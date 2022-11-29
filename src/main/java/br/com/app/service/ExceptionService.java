package br.com.app.service;

import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.Funcionario;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.function.Consumer;

@Service
public interface ExceptionService {

    default void clienteEncontradoException() {
        throw new EntityExistsException("Cliente já cadastrado na base de dados!");
    }

    default void clienteNaoEncontradoException() {
        throw new EntityNotFoundException("Cliente não encontrado na base de dados!");
    }

    default void carroEncontradoException(){
        throw new EntityExistsException("Carro já cadastrado na base de dados!");
    }

    default void pagamentoNaoAceito(){
        throw new EntityExistsException("Forma de pagamento não aceita");
    }
    default void funcionarioEncontradoException(){
        throw new EntityExistsException("Funcionario já cadastrado na base de dados!");
    }

    default void funcionarioNaoEncontradoException(){
        throw new EntityNotFoundException("Funcionario não cadastrado na base de dados!");
    }

    default void carroNaoEncontradoException(){
        throw new EntityNotFoundException("Carro não cadastrado na base de dados!");
    }
}
