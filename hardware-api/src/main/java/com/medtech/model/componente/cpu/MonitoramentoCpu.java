package com.medtech.model.componente.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.medtech.model.componente.Hardware;

public class MonitoramentoCpu extends Hardware {
    private Looca looca = new Looca();
    private String cpuId = IpAddressFetcher.getIpAddress();
    private Long cpuFreq = looca.getProcessador().getFrequencia(); // Em Hz
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
        return (getCpuFreqGHz() * cpuUso) / 100; // Calcula o uso em GHz baseado na frequência máxima
    }

    public String getIdCPU(){
        return cpuId;
    }

    public double getCpuFreqTotalGHz() {
        return getCpuFreqGHz(); // Frequência total da CPU em GHz
    }

    public double getCpuUsoPorcentagem() {
        double usoPorcentagem = (getCpuFreqGHz() * cpuUso) / getCpuFreqTotalGHz(); // Calcula a porcentagem de uso
        return usoPorcentagem;
    }

    public void setCpuFreq(Long cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public void setCpuUso(Double cpuUso) {
        this.cpuUso = cpuUso;
    }
}
