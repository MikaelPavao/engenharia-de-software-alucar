package br.com.app.service.impl;

import br.com.app.dto.AluguelDto;
import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.LocacaoOS;
import br.com.app.repository.CarroRepository;
import br.com.app.repository.ClienteRepository;
import br.com.app.repository.LocacaoOsRepository;
import br.com.app.service.LocacaoOsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocacaoOsServiceImpl implements LocacaoOsService {

    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    private final LocacaoOsRepository locacaoOsRepository;

    @Override
    public LocacaoOS processarOS(List<AluguelDto> aluguelDtoList, String rg) {
        Long qtdDias = aluguelDtoList.get(0).getQtdDias();
        Cliente cliente = clienteRepository.findByRg(rg);
        Set<Carro> carros = getCarros(aluguelDtoList);
        Map<BigDecimal, BigDecimal> mapValorAluguel = getMapValorAluguel(carros, aluguelDtoList);

        return locacaoOsRepository.saveAndFlush(LocacaoOS.builder()
                .clienteLocacao(cliente)
                .dataLocacao(LocalDate.now())
                .dataEntrega(LocalDate.now().plusDays(qtdDias))
                .carros(carros)
                .valorLocacao(calculaValorAlugue(mapValorAluguel))
                .build());

    }

    private Map<BigDecimal, BigDecimal> getMapValorAluguel(Set<Carro> carros, List<AluguelDto> aluguelDtoList) {
        Map<BigDecimal, BigDecimal> map = new HashMap<>();
        aluguelDtoList.forEach(aluguelDto -> {
            carros.stream().filter(carro -> carro.getPlaca().equals(aluguelDto.getPlacaCarro())).forEach(carro -> {
                map.put(aluguelDto.getVlAluguel(), carro.fatorMultiplicador(carro.getCategoria()));
            });
        });
        return map;
    }

    private BigDecimal calculaValorAlugue(Map<BigDecimal, BigDecimal> map) {
        AtomicReference<BigDecimal> valorTotal = new AtomicReference<>(BigDecimal.ZERO);
        map.forEach((valorAlugel, fatorMultiplicador) -> {
            valorTotal.updateAndGet(v -> valorTotal.get().add(valorAlugel.multiply(fatorMultiplicador)));
        });

        return valorTotal.get();
    }

    private Set<Carro> getCarros(List<AluguelDto> aluguelDtoList) {
        List<String> placas = aluguelDtoList.stream().map(AluguelDto::getPlacaCarro).collect(Collectors.toList());
        return carroRepository.findAllByPlaca(placas);
    }
}
