package com.medtech.model.componente.armazenamento;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.medtech.model.componente.Hardware;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.List;

public class Armazenamento extends Hardware {
    Looca looca = new Looca();
    //Criação do gerenciador
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

    //Obtendo lista de discos a partir do getter
    List<Disco> discos = grupoDeDiscos.getDiscos();

    public Armazenamento(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public Armazenamento() {
    }

    public List<Disco> exibeDiscos() {
        return discos;
    }

    public Double porcentagemDeUso(){
        List<Volume> volumes = grupoDeDiscos.getVolumes();

        Double usoDisco = 0.0;
        Double volumeTotalDiscos = 0.0;
        Double volumeDisponivelDiscos = 0.0;

        for (Volume volume : volumes){
            volumeTotalDiscos += volume.getTotal();
            volumeDisponivelDiscos += volume.getDisponivel();
        }
        usoDisco = (((volumeTotalDiscos - volumeDisponivelDiscos) * 100) / volumeTotalDiscos);

        return usoDisco;
    }
}
