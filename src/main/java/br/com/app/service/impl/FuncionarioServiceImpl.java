package br.com.app.service.impl;

import br.com.app.entity.Funcionario;
import br.com.app.repository.FuncionarioRepository;
import br.com.app.service.ExceptionService;
import br.com.app.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FuncionarioServiceImpl implements FuncionarioService, ExceptionService {

    private final FuncionarioRepository funcionarioRepository;

    private final EntityManager entityManager;

    @Override
    public void cadastrarFuncionario(Funcionario funcionario) {
        validarExistenciaFuncionario(funcionario);
        funcionarioRepository.saveAndFlush(funcionario);
    }

    @Override
    public Funcionario buscarByRg(String rg) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Funcionario> query = criteriaBuilder.createQuery(Funcionario.class);
        Root<Funcionario> root = query.from(Funcionario.class);
        query.select(root).where(criteriaBuilder.equal(root.get("rg"), rg));

        Funcionario funcionario = entityManager.createQuery(query).getSingleResult();

        if (Objects.isNull(funcionario))
            funcionarioNaoEncontradoException();

        return funcionario;
    }

    private void validarExistenciaFuncionario(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findAll()
                .stream()
                .filter(_funcionario -> _funcionario.getRg().equals(funcionario.getRg()))
                .findFirst();

        if (funcionarioOptional.isPresent())
            funcionarioEncontradoException();
    }
}
