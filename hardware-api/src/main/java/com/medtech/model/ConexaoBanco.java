package com.medtech.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoBanco {

    private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public ConexaoBanco() {
        // Configuração do BasicDataSource
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/medtech");
        dataSource.setUsername("user_medtech");
        dataSource.setPassword("URUBU100");

        // Inicialização do JdbcTemplate com o dataSource
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public Connection getConexao() throws SQLException {
        // Obter uma conexão a partir do dataSource
        return dataSource.getConnection();
    }
}
