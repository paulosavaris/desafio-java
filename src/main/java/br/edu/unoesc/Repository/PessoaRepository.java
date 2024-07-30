package br.edu.unoesc.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
