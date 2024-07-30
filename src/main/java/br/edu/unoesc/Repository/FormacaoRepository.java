package br.edu.unoesc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.entities.Formacao;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, Long>{
    
}
