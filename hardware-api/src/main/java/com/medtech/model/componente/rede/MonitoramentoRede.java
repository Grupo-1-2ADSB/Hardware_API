package com.medtech.model.componente.rede;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.medtech.model.componente.Hardware;

import java.util.List;

public class MonitoramentoRede extends Hardware {
    private Looca looca = new Looca();
    private List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();

    public MonitoramentoRede(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoRede() {
    }

    // Método para calcular a velocidade da rede em Mbps
    public double calcularVelocidadeRedeMbps() {
        double velocidadeTotal = 0.0;
        for (RedeInterface redeInterface : interfaces) {
            velocidadeTotal += redeInterface.getBytesRecebidos() + redeInterface.getBytesEnviados();
        }
        // Converter de bytes para megabits (1 byte = 8 bits, 1 megabit = 1,000,000 bits)
        return (velocidadeTotal * 8) / 1_000_000.0;
    }

    // Método para atualizar as informações da rede
    public void atualizarDadosRede() {
        this.interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();
    }
}
