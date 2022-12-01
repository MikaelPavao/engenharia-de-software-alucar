package br.com.app.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static br.com.app.config.utils.DefaultConstant.DEFAULT_SCHEMA;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "carros", schema = DEFAULT_SCHEMA)
public class Carro implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRO")
    private Long id;

    @Column(name = "MODELO", nullable = false, length = 200)
    private String modelo;

    @Column(name = "PLACA", nullable = false, length = 10)
    private String placa;

    @Column(name = "STATUS", nullable = false, length = 200)
    private String status;

    @Column(name = "CATEGORIA", nullable = false, length = 200)
    private String categoria;

    @ManyToMany
    @JoinTable(name = "rel_carros_locacao_os",
            joinColumns = @JoinColumn(name = "ID_CARRO",
                    referencedColumnName = "ID_CARRO",
                    foreignKey = @ForeignKey(name = "carros_rel_carros_locacao_os_fk")),
            inverseJoinColumns = @JoinColumn(name = "ID_LOCACAO_OS",
                    referencedColumnName = "ID_LOCACAO_OS",
                    foreignKey = @ForeignKey(name = "rel_carros_locacao_os_carros_fk")))
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Carro carro = (Carro) o;
        return id != null && Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}