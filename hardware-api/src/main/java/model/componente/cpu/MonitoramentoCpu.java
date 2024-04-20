package model.componente.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class MonitoramentoCpu {
    Looca looca = new Looca();
    Processador cpu = looca.getProcessador();

    public Processador exibeCpu() {
        return cpu;
    }
}
