package br.com.app.service;

import br.com.app.entity.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarroService {

    void cadastrarCarro(Carro carro);
    Carro buscarPorPlaca(String placa);

    Page<Carro> buscarTodosPaginado(Pageable pageable);
}
