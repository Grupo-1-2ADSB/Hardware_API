import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import model.ConexaoBanco;
import model.componente.armazenamento.Armazenamento;
import model.componente.cpu.MonitoramentoCpu;
import model.componente.memoria.MonitoramentoMemoria;
import model.componente.rede.MonitoramentoRede;
import model.usuario.login.Login;
import org.checkerframework.checker.units.qual.A;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Looca looca = new Looca();
        Armazenamento disco01 = new Armazenamento();
        MonitoramentoMemoria memoria01 = new MonitoramentoMemoria();
        MonitoramentoCpu cpu01 = new MonitoramentoCpu();
        MonitoramentoRede rede01 = new MonitoramentoRede();

        ///ConexaoBanco conexao = new ConexaoBanco();
        ///JdbcTemplate con = conexao.getConexaoDoBanco();

        ///con.execute("DROP TABLE IF EXISTS usuario");

        ///con.execute("""
        ///CREATE TABLE usuario (
        ///idUsuario INT PRIMARY KEY AUTO_INCREMENT,
        ///nomeUser VARCHAR(45),
        ///email VARCHAR(45),
        ///senha VARCHAR(45)
        ///)
        ///""");

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

        Login login = new Login(nomeUsuario, senhaUsuario);

        String usuarioVar = "kaua@gmail.com";
        String senhaVar = "urubu100";

        if (login.verificarCredenciais(usuarioVar, senhaVar)){

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

            for (int i = 0; i < 10; i++) {

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
                            break;
                        case 3:
                            System.out.println("Você escolheu visualizar Memoria RAM:");
                            System.out.println(memoria01.exibeMemoria());
                            break;
                        case 4:
                            System.out.println("Você escolheu visualizar Rede:");
                            System.out.println(rede01.exibeRedeP());
                            System.out.println(rede01.exibeRede());
                            break;
                        case 5:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                } while (!opcaoDesejada.equals(5));

            }

        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente mais tarde");
            scanner.close();
        }

        System.out.println("=====================================");
        System.out.println();

    }
}
