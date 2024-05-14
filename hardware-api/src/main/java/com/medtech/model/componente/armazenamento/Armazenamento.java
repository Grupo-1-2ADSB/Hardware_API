package com.medtech.model.componente.armazenamento;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.medtech.model.componente.Hardware;

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
}
