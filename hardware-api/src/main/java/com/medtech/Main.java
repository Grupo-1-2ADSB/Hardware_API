package com.medtech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.medtech.dao.ComponenteDAO;
import com.medtech.dao.UsuarioDAO;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;
import com.medtech.model.componente.rede.MonitoramentoRede;
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
        MonitoramentoRede rede01 = new MonitoramentoRede();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ComponenteDAO componenteDAO = new ComponenteDAO();

        System.out.print("Inicializando: [");
        for (int i = 0; i <= 25; i++) {
            System.out.print("█");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("] Concluído!");

        System.out.println("""
                 __  __          _ _____         _    \s
                |  \\/  | ___  __| |_   _|__  ___| |__ \s
                | |\\/| |/ _ \\/ _` | | |/ _ \\/ __| '_ \\\s
                | |  | |  __/ (_| | | |  __/ (__| | | |
                |_|  |_|\\___|\\__,_| |_|\\___|\\___|_| |_|
                                                      \s""");

        System.out.println("=====================================");

        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaUsuario = scanner.nextLine();

        Usuario usuario = usuarioDAO.retornaUsuario(nomeUsuario, senhaUsuario);

        if (usuario != null && !usuario.getNomeUser().isEmpty()) {
            System.out.println("Login bem-sucedido!");
            System.out.print("Pegando os dados do seu computador: ");

            System.out.print("Carregando: [");
            for (int i = 0; i <= 25; i++) {
                System.out.print("█");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("] Concluído!");
            System.out.println();
            Sistema sistema = looca.getSistema();
            System.out.println(sistema);
            System.out.println("=====================================");

            while (true) {
                try {
                    Thread.sleep(3000);
                    System.out.println();
                    System.out.println("Inserindo no Banco de Dados..");
                    memoria01.exibeMemoria();
                    System.out.println("Uso da CPU: %.2f GHz".formatted(cpu01.getUsoCpuGHz()));
                    System.out.println("Armazenamento em uso: %.2f GB".formatted(disco01.getVolumes()));
                    // Inserir os dados no banco de dados
                    componenteDAO.inserirUsoMemoria(memoria01);
                    componenteDAO.inserirUsoArmazenamento(disco01);
                    componenteDAO.inserirUsoCpu(cpu01);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente mais tarde");
            scanner.close();
        }

        System.out.println("=====================================");
        System.out.println();
        scanner.close();
        System.exit(1);
    }
}
