package br.edu.unoesc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.Repository.PessoaRepository;
import br.edu.unoesc.entities.Formacao;
import br.edu.unoesc.entities.Pessoa;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Optional<Pessoa> findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public boolean existsByCpf(String cpf) {
        return pessoaRepository.existsByCpf(cpf);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Iterable<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
