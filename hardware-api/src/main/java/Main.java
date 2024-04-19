import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Looca looca = new Looca();
        Sistema sistema = looca.getSistema();
        System.out.println(sistema);

        //Criação do gerenciador
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

//Obtendo lista de discos a partir do getter
        List<Disco> discos = grupoDeDiscos.getDiscos();
        for (Disco disco : discos) {
            System.out.println(disco);
        }
}}