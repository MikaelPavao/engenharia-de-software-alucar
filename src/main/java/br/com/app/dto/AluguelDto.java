package br.com.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class AluguelDto {
    private String rgCliente;
    private List<String> placaCarro;
    private Long qtdDias;
    private BigDecimal vlAluguel;
}
