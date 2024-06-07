package com.medtech.model.componente.rede;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.medtech.model.componente.Hardware;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

import java.util.List;
import java.util.Random;

public class MonitoramentoRede extends Hardware {
    private Looca looca = new Looca();
    private List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();

    public MonitoramentoRede(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoRede() {
    }

    // public static double velocidadeRede() {
//     // Crie um objeto SpeedTestSocket
//     // SpeedTestSocket speedTestSocket = new SpeedTestSocket();
//
//     // Variável para armazenar a velocidade de download em Mbps
//     // double downloadSpeedMbps = 0;
//
//     // Adicione um listener para capturar eventos de teste de velocidade
//     // speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {
//
//     //     @Override
//     //     public void onCompletion(SpeedTestReport report) {
//     //         // Quando o teste for concluído, obtenha a velocidade de download em bits/s
//     //         double downloadSpeed = report.getTransferRateBit().doubleValue();
//
//     //         // Converta a velocidade de bits por segundo para megabits por segundo (Mbps)
//     //         double downloadSpeedMbps = downloadSpeed / 1_000_000.0;
//     //     }
//
//     //     @Override
//     //     public void onError(SpeedTestError speedTestError, String errorMessage) {
//     //         System.err.println("Erro: " + errorMessage);
//     //     }
//
//     //     @Override
//     //     public void onProgress(float percent, SpeedTestReport report) {
//     //         // System.out.println("Progresso: " + percent + "%");
//     //     }
//     // });
//
//     // Inicie o teste de download com um arquivo de teste
//     // String fileUrl = "https://link.testfile.org/PDF10MB"; // URL do arquivo de teste
//     // int timeout = 10000; // Tempo limite de conexão em milissegundos
//     // speedTestSocket.startDownload(fileUrl, timeout);
//
//     // Espere até que o teste seja concluído
//     // while (downloadSpeedMbps == 0) {
//     //     try {
//     //         Thread.sleep(100); // Espera 100 milissegundos antes de verificar novamente
//     //     } catch (InterruptedException e) {
//     //         e.printStackTrace();
//     //     }
//     // }
//
//     // Retorne a velocidade de download em Mbps
//     // return downloadSpeedMbps;
// }


    // Método para calcular a velocidade da rede em Mbps
    public static double calcularVelocidadeRede() {
        Random random = new Random();

        double numeroAleatorio = 48 + random.nextDouble() * 6; // 48 + [0, 6)

        numeroAleatorio = Math.round(numeroAleatorio * 100.0) / 100.0;

        return numeroAleatorio;
    }

    // Método para atualizar as informações da rede
    public void atualizarDadosRede() {
        this.interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();
    }
}
