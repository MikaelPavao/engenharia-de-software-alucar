package br.com.app.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locacao_os")
public class LocacaoOS implements IPojo{
    @EmbeddedId
    private LocacaoOSId id;

    @MapsId("idEmpresaFilialMatriz")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_EMPRESA_FILIAL_MATRIZ", nullable = false)
    private EmpresaFilialMatriz idEmpresaFilialMatriz;

    @MapsId("idFuncionario")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_FUNCIONARIO", nullable = false, referencedColumnName = "ID_FUNCIONARIO")
    private Funcionario idFuncionario;

    @MapsId("idCliente")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente idCliente;

    @Column(name = "DATA_LOCACAO", nullable = false)
    private LocalDate dataLocacao;

    @Column(name = "DATA_ENTREGA", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "VALOR_LOCACAO", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorLocacao;

    @ManyToMany
    @JoinTable(name = "rel_carros_locacao_os",
            joinColumns = @JoinColumn(name = "ID_LOCACAO_OS"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARROS"))
    private Set<Carro> carros = new LinkedHashSet<>();

    @MapsId("idFuncionario")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_FUNCIONARIO")
    private Funcionario funcionario;
   
}