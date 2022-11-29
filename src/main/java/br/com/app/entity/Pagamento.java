package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static br.com.app.config.utils.DefaultConstant.DEFAULT_SCHEMA;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "pagamento", schema = DEFAULT_SCHEMA)
public class Pagamento implements IPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", nullable = false)
    private Long id;

    @Column(name = "valor_pagamento")
    private BigDecimal vlPagamento;

    @OneToMany(mappedBy = "pagamento")
//    @JoinColumn(name = "id_cartao", referencedColumnName = "id_pagamento", foreignKey = @ForeignKey(name = "pagamento_cartao_fk"))
    private Set<Cartao> cartoes;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "pagamento_cliente_fk"))
    private Cliente clientePagamento;

}
