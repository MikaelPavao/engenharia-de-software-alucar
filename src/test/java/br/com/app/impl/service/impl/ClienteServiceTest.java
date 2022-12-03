package br.com.app.impl.service.impl;

import br.com.app.entity.Cliente;
import br.com.app.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.springframework.data.domain.Page;

import java.util.List;

import static br.com.app.impl.service.impl.ObjectFactory.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    ClienteService clienteService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void buscarSuccess() {
        doReturn(CLIENTE).when(clienteService).buscar(anyString());

        Cliente cli = clienteService.buscar(anyString());

        Assertions.assertNotNull(cli);

        verify(clienteService, new Times(1)).buscar(anyString());
    }

    @Test
    public void buscarTodosSuccess() {
    doReturn(CLIENTE_LIST).when(clienteService).buscarTodos();

        List<Cliente> clientes = clienteService.buscarTodos();

        Assertions.assertNotNull(clientes);

        verify(clienteService, new Times(1)).buscarTodos();
    }

    @Test
    public void buscarTodosPaginadoSuccess() {
        doReturn(Page.empty(PAGEABLE)).when(clienteService).buscarTodosPaginado(PAGEABLE);

        Page<Cliente> clientes = clienteService.buscarTodosPaginado(PAGEABLE);

        Assertions.assertNotNull(clientes);

        verify(clienteService, new Times(1)).buscarTodosPaginado(PAGEABLE);
    }


    @Test
    public void salvar() {
        doNothing().when(clienteService).salvar(CLIENTE);

        clienteService.salvar(CLIENTE);

        verify(clienteService).salvar(CLIENTE);
    }


}



