package br.com.app.service.impl;

import br.com.app.entity.Cartao;
import br.com.app.entity.Cliente;
import br.com.app.repository.CartaoRepository;
import br.com.app.service.CartaoService;
import br.com.app.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CartaoSerciveImpl implements CartaoService {

    private final CartaoRepository cartaoRepository;

    private final ClienteService clienteRepository;

    @Override
    public void cadastrarCartao(Cartao cartao, String rg) {
        Set<Cartao> cartoes = new HashSet<>();
        Cliente cliente = clienteRepository.buscar(rg);
        cartoes.add(cartao);
        cliente.setCartoes(cartoes);
        cartao.setClienteCartao(cliente);
        cartaoRepository.save(cartao);
    }
}
