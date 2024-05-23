package com.medtech.model.componente.memoria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.medtech.model.componente.Hardware;

public class MonitoramentoMemoria extends Hardware {
    private Looca looca = new Looca();
    private Memoria memoria = looca.getMemoria();

    public double getMemoriaEmUsoGB() {
        return memoria.getEmUso() / (1024.0 * 1024.0 * 1024.0);
    }

    public void exibeMemoria() {
        //System.out.printf("Memória Total: %.2f GB%n", getMemoriaTotalGB());
        //System.out.printf("Memória Disponível: %.2f GB%n", getMemoriaDisponivelGB());
        System.out.printf("Memória em Uso: %.2f GB%n", getMemoriaEmUsoGB());
    }
}
