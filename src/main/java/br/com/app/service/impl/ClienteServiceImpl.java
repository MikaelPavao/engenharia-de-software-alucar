package br.com.app.service.impl;

import br.com.app.entity.Cliente;
import br.com.app.repository.ClienteRepository;
import br.com.app.service.ClienteService;
import br.com.app.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public class ClienteServiceImpl implements ClienteService, ExceptionService {

    @Autowired
    ClienteRepository clienteRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @Override
    public Cliente buscar(String rg) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);

        Root<Cliente> rootCliente = query.from(Cliente.class);

        query.select(rootCliente)
                .where(criteriaBuilder.equal(rootCliente.get("rg"), rg));

        Cliente cliente = entityManager.createQuery(query).getSingleResult();

        Assert.isNull(cliente, clienteNaoEncontradoException());

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
    public void cadastrar(Cliente cliente) {
        validarExistenciaCliente(cliente);
        clienteRepository.saveAndFlush(cliente);
    }

    private void validarExistenciaCliente(Cliente clienteNovo) {

        buscarTodos()
                .stream()
                .filter(clienteAntigo -> clienteAntigo.getRg().equalsIgnoreCase(clienteNovo.getRg()))
                .findFirst()
                .ifPresent(clienteEncontradoException());

    }


}
