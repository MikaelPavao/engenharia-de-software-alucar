package br.com.app.service;

import br.com.app.dto.AluguelDto;
import br.com.app.entity.LocacaoOS;

import java.util.List;

public interface LocacaoOsService {
    LocacaoOS processarOS(List<AluguelDto> aluguelDto, String rg);
}
