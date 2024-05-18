package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ComponenteDAO {

    private ConexaoBanco conexaoBanco;

    public ComponenteDAO() {
        this.conexaoBanco = new ConexaoBanco();
    }

    public void inserirUsoMemoria(MonitoramentoMemoria memoria) throws SQLException {
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, memoria.getMemoriaEmUsoGB());
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setInt(3, 1); // ID do computador mocado para teste
            stmt.setInt(4, 1); // Supondo que 1 seja o ID correspondente ao hardware de memória RAM
            stmt.executeUpdate();
        }
    }

    public void inserirUsoArmazenamento(Armazenamento armazenamento) throws SQLException {
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, armazenamento.getArmazenamentoUsadoGB());
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setInt(3, 1); // ID do computador mocado para teste
            stmt.setInt(4, 2); // Supondo que 2 seja o ID correspondente ao hardware de armazenamento
            stmt.executeUpdate();
        }
    }

    public void inserirUsoCpu(MonitoramentoCpu cpu) throws SQLException {
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, Double.parseDouble(cpu.exibeUsoCpuGHz()));
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setInt(3, 1); // ID do computador mocado para teste
            stmt.setInt(4, 3); // Supondo que 3 seja o ID correspondente ao hardware de CPU
            stmt.executeUpdate();
        }
    }
}
