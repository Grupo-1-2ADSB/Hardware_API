package com.medtech.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private JdbcTemplate jdbcTemplate;


    public ConexaoBanco() {
        BasicDataSource bds = new BasicDataSource();

        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/medtech");
        bds.setPassword("URUBU100");
        bds.setUsername("user_medtech");
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
