package com.medtech.model.componente.rede;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeParametros;

public class MonitoramentoRede {
    Looca looca = new Looca();
    String rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().toString();
    RedeParametros redeParametros = looca.getRede().getParametros();

    public String exibeRede() {
        return rede;
    }

    public RedeParametros exibeRedeP() {
        return redeParametros;
    }
}
