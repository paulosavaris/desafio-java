package br.edu.unoesc.entities;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Formacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nomeCurso;

    @NotNull
    private String nivelCurso;

    @Temporal(TemporalType.DATE)
    private Date dataConclusao;

    @NotNull
    private String instituicaoEnsino;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;


    // Getters e Setters
}
