package br.com.app.repository;

import br.com.app.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query("select c from Carro c where placa = :placas")
    Set<Carro> findAllByPlaca(@Param("placas") List<String> placas);
}