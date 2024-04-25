package com.medtech.model.componente.memoria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

public class MonitoramentoMemoria {
    Looca looca = new Looca();
    Memoria memoria = looca.getMemoria();

    public Memoria exibeMemoria() {
        return memoria;
    }
}
