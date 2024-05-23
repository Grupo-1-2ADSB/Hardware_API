package com.medtech.model.componente.rede;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.medtech.model.componente.Hardware;
import org.w3c.dom.stylesheets.LinkStyle;
import oshi.software.os.InternetProtocolStats;

import java.util.List;

public class MonitoramentoRede extends Hardware {
    Looca looca = new Looca();
    String rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().toString();
    RedeParametros redeParametros = looca.getRede().getParametros();
    List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();

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
