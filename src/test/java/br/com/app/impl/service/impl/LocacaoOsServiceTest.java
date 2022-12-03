package br.com.app.impl.service.impl;

import br.com.app.entity.LocacaoOS;
import br.com.app.service.LocacaoOsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import java.math.BigDecimal;

import static br.com.app.impl.service.impl.ObjectFactory.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class LocacaoOsServiceTest {

    @Mock
    LocacaoOsService locacaoOsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void processarOS() {
        doReturn(LOCACAO_OS).when(locacaoOsService).processarOS(LIST_ALUGUEL_DTO, RG);

        LocacaoOS locacaoOS = locacaoOsService.processarOS(LIST_ALUGUEL_DTO, RG);

        BigDecimal valorLocacao = locacaoOS.getValorLocacao();

        Assertions.assertEquals(valorLocacao, new BigDecimal("1009.90"));

        verify(locacaoOsService, new Times(1)).processarOS(LIST_ALUGUEL_DTO, RG);
    }

}
