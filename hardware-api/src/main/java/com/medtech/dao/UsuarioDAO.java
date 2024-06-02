package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.usuario.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioDAO {
    private final ConexaoBanco conexaoBanco;

    public UsuarioDAO() {
        this.conexaoBanco = new ConexaoBanco();
    }

    public Usuario retornaUsuario(String userVerificar, String senhaVerificar) {
        Usuario usuario = null;
        try {
            JdbcTemplate sqlServerConexao = conexaoBanco.getSqlServerJdbcTemplate();
            String query = "SELECT * FROM usuario WHERE nomeUser = ? AND senha = ?";
            usuario = sqlServerConexao.queryForObject(query, new Object[]{userVerificar, senhaVerificar}, new BeanPropertyRowMapper<>(Usuario.class));
            return usuario;
        } catch (Exception ex) {
            return null;
        }
    }
}


