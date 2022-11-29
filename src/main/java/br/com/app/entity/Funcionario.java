package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
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
@Table(name = "funcionario", schema = DEFAULT_SCHEMA)
public class Funcionario implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_EMPRESA_FILIAL_MATRIZ", nullable = false, referencedColumnName = "ID_EMPRESA_FILIAL_MATRIZ")
    private EmpresaFilialMatriz EmpresaFilialMatrizfuncionario;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "RG", nullable = false, length = 20, unique = true)
    private String rg;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @Column(name = "TELEFONE", nullable = false, length = 15)
    private String telefone;

    @Column(name = "EMAIL", nullable = false, length = 200)
    private String email;

    @OneToMany(mappedBy = "funcionarioLocacao")
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs;
}