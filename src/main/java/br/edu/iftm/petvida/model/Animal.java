package br.edu.iftm.petvida.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    private int idAnimal;
    private String nome;
    private String especie;
    private int idade;
    private Tutor tutor;
}