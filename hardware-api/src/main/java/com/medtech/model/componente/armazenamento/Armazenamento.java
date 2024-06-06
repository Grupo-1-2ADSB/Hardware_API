package com.medtech.model.componente.armazenamento;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import  com.github.britooo.looca.api.group.discos.Volume;
import com.medtech.model.componente.Hardware;

import java.util.List;

public class Armazenamento extends Hardware {
    private static Looca looca = new Looca();
    private static List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

    public Armazenamento(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public Armazenamento() {
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

    public static double getVolumeTotalGB() {
        double volumeTotal = 0.0;
        for (Volume volume : volumes) {
            volumeTotal += volume.getTotal();
        }

        return volumeTotal / (1024.0 * 1024.0 * 1024.0); // Convertendo de bytes para gigabytes
    }

}
