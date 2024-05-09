package com.medtech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.mysql.cj.util.StringUtils;
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

        Integer total2 = 25;
        System.out.print("Inicializando: [");
        for (int i = 0; i <= total2; i++) {
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

        System.out.print("Digite seu email: ");
        String emailUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaUsuario = scanner.nextLine();

        Usuario usuario = usuarioDAO.retornaUsuario(emailUsuario, senhaUsuario);

        if (usuario != null && !StringUtils.isNullOrEmpty(usuario.getNomeUser())){

            System.out.println("Login bem-sucedido!");
            System.out.print("Pegando os dados do seu computador: ");

            Integer total = 25;
            System.out.print("Carregando: [");
            for (int i = 0; i <= total; i++) {
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

                Integer opcaoDesejada;
                do {
                    System.out.println("""
                    Qual processo deseja visualizar?
                    1 - Armazenamento
                    2 - CPU
                    3 - Memoria RAM
                    4 - Rede
                    5 - Sair
                    """);
                    opcaoDesejada = scanner.nextInt();

                    switch (opcaoDesejada) {
                        case 1:
                            System.out.println("Você escolheu visualizar Armazenamento:");
                            for (Disco disco : disco01.exibeDiscos()) {
                                System.out.println(disco);
                            }
                            break;
                        case 2:
                            System.out.println("Você escolheu visualizar CPU:");
                            System.out.println(cpu01.exibeCpu());
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("Você escolheu visualizar Memoria RAM:");
                            System.out.println(memoria01.exibirMemoria());
                            break;
                        case 4:
                            System.out.println("Você escolheu visualizar Rede:");
                            System.out.println(rede01.exibeRedeP());
                            System.out.println(rede01.exibeRede());
                            System.out.println();
                            break;
                        case 5:
                            Integer total3 = 5;
                            System.out.print("Saindo: [");
                            for (int i = 0; i <= total3; i++) {
                                System.out.print(".");
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("] Concluído!");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }

                } while (!opcaoDesejada.equals(5));

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
