package com.medtech.model.componente.memoria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.medtech.model.componente.Hardware;

public class MonitoramentoMemoria extends Hardware {
    Looca looca = new Looca();
    Memoria memoria = looca.getMemoria();

    public MonitoramentoMemoria(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoMemoria() {
    }

    public Memoria exibeMemoria() {
        return memoria;
    }
}
