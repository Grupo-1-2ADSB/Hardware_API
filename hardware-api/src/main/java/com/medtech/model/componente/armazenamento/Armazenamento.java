package com.medtech.model.componente.armazenamento;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import  com.github.britooo.looca.api.group.discos.Volume;
import com.medtech.model.componente.Hardware;

import java.util.List;

public class Armazenamento extends Hardware {
    private Looca looca = new Looca();
    private List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();

    private List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

    public Armazenamento(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public Armazenamento() {
    }

    public double getArmazenamentoUsadoGB() {
        long totalArmazenamento = 0;
        long usadoArmazenamento = 0;

        for (Disco disco : discos) {
            totalArmazenamento += disco.getTamanho();
            usadoArmazenamento += disco.getTamanho() - disco.getEscritas();
        }

        return usadoArmazenamento / 1_073_741_824.0; // Convertendo de bytes para GB
    }

    public double getVolumes(){
        double volumeAtual = 0.0;
        double volumeTotal = 0.0;
        double volumeDisp = 0.0;
        for (Volume volume : volumes){

            volumeTotal += volume.getTotal();
            volumeDisp += volume.getDisponivel();
            volumeAtual += volumeTotal - volumeDisp;
        }


        double totalVolumeInGB = volumeAtual / (1024 * 1024 * 1024);


        return totalVolumeInGB;
    }

}
