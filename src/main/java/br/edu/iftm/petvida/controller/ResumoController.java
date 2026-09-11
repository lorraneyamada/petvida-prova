package br.edu.iftm.petvida.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.repository.AnimalRepository;

@Controller
public class ResumoController {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/resumo_02")
    public String resumo(Model model) {
        int total = animalRepository.contarAnimais();
        double media = animalRepository.mediaIdade();
        String maisVelho = animalRepository.animalMaisVelho();
        String dataHora = LocalDateTime.now().format(FORMATO_DATA);

        model.addAttribute("totalAnimais", total);
        // Formatacao das 2 casas decimais feita aqui, no Java - a view so usa th:text.
        model.addAttribute("mediaIdade", String.format(Locale.forLanguageTag("pt-BR"), "%.2f", media));
        model.addAttribute("animalMaisVelho", maisVelho);
        model.addAttribute("dataGeracao", dataHora);

        return "resumo";
    }
}