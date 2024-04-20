import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import model.usuario.login.Login;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
        if (login != null) {
            System.out.println("Login bem-sucedido!");
            System.out.print("Pegando os dados do seu computador: ");
            System.out.println();

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    System.out.print("█");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();
            System.out.println();

            Looca looca = new Looca();
            Sistema sistema = looca.getSistema();
            System.out.println(sistema);
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
        System.out.println("=====================================");
    }
}
