package com.medtech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.medtech.dao.ComponenteDAO;
import com.medtech.dao.UsuarioDAO;
import com.medtech.inovacao.MemoryUsageFinisher;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;
import com.medtech.model.usuario.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Looca looca = new Looca();
        Armazenamento disco01 = new Armazenamento();
        MonitoramentoMemoria memoria01 = new MonitoramentoMemoria();
        MonitoramentoCpu cpu01 = new MonitoramentoCpu();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ComponenteDAO componenteDAO = new ComponenteDAO();

        exibirBanner();

        Usuario usuario = autenticarUsuario(scanner, usuarioDAO);
        if (usuario != null && !usuario.getNomeUser().isEmpty()) {
            exibirSistema(looca);
            iniciarColetaDeDados(memoria01, cpu01, disco01, componenteDAO, usuario.getNomeUser());
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente mais tarde.");
        }

        scanner.close();
    }

    private static void exibirBanner() {
        System.out.println("""
             __  __          _ _____         _    
            |  \\/  | ___  __| |_   _|__  ___| |__ 
            | |\\/| |/ _ \\/ _` | | |/ _ \\/ __| '_ \\
            | |  | |  __/ (_| | | |  __/ (__| | | |
            |_|  |_|\\___|\\__,_| |_|\\___|\\___|_| |_|
                                                  """);
        System.out.println("=====================================");
    }

    private static Usuario autenticarUsuario(Scanner scanner, UsuarioDAO usuarioDAO) throws SQLException {
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaUsuario = scanner.nextLine();
        return usuarioDAO.retornaUsuario(nomeUsuario, senhaUsuario);
    }

    private static void exibirSistema(Looca looca) {
        System.out.print("Pegando os dados do seu computador: ");
        for (int i = 0; i <= 25; i++) {
            System.out.print("█");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("] Concluído!");
        Sistema sistema = looca.getSistema();
        System.out.println(sistema);
        System.out.println("=====================================");
    }

    private static void iniciarColetaDeDados(MonitoramentoMemoria memoria, MonitoramentoCpu cpu, Armazenamento armazenamento, ComponenteDAO componenteDAO, String nomeUsuario) {
        while (true) {
            MemoryUsageFinisher.checkMemoryUsage();
            try {
                Thread.sleep(3000);

                double memoriaEmUso = memoria.getMemoriaEmUsoGB();
                double usoCpu = cpu.getCpuFreqGHz();
                double armazenamentoEmUso = armazenamento.getVolumes();

                componenteDAO.inserirUsoMemoria(memoria, nomeUsuario);
                componenteDAO.inserirUsoArmazenamento(armazenamento, nomeUsuario);
                componenteDAO.inserirUsoCpu(cpu, nomeUsuario);

                System.out.println("Dados atuais:");
                System.out.println("Uso da Memória: " + String.format("%.2f", memoriaEmUso) + " GB");
                System.out.println("Uso da CPU: " + String.format("%.2f", usoCpu) + " GHz");
                System.out.println("Armazenamento em uso: " + String.format("%.2f", armazenamentoEmUso) + " GB");
                System.out.println();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erro ao inserir dados no banco de dados: " + e.getMessage());
            }
        }
    }
}