package br.com.app.repository;

import br.com.app.entity.EmpresaFilialMatriz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaFilialMatrizRepository extends JpaRepository<EmpresaFilialMatriz, Long> {
}