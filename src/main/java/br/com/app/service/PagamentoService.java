package br.com.app.service;

import br.com.app.entity.Pagamento;
import org.springframework.stereotype.Service;

public interface PagamentoService {

    void registraPagamento(Pagamento pagamento);
}
