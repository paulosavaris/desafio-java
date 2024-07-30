package br.edu.unoesc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone;

    @Email
    @NotNull
    private String email;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @OneToMany(mappedBy = "pessoa")
    private Set<Formacao> formacoes;

}
