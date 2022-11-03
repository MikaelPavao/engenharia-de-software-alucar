package br.com.app.repository;

import br.com.app.entity.LocacaoOS;
import br.com.app.entity.LocacaoOSId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoOSRepository extends JpaRepository<LocacaoOS, LocacaoOSId> {
}