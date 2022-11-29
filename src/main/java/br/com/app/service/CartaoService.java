package br.com.app.service;

import br.com.app.entity.Cartao;
import org.springframework.stereotype.Service;

public interface CartaoService {

    void cadastrarCartao(Cartao cartao, String rg);
}
