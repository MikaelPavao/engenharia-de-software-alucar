package br.com.app.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public interface ExceptionService {

    default void clienteEncontradoException() {
        throw new EntityExistsException("Cliente já cadastrado na base de dados!");
    }

    default void clienteNaoEncontradoException() {
        throw new EntityNotFoundException("Cliente não encontrado na base de dados!");
    }

    default void carroEncontradoException() {
        throw new EntityExistsException("Carro já cadastrado na base de dados!");
    }

    default void carroNaoEncontradoException() {
        throw new EntityNotFoundException("Carro não cadastrado na base de dados!");
    }
}
