package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static br.com.app.config.utils.DefaultConstant.DEFAULT_SCHEMA;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "cartao", schema = DEFAULT_SCHEMA)
public class Cartao implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao", nullable = false)
    private Long id;

    @Column(name = "numero_cartao")
    private Long numCartao;

    @Column(name = "codigo_seguranca")
    private Long codSeguranca;

    @Column(name = "validade")
    private String validade;

    @ManyToOne
    private Pagamento pagamento;

    @ManyToOne
    private Cliente clienteCartao;
}
