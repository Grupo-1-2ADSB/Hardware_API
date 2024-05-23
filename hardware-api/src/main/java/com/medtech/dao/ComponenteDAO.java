package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ComponenteDAO {

    private ConexaoBanco conexaoBanco;

    public ComponenteDAO() {
        this.conexaoBanco = new ConexaoBanco();
    }

    MonitoramentoCpu cpu01 = new MonitoramentoCpu();

    private boolean computadorExiste(String idComputador) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Computador WHERE idComputador = ?";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setString(1, idComputador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void inserirComputadorSeNecessario(String idComputador) throws SQLException {
        if (!computadorExiste(idComputador)) {
            String sql = "INSERT INTO Computador (idComputador, nome, localizacao, statusPC, fkUnidadeHospitalar, fkSO) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
                stmt.setString(1, idComputador);
                stmt.setString(2, "NomePadrao"); // Substitua com valores apropriados
                stmt.setString(3, "LocalizacaoPadrao"); // Substitua com valores apropriados
                stmt.setString(4, "ativado"); // Substitua com o status padrão desejado
                stmt.setInt(5, 1); // Substitua com a fkUnidadeHospitalar apropriada
                stmt.setInt(6, 1); // Substitua com a fkSO apropriada
                stmt.executeUpdate();

            }
        }
    }

    public void inserirUsoMemoria(MonitoramentoMemoria memoria) throws SQLException {
        inserirComputadorSeNecessario(cpu01.getIdCPU()); // Verifica e insere o computador se necessário
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, memoria.getMemoriaEmUsoGB());
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setString(3, cpu01.getIdCPU()); // ID do computador = idCPU
            stmt.setInt(4, 1); // Supondo que 1 seja o ID correspondente ao hardware de memória RAM
            stmt.executeUpdate();
        }
    }

    public void inserirUsoArmazenamento(Armazenamento armazenamento) throws SQLException {
        inserirComputadorSeNecessario(cpu01.getIdCPU()); // Verifica e insere o computador se necessário
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, armazenamento.getVolumes());
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setString(3, cpu01.getIdCPU()); // ID do computador = idCPU
            stmt.setInt(4, 2); // Supondo que 2 seja o ID correspondente ao hardware de armazenamento
            stmt.executeUpdate();
        }
    }

    public void inserirUsoCpu(MonitoramentoCpu cpu) throws SQLException {
        inserirComputadorSeNecessario(cpu01.getIdCPU()); // Verifica e insere o computador se necessário
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexaoBanco.getConexao().prepareStatement(sql)) {
            stmt.setDouble(1, cpu.getUsoCpuGHz());
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setString(3, cpu01.getIdCPU()); // ID do computador = idCPU
            stmt.setInt(4, 3); // Supondo que 3 seja o ID correspondente ao hardware de CPU
            stmt.executeUpdate();
        }
    }
}
