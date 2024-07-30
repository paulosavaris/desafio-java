package br.edu.unoesc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.Repository.TimeRepository;
import br.edu.unoesc.entities.Pessoa;
import br.edu.unoesc.entities.Time;

@Service
public class TimeService {
    @Autowired
    private TimeRepository timeRepository;

    public boolean existsByNome(String nome) {
        return timeRepository.existsByNome(nome);
    }

    public void saveTime(Time time) {
        timeRepository.save(time);
    }

    public Iterable<Time> findAll() {
        return timeRepository.findAll();
    }

    public Optional<Time> findById(Long id) {
        return timeRepository.findById(id);
    }

    public void deleteById(Long id) {
        timeRepository.deleteById(id);
    }
}
