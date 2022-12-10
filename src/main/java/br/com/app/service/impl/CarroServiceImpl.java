package br.com.app.service.impl;

import br.com.app.entity.Carro;
import br.com.app.repository.CarroRepository;
import br.com.app.service.CarroService;
import br.com.app.service.ExceptionService;
import br.com.app.service.report.factory.AluguelCarrosReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarroServiceImpl implements CarroService, ExceptionService {

    private final CarroRepository carroRepository;
    private final EntityManager entityManager;

    @Override
    public void cadastrarCarro(Carro carro) {
        voidvalidarExistenciaCarro(carro);
        carroRepository.saveAndFlush(carro);
    }

    @Override
    public Carro buscarPorPlaca(String placa) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Carro> query = criteriaBuilder.createQuery(Carro.class);
        Root<Carro> root = query.from(Carro.class);
        query.select(root).where(criteriaBuilder.equal(root.get("placa"), placa));
        Carro carro = new Carro();
        try {
            carro = entityManager.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            carroNaoEncontradoException();
        }
        if (Objects.isNull(carro))
            carroNaoEncontradoException();

        return carro;
    }

    @Override
    public Page<Carro> buscarTodosPaginado(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    @Override
    public void gerarRelatorio(Carro carro) {
        new AluguelCarrosReport();
    }

    private void voidvalidarExistenciaCarro(Carro carro) {
        Optional<Carro> optionalCarro = carroRepository.findAll().stream().filter(_carro -> _carro.getPlaca().equals(carro.getPlaca()))
                .findFirst();

        if (optionalCarro.isPresent())
            carroEncontradoException();
    }
}
