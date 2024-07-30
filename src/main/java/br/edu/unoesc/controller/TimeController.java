package br.edu.unoesc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.unoesc.entities.Time;
import br.edu.unoesc.service.TimeService;

@Controller
public class TimeController extends BaseController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/times")
    public String listTimes(Model model) {
        setCommonAttributes(model, "Times", "Novo Time", "/times/cadastro");
        return "times/times";
    }

    @GetMapping("/times/cadastro")
    public String createTime(Model model) {
        setCommonAttributes(model, "Cadastro de Time", "Salvar Time", "/times");
        return "times/timesCadastro";
    }

    @PostMapping("/times/cadastro")
    public String saveTime(@RequestParam("time") String nome,
            @RequestParam("setor") String setor,
            Model model) {
        if (timeService.existsByNome(nome)) {
            model.addAttribute("errorMessage", "Já existe um time com o nome fornecido.");
            setCommonAttributes(model, "Cadastro de Time", "Salvar Time", "/times");
            return "times/timesCadastro";
        }

        Time time = new Time();
        time.setNome(nome);
        time.setSetor(setor);
        timeService.saveTime(time);

        model.addAttribute("successMessage", "Time cadastrado com sucesso!");
        return "redirect:/times";
    }
}
