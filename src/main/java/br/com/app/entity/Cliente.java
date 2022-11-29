package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import static br.com.app.config.utils.DefaultConstant.DEFAULT_SCHEMA;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "cliente", schema = DEFAULT_SCHEMA)
public class Cliente implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE", nullable = false)
    private Long id;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "RG", nullable = false, length = 15)
    private String rg;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "TELEFONE", nullable = false, length = 15)
    private String telefone;

    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @Column(name = "OCUPACAO", nullable = false, length = 45)
    private String ocupacao;

    @OneToMany(mappedBy = "clienteLocacao")
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs;

    @OneToMany(mappedBy = "clientePagamento")
    private Set<Pagamento> pagamento;

    @OneToMany(mappedBy = "clienteCartao")
    private Set<Cartao> cartoes;



}