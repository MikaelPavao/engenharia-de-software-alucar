package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static br.com.app.config.utils.DefaultConstant.DEFAULT_SCHEMA;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "locacao_os", schema = DEFAULT_SCHEMA)
public class LocacaoOS implements IPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOCACAO_OS", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente clienteLocacao;

    @Column(name = "DATA_LOCACAO", nullable = false)
    private LocalDate dataLocacao;

    @Column(name = "DATA_ENTREGA", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "VALOR_LOCACAO", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorLocacao;

    @ManyToMany
    @JoinTable(name = "rel_carros_locacao_os",
            joinColumns = @JoinColumn(name = "ID_LOCACAO_OS"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARRO"))
    private Set<Carro> carros;

}