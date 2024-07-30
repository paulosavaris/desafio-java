package br.edu.unoesc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.Repository.FormacaoRepository;
import br.edu.unoesc.entities.Formacao;
import br.edu.unoesc.entities.Time;

@Service
public class FormacaoService {
    @Autowired
    private FormacaoRepository formacaoRepository;

    public Formacao save(Formacao formacao) {
        return formacaoRepository.save(formacao);
    }

    public Optional<Formacao> findById(Long id) {
        return formacaoRepository.findById(id);
    }

    public void deleteById(Long id) {
        formacaoRepository.deleteById(id);
    }

    public Iterable<Formacao> findAll() {
        return formacaoRepository.findAll();
    }
}
