package br.com.app.controller;

import br.com.app.entity.Carro;
import br.com.app.entity.Cartao;
import br.com.app.entity.Cliente;
import br.com.app.entity.Pagamento;
import br.com.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

@Controller
@RestController
@Slf4j
@RequiredArgsConstructor
public class AlucarController {
    private final ClienteService clienteService;

    private final CarroService carroService;

    private final FuncionarioService funcionarioService;

    private final PagamentoService pagamentoService;

    private final CartaoService cartaoService;


    @GetMapping(value = "/buscar/{rg}/cliente")
    public ResponseEntity<?> buscarCliente(@PathParam("rg") String rg) {
        try {
            Cliente cliente = clienteService.buscar(rg);
            return ResponseEntity
                    .ok()
                    .body(cliente);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cadastrar/cliente")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {

        try {
            clienteService.salvar(cliente);
            return ResponseEntity
                    .ok()
                    .body("Cliente cadastrado com sucesso");
        } catch (EntityExistsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @GetMapping(value = "/listar/cliente")
    public ResponseEntity<Page<Cliente>> listarClientes(@PageableDefault(sort = "nome",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 10) Pageable pageable) {

        try {
            Page<Cliente> clientes = clienteService.buscarTodosPaginado(pageable);
            return ResponseEntity.ok().body(clientes);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/cadastrar/cliente/pagamento")
    public ResponseEntity<?> cadastrarClientePagamento(@RequestBody Pagamento pagamento) {

        try {
            pagamentoService.registraPagamento(pagamento);
            return ResponseEntity
                    .ok()
                    .body("Pagamento efetuado com sucesso");
        } catch (EntityExistsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cadastrar/{rg}/cartao")
    public ResponseEntity<?> cadastrarClienteCartao(@RequestBody Cartao cartao, @PathVariable("rg") String rg) {

        try {
            cartaoService.cadastrarCartao(cartao, rg);
            return ResponseEntity
                    .ok()
                    .body("Cartão cliente cadastrado com sucesso");
        } catch (EntityExistsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cadastrar/veiculo")
    public ResponseEntity<?> cadastrarVeiculo(@RequestBody Carro carro) {

        try {
            carroService.cadastrarCarro(carro);
            return ResponseEntity
                    .ok()
                    .body("Veiculo cadastrado com sucesso");
        } catch (EntityExistsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }


}
