package com.medtech.model.componente.memoria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import javax.swing.JOptionPane;

public class MonitoramentoMemoria {
    Looca looca = new Looca();
    Memoria memoria = looca.getMemoria();

    Double quantidadeMemoria = Double.valueOf(looca.getMemoria().getTotal());
    Double quantidadeMemoriaUsando = Double.valueOf(looca.getMemoria().getEmUso());

    public Memoria exibirMemoria() {
        double porcentagemUso = (quantidadeMemoriaUsando / quantidadeMemoria) * 100;

        if (porcentagemUso >= 85.0 && porcentagemUso <95.0) {
            String porcentagemFormatada = String.format("%.2f", porcentagemUso);
            JOptionPane.showMessageDialog(null, "A sua memória está com " + porcentagemFormatada + "% de uso, Considere fechar alguns processos", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else if (porcentagemUso >95.0){
            String porcentagemFormatada = String.format("%.2f", porcentagemUso);
            JOptionPane.showMessageDialog(null, "A sua memória está com " + porcentagemFormatada + "% de uso, Provavelmente sua máquina vai sobrecarregar", "Emergência", JOptionPane.ERROR_MESSAGE);
        }
        return memoria;
    }
}
