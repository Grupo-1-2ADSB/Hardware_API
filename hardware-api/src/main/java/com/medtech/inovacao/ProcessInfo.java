package com.medtech.inovacao;

public class ProcessInfo {
    private String processName;
    private double memoryUsageMB;

    public ProcessInfo(String processName, double memoryUsageMB) {
        this.processName = processName;
        this.memoryUsageMB = memoryUsageMB;
    }

    public String getProcessName() {
        return processName;
    }

    public double getMemoryUsage() {
        return memoryUsageMB;
    }
} //Classe auxiliar que armazena as informações do processo registrado no momento (nome e uso de memória em MB)
// o método finishHighMemoryProcesses faz uma lista dos processos e a suas infos