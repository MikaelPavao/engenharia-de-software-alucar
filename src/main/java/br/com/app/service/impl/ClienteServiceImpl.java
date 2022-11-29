package br.com.app.service.impl;

import br.com.app.entity.Cliente;
import br.com.app.repository.ClienteRepository;
import br.com.app.service.ClienteService;
import br.com.app.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService, ExceptionService {


    private final ClienteRepository clienteRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @Override
    public Cliente buscar(String rg) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);

        Root<Cliente> rootCliente = query.from(Cliente.class);

        query.where(criteriaBuilder.equal(rootCliente.get("rg"), rg));

        Cliente cliente = entityManager.createQuery(query).getSingleResult();

        if (Objects.isNull(cliente))
            clienteNaoEncontradoException();

        return cliente;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Transactional
    @Override
    public Page<Cliente> buscarTodosPaginado(Pageable page) {
        return clienteRepository.findAll(page);
    }


    @Transactional
    @Override
    public void salvar(Cliente cliente) {
        validarExistenciaCliente(cliente);
        clienteRepository.saveAndFlush(cliente);
    }

    private void validarExistenciaCliente(Cliente clienteNovo) {

        Optional<Cliente> optionalCliente = buscarTodos()
                .stream()
                .filter(clienteAntigo -> clienteAntigo.getRg().equalsIgnoreCase(clienteNovo.getRg()))
                .findFirst();

        if (optionalCliente.isPresent())
            clienteEncontradoException();

    }


}
