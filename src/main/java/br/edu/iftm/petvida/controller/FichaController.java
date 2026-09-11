package br.edu.iftm.petvida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.repository.AnimalRepository;

@Controller
public class FichaController {

    private static final int MEU_ID_ANIMAL = 102; // 100 + NN (NN = 02)

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/ficha_02")
    public String ficha(Model model) {
        Animal animal = animalRepository.buscarPorId(MEU_ID_ANIMAL);

        model.addAttribute("nomeAnimal", animal.getNome());
        model.addAttribute("especieAnimal", animal.getEspecie());
        model.addAttribute("idadeAnimal", animal.getIdade());
        model.addAttribute("nomeTutor", animal.getTutor().getNome());
        model.addAttribute("telefoneTutor", animal.getTutor().getTelefone());

        return "ficha";
    }
}