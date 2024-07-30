package br.edu.unoesc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.entities.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{
    boolean existsByNome(String nome);
}
