package br.edu.iftm.petvida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.model.Tutor;
import br.edu.iftm.petvida.repository.AnimalRepository;
import br.edu.iftm.petvida.repository.TutorRepository;

@Controller
public class TutorController {

    private static final int MEU_ID_TUTOR = 102; // 100 + NN (NN = 02)

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/tutor_02")
    public String tutor(Model model) {
        Tutor tutor = tutorRepository.buscarPorId(MEU_ID_TUTOR);
        int quantidadeAnimais = animalRepository.contarAnimaisDoTutor(MEU_ID_TUTOR);

        model.addAttribute("nomeTutor", tutor.getNome());
        model.addAttribute("telefoneTutor", tutor.getTelefone());
        model.addAttribute("quantidadeAnimais", quantidadeAnimais);

        return "tutor";
    }
}