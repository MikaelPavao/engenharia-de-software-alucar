package br.com.app.impl.service.impl;

import br.com.app.dto.AluguelDto;
import br.com.app.entity.Carro;
import br.com.app.entity.Cliente;
import br.com.app.entity.LocacaoOS;
import br.com.app.entity.enuns.CategoriaEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ObjectFactory {

    static Carro CARRO = createCarro();
    static Pageable PAGEABLE = createPageable();
    static LocacaoOS LOCACAO_OS = createLocacaoOS();
    static Cliente CLIENTE = createCliente();
    static List<Cliente> CLIENTE_LIST = createClienteList();

    static String RG = "9854875412";
    static List<AluguelDto> LIST_ALUGUEL_DTO = createListAluguelDto();

    public static Carro createCarro() {
        return Carro.builder()
                .status("Livre")
                .modelo("Land Hover")
                .placa("WER-1E34")
                .categoria(CategoriaEnum.SUV)
                .build();
    }

    public static Pageable createPageable() {
        return PageRequest.of(1, 10);
    }

    public static List<Carro> createListCarro() {
        return Arrays.asList(Carro.builder()
                .status("Livre")
                .modelo("Land Hover")
                .placa("wer-1234")
                .categoria(CategoriaEnum.SUV)
                .build());
    }

    public static Cliente createCliente() {
        return Cliente.builder()
                .rg("45565756775")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build();
    }

    public static List<Cliente> createClienteList() {
        return Arrays.asList(Cliente.builder()
                .rg("45565756775")
                .dataNascimento(new Date())
                .email("teste@teste.com.br")
                .nome("Fulano")
                .endereco("rua beltrano 98")
                .ocupacao("Trabalhando")
                .telefone("5190238736")
                .build());
    }

    public static LocacaoOS createLocacaoOS() {
        return LocacaoOS.builder()
                .clienteLocacao(createCliente())
                .carros(Set.of(createCarro()))
                .dataLocacao(LocalDate.now())
                .dataEntrega(LocalDate.now().plusDays(5))
                .valorLocacao(createCarro()
                        .fatorMultiplicador(createCarro().getCategoria())
                        .multiply(createAluguelDto().getVlAluguel()).setScale(2, RoundingMode.HALF_EVEN))
                .build();
    }

    public static List<AluguelDto> createListAluguelDto() {
        return Arrays.asList(AluguelDto.builder()
                .vlAluguel(new BigDecimal(100.99))
                .qtdDias(3L)
                .placaCarro("WER-1E34")
                .build());
    }

    public static AluguelDto createAluguelDto() {
        return createListAluguelDto().get(0);
    }

}
