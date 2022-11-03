package br.com.app.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioId implements Serializable {
    private static final long serialVersionUID = 4363850877825898248L;
    @Column(name = "ID_FUNCIONARIO", nullable = false)
    private Long idFuncionario;
    @Column(name = "ID_EMPRESA_FILIAL_MATRIZ", nullable = false)
    private Long idEmpresaFilialMatriz;
}