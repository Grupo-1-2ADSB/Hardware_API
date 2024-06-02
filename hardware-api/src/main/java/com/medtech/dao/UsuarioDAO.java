package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.usuario.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

public class UsuarioDAO {
    private final ConexaoBanco conexaoBanco;

    public UsuarioDAO() {
        this.conexaoBanco = new ConexaoBanco();
    }

    public Usuario retornaUsuario(String userVerificar, String senhaVerificar) {
        Usuario usuario = null;

        if (isInternetAvailable()) {
            // Tenta autenticar no SQL Server se houver conexão com a internet
            try {
                JdbcTemplate sqlServerConexao = conexaoBanco.getSqlServerJdbcTemplate();
                String query = "SELECT * FROM usuario WHERE nomeUser = ? AND senha = ?";
                usuario = sqlServerConexao.queryForObject(query, new Object[]{userVerificar, senhaVerificar}, new BeanPropertyRowMapper<>(Usuario.class));
                return usuario;
            } catch (Exception ex) {
                return null; // Falha na autenticação no SQL Server
            }
        } else {
            // Tenta autenticar no MySQL local se não houver conexão com a internet
            try {
                JdbcTemplate mysqlConexao = conexaoBanco.getMysqlJdbcTemplate();
                String query = "SELECT * FROM usuario WHERE nomeUser = ? AND senha = ?";
                usuario = mysqlConexao.queryForObject(query, new Object[]{userVerificar, senhaVerificar}, new BeanPropertyRowMapper<>(Usuario.class));
                return usuario;
            } catch (Exception e) {
                return null; // Falha na autenticação no MySQL local
            }
        }
    }

    private boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(1000);
        } catch (IOException e) {
            return false;
        }
    }
}
