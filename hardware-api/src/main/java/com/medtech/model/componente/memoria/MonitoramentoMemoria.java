package com.medtech.model.componente.memoria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.medtech.model.componente.Hardware;

public class MonitoramentoMemoria extends Hardware {
    private static Looca looca = new Looca();
    private static Memoria memoria = looca.getMemoria();

    public double getMemoriaEmUsoGB() {
        return memoria.getEmUso() / (1024.0 * 1024.0 * 1024.0);
    }

    public static double getMemoria() {
        return memoria.getTotal() / (1024.0 * 1024.0 * 1024.0); // Retorna em GB
    }

    public void exibeMemoria() {
        System.out.printf("Memória em Uso: %.2f GB%n", getMemoriaEmUsoGB());
    }
}
