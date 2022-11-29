package br.com.app.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Table(name = "empresa_filial_matriz", schema = DEFAULT_SCHEMA)
public class EmpresaFilialMatriz implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA_FILIAL_MATRIZ", nullable = false)
    private Long id;

    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @Column(name = "CNPJ", nullable = false, length = 15)
    private String cnpj;

    @Column(name = "CIDADE", nullable = false, length = 200)
    private String cidade;

    @Column(name = "ESTADO", nullable = false, length = 120)
    private String estado;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @OneToMany(mappedBy = "EmpresaFilialMatrizfuncionario")
    @ToString.Exclude
    private Set<Funcionario> funcionarios ;

    @OneToMany(mappedBy = "empresaFilialMatrizLocacaoOS")
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs;
   }