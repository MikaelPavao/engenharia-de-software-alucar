package br.com.app.service.impl;

import br.com.app.dto.AluguelDto;
import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.LocacaoOS;
import br.com.app.repository.CarroRepository;
import br.com.app.repository.ClienteRepository;
import br.com.app.service.LocacaoOsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LocacaoOsServiceImpl implements LocacaoOsService {

    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public LocacaoOS processarOS(AluguelDto aluguelDto) {
        Set<Carro> carrosPorPlacaList = getCarrosPorPlaca(aluguelDto.getPlacaCarro());
        Cliente cliente = clienteRepository.findByRg(aluguelDto.getRgCliente());
        LocacaoOS.builder()
                .clienteLocacao(cliente)
                .dataLocacao(LocalDate.now())
                .dataEntrega(LocalDate.now().plusDays(aluguelDto.getQtdDias()))
                .carros(carrosPorPlacaList)
                .valorLocacao(calculaAluguel(aluguelDto.getVlAluguel(), carrosPorPlacaList.size()))
                .build();
        return null;
    }

    private Set<Carro> getCarrosPorPlaca(List<String> placa) {
        return carroRepository.findAllByPlaca(placa);
    }

    private BigDecimal calculaAluguel(BigDecimal vlAluguel, Integer vlMultiplicacao) {
        return vlAluguel.multiply(new BigDecimal(vlMultiplicacao)).setScale(2, RoundingMode.HALF_EVEN);
    }
}
