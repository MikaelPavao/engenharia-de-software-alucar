package br.com.app.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carros")
public class Carro implements IPojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARROS", nullable = false)
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
            joinColumns = @JoinColumn(name = "ID_CARROS",
                    referencedColumnName = "ID_CARROS",
                    foreignKey = @ForeignKey(name = "carros_rel_carros_locacao_os_fk")),
            inverseJoinColumns = @JoinColumn(name = "ID_LOCACAO_OS",
                    referencedColumnName = "ID_LOCACAO_OS",
                    foreignKey = @ForeignKey(name = "rel_carros_locacao_os_carros_fk")))
    @ToString.Exclude
    private Set<LocacaoOS> locacaoOs = new LinkedHashSet<>();

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