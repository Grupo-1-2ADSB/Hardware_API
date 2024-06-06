package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.componente.cpu.MonitoramentoCpu;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserirHardwareDAO {
    private final ConexaoBanco conexaoBanco;
    private MonitoramentoCpu cpu01 = new MonitoramentoCpu();

    public InserirHardwareDAO(ConexaoBanco conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    private boolean temConexaoInternet() {
        try {
            return InetAddress.getByName("www.google.com").isReachable(3000);
        } catch (Exception e) {
            return false;
        }
    }

    private Connection obterConexaoSqlServer() throws SQLException {
        return conexaoBanco.getSqlServerConexao();
    }

    private Connection obterConexaoMysql() throws SQLException {
        return conexaoBanco.getMysqlConexao();
    }

    public void inserirHardwareEspecifico(Connection conexao, String idComputador, String nomeHardware, String descricaoValor, double valor, String descricaoHardware) throws SQLException {
        String sql = "INSERT INTO Hardware (nomeHardware, descricaoValor, valor, descricaoHardware, fkComputador) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeHardware);
            stmt.setString(2, descricaoValor);
            stmt.setDouble(3, valor);
            stmt.setString(4, descricaoHardware);
            stmt.setString(5, idComputador);
            stmt.executeUpdate();
        }
    }
}
