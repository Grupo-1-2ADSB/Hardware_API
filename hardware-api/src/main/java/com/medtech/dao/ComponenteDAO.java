package com.medtech.dao;

import com.medtech.model.ConexaoBanco;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;

import java.sql.*;
import java.util.Date;
import java.net.InetAddress;

public class ComponenteDAO {

    private final ConexaoBanco conexaoBanco;
    private final InserirHardwareDAO inserirHardwareDAO;

    public ComponenteDAO() {
        this.conexaoBanco = new ConexaoBanco();
        this.inserirHardwareDAO = new InserirHardwareDAO(conexaoBanco);
    }

    private MonitoramentoCpu cpu01 = new MonitoramentoCpu();

    private boolean temConexaoInternet() {
        try {
            return InetAddress.getByName("www.google.com").isReachable(3000);
        } catch (Exception e) {
            return false;
        }
    }

    public Connection obterConexaoSqlServer() throws SQLException {
        return conexaoBanco.getSqlServerConexao();
    }

    public Connection obterConexaoMysql() throws SQLException {
        return conexaoBanco.getMysqlConexao();
    }

    private boolean computadorExiste(Connection conexao, String idComputador) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Computador WHERE idComputador = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, idComputador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void inserirComputadorSeNecessario(Connection conexao, String idComputador, String nomeUsuario) throws SQLException {
        if (!computadorExiste(conexao, idComputador)) {
            int fkUnidadeHospitalar = obterFkUnidadeHospitalarDoUsuario(conexao, nomeUsuario);
            String sql = "INSERT INTO Computador (idComputador, nome, localizacao, statusPC, fkUnidadeHospitalar, fkSO) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, idComputador);
                stmt.setString(2, "NomePadrao"); // Substitua com valores apropriados
                stmt.setString(3, "LocalizacaoPadrao"); // Substitua com valores apropriados
                stmt.setString(4, "ativado"); // Substitua com o status padrão desejado
                stmt.setInt(5, fkUnidadeHospitalar); // Usando o fkUnidadeHospitalar do usuário
                stmt.setInt(6, 1); // Substitua com a fkSO apropriada
                stmt.executeUpdate();
            }
            inserirHardware(conexao, idComputador);
        }
    }

    private void inserirHardware(Connection conexao, String idComputador) throws SQLException {
        double memoriaTotal = MonitoramentoMemoria.getMemoria();
        double cpuFreqTotal = cpu01.getCpuFreqTotalGHz();
        double volumeTotal = Armazenamento.getVolumeTotalGB();

        inserirHardwareDAO.inserirHardwareEspecifico(conexao, idComputador, "Memoria RAM", "Capacidade", memoriaTotal, "Memoria RAM");
        inserirHardwareDAO.inserirHardwareEspecifico(conexao, idComputador, "Armazenamento", "Capacidade", volumeTotal, "Dispositivo de Armazanamento");
        inserirHardwareDAO.inserirHardwareEspecifico(conexao, idComputador, "CPU", "Frequencia", cpuFreqTotal, "Processado");
        inserirHardwareDAO.inserirHardwareEspecifico(conexao, idComputador, "Rede", "Velocidade", 1000.0, "Dispositivo de rede");
    }

    private void inserirRegistro(Connection conexao, double valor, String idComputador, int fkHardware) throws SQLException {
        String sql = "INSERT INTO Registro (valor, dataHora, fkComputador, fkHardware) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
            stmt.setString(3, idComputador);
            stmt.setInt(4, fkHardware);
            stmt.executeUpdate();
        }
    }

    private void inserirComputadorSeNecessarioEmAmbos(String idComputador, String nomeUsuario) throws SQLException {
        boolean internetDisponivel = temConexaoInternet();
        try (Connection conexaoMysql = obterConexaoMysql()) {
            inserirComputadorSeNecessario(conexaoMysql, idComputador, nomeUsuario);
        }
        if (internetDisponivel) {
            try (Connection conexaoSqlServer = obterConexaoSqlServer()) {
                inserirComputadorSeNecessario(conexaoSqlServer, idComputador, nomeUsuario);
            }
        }
    }

    private void inserirRegistroEmAmbos(double valor, String idComputador, int fkHardware) throws SQLException {
        boolean internetDisponivel = temConexaoInternet();
        try (Connection conexaoMysql = obterConexaoMysql()) {
            inserirRegistro(conexaoMysql, valor, idComputador, fkHardware);
        }
        if (internetDisponivel) {
            try (Connection conexaoSqlServer = obterConexaoSqlServer()) {
                inserirRegistro(conexaoSqlServer, valor, idComputador, fkHardware);
            }
        }
    }

    private int obterFkUnidadeHospitalarDoUsuario(Connection conexao, String nomeUsuario) throws SQLException {
        String sql = "SELECT fkUnidadeHospitalar FROM Usuario WHERE nomeUser = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("fkUnidadeHospitalar");
                }
            }
        }
        throw new SQLException("Usuário não encontrado: " + nomeUsuario);
    }

    public String obterNome(Connection conexao, String idComputador) throws SQLException {
        String sql = "SELECT nome FROM Computador WHERE idComputador = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, idComputador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        }
        throw new SQLException("Computador não encontrado: " + idComputador);
    }

    public String obterLocalizacao(Connection conexao, String idComputador) throws SQLException {
        String sql = "SELECT localizacao FROM Computador WHERE idComputador = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, idComputador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("localizacao");
                }
            }
        }
        throw new SQLException("Computador não encontrado: " + idComputador);
    }

//    public String obterIpRede(Connection conexao, String idComputador) throws SQLException {
//        String sql = "SELECT ipRede FROM Computador WHERE idComputador = ?";
//        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//            stmt.setString(1, idComputador);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("ipRede");
//                }
//            }
//        }
//        throw new SQLException("Computador não encontrado: " + idComputador);
//    }

    // Métodos de inserção

    private int obterFkHardware(Connection conexao, String nomeHardware) throws SQLException {
        String sql = "SELECT idHardware FROM Hardware WHERE nomeHardware = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeHardware);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idHardware");
                } else {
                    throw new SQLException("Hardware não encontrado: " + nomeHardware);
                }
            }
        }
    }


    // Métodos de inserção

    public void inserirUsoMemoria(MonitoramentoMemoria memoria, String nomeUsuario) throws SQLException {
        inserirComputadorSeNecessarioEmAmbos(cpu01.getIdCPU(), nomeUsuario);
        try (Connection conexao = obterConexaoMysql()) {
            int fkMemoria = obterFkHardware(conexao, "Memoria RAM");
            inserirRegistroEmAmbos(memoria.getMemoriaEmUsoGB(), cpu01.getIdCPU(), fkMemoria);
        }
    }

    public void inserirUsoArmazenamento(Armazenamento armazenamento, String nomeUsuario) throws SQLException {
        inserirComputadorSeNecessarioEmAmbos(cpu01.getIdCPU(), nomeUsuario);
        try (Connection conexao = obterConexaoMysql()) {
            int fkArmazenamento = obterFkHardware(conexao, "Armazenamento");
            inserirRegistroEmAmbos(armazenamento.getVolumes(), cpu01.getIdCPU(), fkArmazenamento);
        }
    }

    public void inserirUsoCpu(MonitoramentoCpu cpu, String nomeUsuario) throws SQLException {
        inserirComputadorSeNecessarioEmAmbos(cpu01.getIdCPU(), nomeUsuario);
        try (Connection conexao = obterConexaoMysql()) {
            int fkCpu = obterFkHardware(conexao, "CPU");
            inserirRegistroEmAmbos(cpu.getCpuUsoGHz(), cpu01.getIdCPU(), fkCpu);
        }
    }

    public void inserirVelocidadeRede(double velocidade, String idComputador) throws SQLException {
        inserirComputadorSeNecessarioEmAmbos(cpu01.getIdCPU(), idComputador);
        try (Connection conexao = obterConexaoMysql()) {
            int fkRede = obterFkHardware(conexao, "Rede");
            inserirRegistroEmAmbos(velocidade, cpu01.getIdCPU(), fkRede);
        }
    }

}
