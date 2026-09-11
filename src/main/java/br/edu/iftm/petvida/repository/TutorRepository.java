package br.edu.iftm.petvida.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.edu.iftm.petvida.model.Tutor;

@Repository
public class TutorRepository {

    @Autowired
    private JdbcTemplate jdbc;

    // RowMapper: converte cada linha do ResultSet em um objeto Tutor.
    private final RowMapper<Tutor> tutorRowMapper = (rs, rowNum) -> new Tutor(
            rs.getInt("id_tutor"),
            rs.getString("nome"),
            rs.getString("telefone")
    );

    public Tutor buscarPorId(int id) {
        String sql = "SELECT id_tutor, nome, telefone FROM tutor WHERE id_tutor = ?";
        return jdbc.queryForObject(sql, tutorRowMapper, id);
    }

    public void salvar(Tutor tutor) {
        String sql = "INSERT INTO tutor (id_tutor, nome, telefone) VALUES (?, ?, ?)";
        jdbc.update(sql, tutor.getIdTutor(), tutor.getNome(), tutor.getTelefone());
    }
}