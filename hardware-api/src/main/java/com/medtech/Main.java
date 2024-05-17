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

        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaUsuario = scanner.nextLine();

        Usuario usuario = usuarioDAO.retornaUsuario(nomeUsuario, senhaUsuario);

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

            try {
                    for (Disco disco : disco01.exibeDiscos()) {
                        System.out.println(disco); // Imprimir informações do disco
                        double porcentagemUsoDisco = disco01.porcentagemDeUso(); // Obter porcentagem de uso atual
                        System.out.println("Uso do disco: %.2f".formatted(porcentagemUsoDisco)); // Imprimir porcentagem de uso
                        Thread.sleep(5000); // Atraso de 5 segundos
                    }
                    System.out.println(cpu01.exibeCpu());
                    System.out.println(memoria01.exibeMemoria());
            } catch (InterruptedException e) {

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
