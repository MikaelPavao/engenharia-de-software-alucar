package br.com.app.controller;

import br.com.app.entity.Cliente;
import br.com.app.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

@RestController(value = "/sistema-alucar")
@Slf4j
public class AlucarController {
    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/buscar/{rg}/cliente")
    public ResponseEntity<?> buscarCliente(@PathParam(value = "rg") String rg) {
        try {
            clienteService.buscar(rg);
            return ResponseEntity
                    .ok()
                    .body(String.format("Cliente com rg {} cadastrado com sucesso", rg));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping(value = "/cadastrar/cliente")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {

        try {
            clienteService.cadastrar(cliente);
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
}
