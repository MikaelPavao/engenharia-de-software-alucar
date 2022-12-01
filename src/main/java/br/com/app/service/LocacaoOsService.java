package br.com.app.service;

import br.com.app.dto.AluguelDto;
import br.com.app.entity.LocacaoOS;

public interface LocacaoOsService {
    LocacaoOS processarOS(AluguelDto aluguelDto);
}
