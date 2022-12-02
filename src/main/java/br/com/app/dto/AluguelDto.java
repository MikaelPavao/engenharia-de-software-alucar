package br.com.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class AluguelDto {
    private String placaCarro;
    private Long qtdDias;
    private BigDecimal vlAluguel;
}
