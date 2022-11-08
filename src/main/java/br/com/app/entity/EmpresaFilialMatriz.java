package br.com.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa_filial_matriz")
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

    @OneToMany(mappedBy = "idEmpresaFilialMatriz")
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEmpresaFilialMatriz")
    private Set<LocacaoOS> locacaoOs = new LinkedHashSet<>();

    public Set<LocacaoOS> getLocacaoOs() {
        return locacaoOs;
    }

    public void setLocacaoOs(Set<LocacaoOS> locacaoOs) {
        this.locacaoOs = locacaoOs;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}