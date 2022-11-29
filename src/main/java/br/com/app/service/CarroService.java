package br.com.app.service;

import br.com.app.entity.Carro;
import org.springframework.stereotype.Service;

public interface CarroService {

    void cadastrarCarro(Carro carro);
    Carro buscarPorPlaca(String placa);
}
