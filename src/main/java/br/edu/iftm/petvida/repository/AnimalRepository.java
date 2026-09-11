package br.edu.iftm.petvida.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.model.Tutor;

@Repository
public class AnimalRepository {

    @Autowired
    private JdbcTemplate jdbc;

    // Mapeia uma linha do JOIN (animal + tutor) para um Animal com o Tutor ja preenchido.
    private final RowMapper<Animal> animalComTutorRowMapper = (rs, rowNum) -> {
        Tutor tutor = new Tutor(
                rs.getInt("id_tutor"),
                rs.getString("nome_tutor"),
                rs.getString("telefone")
        );
        return new Animal(
                rs.getInt("id_animal"),
                rs.getString("nome_animal"),
                rs.getString("especie"),
                rs.getInt("idade"),
                tutor
        );
    };

    // A.4 - UMA unica consulta SQL com JOIN entre animal e tutor.
    public Animal buscarPorId(int id) {
        String sql = "SELECT a.id_animal, a.nome AS nome_animal, a.especie, a.idade, "
                + "t.id_tutor, t.nome AS nome_tutor, t.telefone "
                + "FROM animal a JOIN tutor t ON a.tutor_id_tutor = t.id_tutor "
                + "WHERE a.id_animal = ?";
        return jdbc.queryForObject(sql, animalComTutorRowMapper, id);
    }

    // Contagem feita pelo banco, nao em Java.
    public int contarAnimais() {
        String sql = "SELECT COUNT(*) FROM animal";
        return jdbc.queryForObject(sql, Integer.class);
    }

    // Media calculada pelo banco. CAST para DOUBLE evita que o H2 trunque
    // o resultado como inteiro (uma das "armadilhas" citadas no enunciado).
    public double mediaIdade() {
        String sql = "SELECT AVG(CAST(idade AS DOUBLE)) FROM animal";
        return jdbc.queryForObject(sql, Double.class);
    }

    // Ordenacao e limite feitos pelo SQL.
    public String animalMaisVelho() {
        String sql = "SELECT nome FROM animal ORDER BY idade DESC LIMIT 1";
        return jdbc.queryForObject(sql, String.class);
    }

    // Consulta parametrizada.
    public int contarAnimaisDoTutor(int idTutor) {
        String sql = "SELECT COUNT(*) FROM animal WHERE tutor_id_tutor = ?";
        return jdbc.queryForObject(sql, Integer.class, idTutor);
    }

    // INSERT com jdbc.update; a chave estrangeira sai de animal.getTutor().getId()
    public void salvar(Animal animal) {
        String sql = "INSERT INTO animal (id_animal, nome, especie, idade, tutor_id_tutor) "
                + "VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql, animal.getIdAnimal(), animal.getNome(), animal.getEspecie(),
                animal.getIdade(), animal.getTutor().getIdTutor());
    }
}