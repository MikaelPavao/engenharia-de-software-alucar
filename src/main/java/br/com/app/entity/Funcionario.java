package br.com.app.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
public class Funcionario {
    @EmbeddedId
    private FuncionarioId id;

    @MapsId("idEmpresaFilialMatriz")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_EMPRESA_FILIAL_MATRIZ", nullable = false)
    private EmpresaFilialMatriz idEmpresaFilialMatriz;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "RG", nullable = false, length = 20)
    private String rg;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @Column(name = "TELEFONE", nullable = false, length = 15)
    private String telefone;

    @Column(name = "EMAIL", nullable = false, length = 200)
    private String email;

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs = new LinkedHashSet<>();
}