package br.edu.unoesc.controller;

import br.edu.unoesc.entities.Formacao;
import br.edu.unoesc.service.FormacaoService;
import br.edu.unoesc.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormacaoController extends BaseController {

    @Autowired
    private FormacaoService formacaoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/formacoes")
    public String listFormacoes(Model model) {
        // Buscar todas as formações do serviço
        Iterable<Formacao> formacoes = formacaoService.findAll();

        // Adicionar a lista de formações ao modelo
        model.addAttribute("formacoes", formacoes);

        // Definir os atributos comuns para a página
        setCommonAttributes(model, "Formações", "Nova Formação", "/formacoes/cadastro");

        // Retornar o nome da view para renderizar
        return "formacoes/formacoes";
    }

    @GetMapping("/formacoes/cadastro")
    public String createFormacao(Model model) {
        // Adicionar um novo objeto Formacao e a lista de pessoas ao modelo
        model.addAttribute("formacao", new Formacao());
        model.addAttribute("pessoas", pessoaService.findAll());
        setCommonAttributes(model, "Cadastro de Formação", "Salvar Formação", "/formacoes");
        return "formacoes/formacaoCad";
    }

    @PostMapping("/formacoes/cadastro")
    public String saveFormacao(@RequestParam("nomeCurso") String nomeCurso,
                               @RequestParam("nivelCurso") String nivelCurso,
                               @RequestParam("dataConclusao") String dataConclusaoStr,
                               @RequestParam("instituicao") String instituicao,
                               @RequestParam("pessoaId") Long pessoaId,
                               Model model) {

        Formacao formacao = new Formacao();
        formacao.setNomeCurso(nomeCurso);
        formacao.setNivelCurso(nivelCurso);
        formacao.setDataConclusao(java.sql.Date.valueOf(dataConclusaoStr));
        formacao.setInstituicaoEnsino(instituicao);
        pessoaService.findById(pessoaId).ifPresent(formacao::setPessoa);

        formacaoService.save(formacao);

        model.addAttribute("successMessage", "Formação cadastrada com sucesso!");
        return "redirect:/formacoes";
    }
}
