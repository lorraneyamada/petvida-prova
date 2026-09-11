package br.edu.iftm.petvida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.model.Tutor;
import br.edu.iftm.petvida.repository.AnimalRepository;
import br.edu.iftm.petvida.repository.TutorRepository;

@SpringBootApplication
public class PetvidaApplication implements CommandLineRunner {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public static void main(String[] args) {
        SpringApplication.run(PetvidaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // ---- Tutores (gravados antes dos animais: a FK exige que ja existam) ----
        Tutor marina = new Tutor(1, "Marina Alves", "34 99101-0001");
        Tutor carlos = new Tutor(2, "Carlos Prado", "34 99101-0002");
        // NN = 02 -> id_tutor = 100 + 02 = 102
        Tutor meuTutor = new Tutor(102, "Lorrane", "34 90202-0202");

        tutorRepository.salvar(marina);
        tutorRepository.salvar(carlos);
        tutorRepository.salvar(meuTutor);

        // ---- Animais da Secao 2 ----
        Animal mimi = new Animal(2, "Mimi", "gato", 3, marina);
        Animal thor = new Animal(3, "Thor", "cao", 1, carlos);
        Animal lila = new Animal(4, "Lila", "gato", 11, carlos);

        animalRepository.salvar(mimi);
        animalRepository.salvar(thor);
        animalRepository.salvar(lila);

        // ---- Meu animal (semente NN = 02) ----
        // id_animal = 100 + 02 = 102 ; nome = Pet_02 ; especie = cao ; idade = 02
        Animal meuAnimal = new Animal(102, "Pet_02", "cao", 2, meuTutor);
        animalRepository.salvar(meuAnimal);
    }
}
