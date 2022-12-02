package br.com.app.controller;

import br.com.app.config.CargaBase;
import br.com.app.dto.AluguelDto;
import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.LocacaoOS;
import br.com.app.service.CarroService;
import br.com.app.service.ClienteService;
import br.com.app.service.LocacaoOsService;
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
import java.util.List;

@Controller
@RestController
@Slf4j
@RequiredArgsConstructor
public class AlucarController {
    private final ClienteService clienteService;

    private final CarroService carroService;

    private final LocacaoOsService locacaoOsService;

    private final CargaBase cargaBase;

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

    @PostMapping(value = "/cadastrar/carro")
    public ResponseEntity<?> cadastrarCarro(@RequestBody Carro carro) {

        try {
            carroService.cadastrarCarro(carro);
            return ResponseEntity
                    .ok()
                    .body("Carro cadastrado com sucesso");
        } catch (EntityExistsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @GetMapping(value = "/buscar/carro/{placa}")
    public ResponseEntity<?> buscarCarro(@PathVariable("placa") String placa) {

        try {
            Carro carro = carroService.buscarPorPlaca(placa);
            return ResponseEntity.ok().body(carro);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value = "/listar/carros")
    public ResponseEntity<Page<Carro>> listarCarros(@PageableDefault(sort = "modelo",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 10) Pageable pageable) {

        try {
            Page<Carro> carros = carroService.buscarTodosPaginado(pageable);
            return ResponseEntity.ok().body(carros);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping(value = "/logar/cliente/{usuario}/senha/{senha}")
    public ResponseEntity<?> logarSistema(@PathVariable("usuario") String usuario, @PathVariable("senha") String senha) {

        if (usuario.equals("admin") && senha.endsWith("1"))
            return ResponseEntity.ok().body("Logado com sucesso");
        else
            return ResponseEntity.badRequest().body("Não permitido logar");
    }

    @PostMapping(value = "/ordem-servico/cliente/{rg}/efetivar")
    public ResponseEntity<LocacaoOS> efetivarAluguel(@PathVariable("rg") String rgCliente, @RequestBody List<AluguelDto> alugueldto) {

        try {
            return ResponseEntity.ok().body(locacaoOsService.processarOS(alugueldto, rgCliente));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/carga-base")
    public void cargaBase() {
        cargaBase.cargaClientes();
        cargaBase.cargaCarro();
    }

}
