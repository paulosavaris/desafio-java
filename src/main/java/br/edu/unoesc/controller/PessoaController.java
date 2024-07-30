package br.edu.unoesc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.unoesc.Repository.PessoaRepository;
import br.edu.unoesc.Repository.TimeRepository;
import br.edu.unoesc.entities.Genero;
import br.edu.unoesc.entities.Pessoa;
import br.edu.unoesc.service.PessoaService;
import br.edu.unoesc.service.TimeService;
import jakarta.validation.Valid;

@Controller
public class PessoaController extends BaseController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TimeService timeService;

    @GetMapping("/pessoas")
    public String listPessoas(Model model) {
        // Buscar todas as pessoas do repositório
        Iterable<Pessoa> pessoas = pessoaService.findAll();

        // Adicionar a lista de pessoas ao modelo
        model.addAttribute("pessoas", pessoas);

        // Definir os atributos comuns para a página
        setCommonAttributes(model, "Pessoas", "Novo Usuário", "/pessoas/cadastro");

        // Retornar o nome da view para renderizar
        return "pessoas/pessoas";
    }

    @GetMapping("/pessoas/cadastro")
    public String createPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        model.addAttribute("times", timeService.findAll());
        setCommonAttributes(model, "Cadastro de Pessoa", "Salvar Pessoa", "/pessoas");
        return "pessoas/pessoaCadastro";
    }

    @PostMapping("/pessoas/cadastro")
    public String savePessoa(@RequestParam("nome") String nome,
                             @RequestParam("cpf") String cpf,
                             @RequestParam("dataNascimento") String dataNascimentoStr,
                             @RequestParam("genero") String genero,
                             @RequestParam("telefone") String telefone,
                             @RequestParam("email") String email,
                             @RequestParam("timeId") Long timeId,
                             Model model) {
        if (pessoaService.existsByCpf(cpf)) {
            model.addAttribute("errorMessage", "Já existe uma pessoa com o CPF fornecido.");
            model.addAttribute("times", timeService.findAll());
            setCommonAttributes(model, "Cadastro de Pessoa", "Salvar Pessoa", "/pessoas");
            return "pessoas/pessoaCadastro";
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(java.sql.Date.valueOf(dataNascimentoStr));
        pessoa.setGenero(Genero.valueOf(genero));
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        // Atribua o time ao usuário se necessário
        timeService.findById(timeId).ifPresent(pessoa::setTime);

        pessoaService.save(pessoa);

        model.addAttribute("successMessage", "Pessoa cadastrada com sucesso!");
        return "redirect:/pessoas";
    }
}
