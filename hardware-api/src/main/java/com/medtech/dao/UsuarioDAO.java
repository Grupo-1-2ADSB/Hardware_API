package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.usuario.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario retornaUsuario(String userVerificar, String senhaVerificar) throws SQLException {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate sqlServerConexao = conexaoBanco.getSqlServerJdbcTemplate();

        Usuario usuario = null;
        try {
            String query = "SELECT * FROM usuario WHERE nomeUser = ? AND senha = ?";
            usuario = sqlServerConexao.queryForObject(query, new Object[]{userVerificar, senhaVerificar}, new BeanPropertyRowMapper<>(Usuario.class));
        } catch (Exception e) {
            return usuario;
        }

        return usuario;
    }
}
