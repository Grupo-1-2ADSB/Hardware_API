package com.medtech.model.componente.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.medtech.model.componente.Hardware;

public class MonitoramentoCpu extends Hardware {
    private Looca looca = new Looca();
    private String cpuFabricante = looca.getProcessador().getFabricante();
    private String cpuId = looca.getProcessador().getId();
    private String cpuIdentificador = looca.getProcessador().getIdentificador();
    private String cpuNome = looca.getProcessador().getNome();
    private String cpuMicro = looca.getProcessador().getMicroarquitetura();
    private Long cpuFreq = looca.getProcessador().getFrequencia(); // Em Hz
    private Integer cpuFisicas = looca.getProcessador().getNumeroCpusFisicas();
    private Integer cpuLogicas = looca.getProcessador().getNumeroCpusLogicas();
    private Double cpuUso = looca.getProcessador().getUso();

    public MonitoramentoCpu(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoCpu() {
    }

    public double getCpuFreqGHz() {
        return cpuFreq / 1_000_000_000.0; // Converte de Hz para GHz
    }

    public double getCpuUsoGHz() {
        double cpuFreqGHz = getCpuFreqGHz();
        return (cpuFreqGHz * cpuUso) / 100; // Calcula o uso em GHz baseado na frequência máxima
    }

    public String exibeCpu() {
        String cpuInfo = String.format("""
        CPU
        Fabricante: %s
        Id: %s
        Identificador: %s
        Nome: %s
        Frequência: %.2f GHz
        Uso da CPU: %.2f%%
        """,
                cpuFabricante, cpuId, cpuIdentificador, cpuNome, getCpuFreqGHz(), cpuUso);
        return cpuInfo;
    }

    public String exibeUsoCpuGHz() {
        return String.format("Uso da CPU: %.2f GHz", getCpuUsoGHz());
    }

}
