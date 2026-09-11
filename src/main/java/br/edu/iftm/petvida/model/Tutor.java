package br.edu.iftm.petvida.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {

    private int idTutor;
    private String nome;
    private String telefone;
}