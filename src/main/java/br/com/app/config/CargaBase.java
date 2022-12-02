package br.com.app.config;

import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.enuns.CategoriaEnum;
import br.com.app.repository.CarroRepository;
import br.com.app.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CargaBase {
    private final CarroRepository carroRepository;

    private final ClienteRepository clienteRepository;

    public void cargaClientes() {
        log.info("Carga de Cliente");
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(Cliente.builder()
                .rg("845532135798")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build());

        clientes.add(Cliente.builder()
                .rg("98765432119")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build());

        clientes.add(Cliente.builder()
                .rg("23454325345")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build());

        clientes.add(Cliente.builder()
                .rg("45565756775")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build());
        clienteRepository.saveAllAndFlush(clientes);
    }

    public void cargaCarro() {
        log.info("Carga de Carro");
        List<Carro> carros = new ArrayList<>();

        carros.add(Carro.builder()
                .placa("UIR-4P99")
                .categoria(CategoriaEnum.PICKUP)
                .modelo("CHEVROLET")
                .status("LIVRE")
                .build());

        carros.add(Carro.builder()
                .placa("ETR-9Y55")
                .categoria(CategoriaEnum.SUV)
                .modelo("BMW")
                .status("LIVRE")
                .build());

        carros.add(Carro.builder()
                .placa("GDT-7Y55")
                .categoria(CategoriaEnum.HACTH)
                .modelo("VOLKSWAGEM")
                .status("LIVRE")
                .build());

        carros.add(Carro.builder()
                .placa("HRT-3M76")
                .categoria(CategoriaEnum.SEDA)
                .modelo("BMW")
                .status("LIVRE")
                .build());
        carroRepository.saveAllAndFlush(carros);
    }
}
