package com.medtech.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoBanco {

    private BasicDataSource mysqlDataSource;
    private BasicDataSource sqlServerDataSource;
    private JdbcTemplate mysqlJdbcTemplate;
    private JdbcTemplate sqlServerJdbcTemplate;

    public ConexaoBanco() {
        // Configuração do BasicDataSource para MySQL
        mysqlDataSource = new BasicDataSource();
        mysqlDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/medtech");
        mysqlDataSource.setUsername("user_medtech");
        mysqlDataSource.setPassword("URUBU100");

        // Inicialização do JdbcTemplate para MySQL
        mysqlJdbcTemplate = new JdbcTemplate(mysqlDataSource);

        // Configuração do BasicDataSource para SQL Server
        sqlServerDataSource = new BasicDataSource();
        sqlServerDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sqlServerDataSource.setUrl("jdbc:sqlserver://54.161.72.58:1433;databaseName=medtech;encrypt=true;trustServerCertificate=true");
        sqlServerDataSource.setUsername("sa");
        sqlServerDataSource.setPassword("urubu100");

        // Inicialização do JdbcTemplate para SQL Server
        sqlServerJdbcTemplate = new JdbcTemplate(sqlServerDataSource);
    }

    public JdbcTemplate getMysqlJdbcTemplate() {
        return mysqlJdbcTemplate;
    }

    public JdbcTemplate getSqlServerJdbcTemplate() {
        return sqlServerJdbcTemplate;
    }

    public Connection getMysqlConexao() throws SQLException {
        return mysqlDataSource.getConnection();
    }

    public Connection getSqlServerConexao() throws SQLException {
        return sqlServerDataSource.getConnection();
    }
}
