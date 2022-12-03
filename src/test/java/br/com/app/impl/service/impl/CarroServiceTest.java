package br.com.app.impl.service.impl;

import br.com.app.entity.Carro;
import br.com.app.repository.CarroRepository;
import br.com.app.service.impl.CarroServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static br.com.app.impl.service.impl.ObjectFactory.*;
import static org.mockito.Mockito.*;


public class CarroServiceTest {
    @Mock
    private CarroServiceImpl service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void cadastrarCarroSuccess() {
        doNothing()
                .when(service)
                .cadastrarCarro(CARRO);

        service.cadastrarCarro(CARRO);

        verify(service, new Times(1)).cadastrarCarro(CARRO);
    }

    @Test
    public void buscarPorPlacaSuccess() {
        doReturn(CARRO)
                .when(service)
                .buscarPorPlaca(anyString());

        Carro carro = service.buscarPorPlaca(anyString());

        Assertions.assertEquals(carro, CARRO);
        verify(service, new Times(1)).buscarPorPlaca(anyString());
    }

    @Test
    public void buscarTodosPaginadoSuccess() {
        when(service.buscarTodosPaginado(PAGEABLE))
                .thenReturn(Page.empty(PAGEABLE));
        Page<Carro> carros = service.buscarTodosPaginado(PAGEABLE);

        Assertions.assertNotNull(carros);

        verify(service, new Times(1)).buscarTodosPaginado(PAGEABLE);
    }
}
