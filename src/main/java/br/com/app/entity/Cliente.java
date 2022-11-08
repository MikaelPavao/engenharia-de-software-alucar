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
@Table(name = "cliente")
public class Cliente implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE", nullable = false)
    private Long id;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

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

    @OneToMany(mappedBy = "idCliente")
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs = new LinkedHashSet<>();

}