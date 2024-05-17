package com.medtech.model.componente.rede;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.medtech.model.componente.Hardware;

public class MonitoramentoRede extends Hardware {
    Looca looca = new Looca();
    String rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().toString();
    RedeParametros redeParametros = looca.getRede().getParametros();

    public MonitoramentoRede(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoRede() {
    }

    public String exibeRede() {
        return rede;
    }

    public RedeParametros exibeRedeP() {
        return redeParametros;
    }
}
