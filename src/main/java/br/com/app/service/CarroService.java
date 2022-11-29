package br.com.app.service;

import br.com.app.entity.Carro;

public interface CarroService {

    void cadastrarCarro(Carro carro);
    Carro buscarPorPlaca(String placa);
}
