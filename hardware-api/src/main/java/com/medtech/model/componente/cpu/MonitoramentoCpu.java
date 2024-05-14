package com.medtech.model.componente.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.medtech.model.componente.Hardware;

public class MonitoramentoCpu extends Hardware {
    Looca looca = new Looca();
    String cpuFabricante = looca.getProcessador().getFabricante();
    String cpuId = looca.getProcessador().getId();
    String cpuIdentificador = looca.getProcessador().getIdentificador();
    String cpuNome = looca.getProcessador().getNome();
    String cpuMicro = looca.getProcessador().getMicroarquitetura();
    Long cpuFreq = looca.getProcessador().getFrequencia();
    Integer cpuFisicas = looca.getProcessador().getNumeroCpusFisicas();
    Integer cpuLogicas = looca.getProcessador().getNumeroCpusLogicas();
    Double cpuUso = looca.getProcessador().getUso();

    public MonitoramentoCpu(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public String exibeCpu() {
        String cpuInfo = String.format("""
        Fabricante: %s
        Id: %s
        Identificador: %s
        Nome: %s
        Micro-arquitetura: %s
        Frequência: %s
        Cpus Físicas: %d
        Cpus Lógicas: %d
        Uso da Cpu: %.2f""",
                cpuFabricante, cpuId, cpuIdentificador, cpuNome, cpuMicro, cpuFreq, cpuFisicas, cpuLogicas, cpuUso);
        return cpuInfo;
    }
}
