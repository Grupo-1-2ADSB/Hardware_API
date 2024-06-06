package com.medtech.inovacao;

import com.medtech.slack.ChatGeralRAMAlta;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoryUsageFinisher {

    private static final double ACCEPTABLE_MEMORY_USAGE_PERCENTAGE = 85.0; // 85% de uso de memória como limite
    private static final List<String> ESSENTIAL_PROCESSES = Arrays.asList(
            "mysqld.exe", "MySQLWorkbench.exe", "System", "java.exe", "idea64.exe", "smss.exe", "csrss.exe", "wininit.exe", "services.exe", "lsass.exe", "lsm.exe", "svchost.exe", "winlogon.exe", "explorer.exe", "Windows Explorer", "taskhostw.exe", "taskbar.exe", "Taskbar", "shellExperienceHost.exe", "dwm.exe", "Desktop Window Manager", "POWERPNT.EXE", "Microsoft PowerPoint Background Task Handler", "OfficeService.exe"
    ); //Lista de Prioridade ou White List dos processos que não podem ser encerrados

    public static void checkMemoryUsage() throws SQLException {
        double memoryUsage = getSystemMemoryUsage();
//        System.out.println("""
//                Uso de memória: %.2f%%""".formatted(memoryUsage));

        if (memoryUsage > ACCEPTABLE_MEMORY_USAGE_PERCENTAGE) {
            if (memoryUsage > 95) {
                ChatGeralRAMAlta.enviarMensagem();
            }
            finishHighMemoryProcesses();
        }
    } //Método que faz a checagem do uso de memória e se necessário chama o método de finalizar processos

    public static double getSystemMemoryUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        long totalMemory = 0;
        long freeMemory = 0;

        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean sunOsBean = (com.sun.management.OperatingSystemMXBean) osBean;
            totalMemory = sunOsBean.getTotalPhysicalMemorySize();
            freeMemory = sunOsBean.getFreePhysicalMemorySize();
        }

        long usedMemory = totalMemory - freeMemory;
        return (double) usedMemory / totalMemory * 100;
    } //Método que traz o valor de memória sendo utilizado no computador atualmente

    private static void finishHighMemoryProcesses() {
        List<ProcessInfo> processes = null;
        List<String> lines = null;
        //Inicializando listas

        try {
            Process process = Runtime.getRuntime().exec("tasklist /FO CSV /NH"); //Comando que lista todos os processos atuais no computador com filtros para separar a tabela em linhas (String)
            Scanner scanner = new Scanner(process.getInputStream()); //Leitura da resposta do comando tasklist
            scanner.useDelimiter("\n"); //delimitador a cada pulo de linha da resposta do comando, evitando erro multiple points
            processes = new ArrayList<>();
            lines = new ArrayList<>();

            // Limita o número de linhas a serem lidas
            Integer maxLines = 999; // Ajuste conforme necessário
            Integer linesRead = 0;

            while (scanner.hasNextLine() && linesRead < maxLines) { //Vai rodar enquanto o scanner ver uma próxima linha e as linhas lidas forem menores que o máximo de linhas
                String line = scanner.nextLine();
                String[] tokens = line.split(","); //Separa os valores de cada linha da resposta do tasklist em tokens (entre as vírgulas)
                if (tokens.length >= 5) {
                    String processName = tokens[0].replaceAll("\"", ""); //Nome do processo está no índice 0 da linha
                    String memoryUsageStr = tokens[4].replaceAll("\"", "").replaceAll(" K", ""); //Nome do processo está no índice 4 da linha

                    //Extrai uso de memória com expressão regular
                    Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");

                    Matcher matcher = pattern.matcher(memoryUsageStr);
                    if (matcher.find()) {
                        double memoryUsageMB = Double.parseDouble(matcher.group()) / 1024.0; //Converte para MB
                        processes.add(new ProcessInfo(processName, memoryUsageMB)); //Adiciona nome e uso de memória do processo atual em uma lista
                    }
                }
                linesRead++;
            }

            processes.sort(Comparator.comparingDouble(ProcessInfo::getMemoryUsage).reversed()); // Ordena os processos por uso de memória em ordem decrescente

            System.out.println("Uso da memória acima do limite aceitável! Começando finalização preventiva de processos...");
            System.out.println();

            int limiteDaLeva = 10;

            for (int i = 0; i <= limiteDaLeva; i++) {
                String processName = processes.get(i).getProcessName();
                double memoryUsage = processes.get(i).getMemoryUsage();

                if (!isEssentialProcess(processName)) {
                    System.out.println("""
                        Processo: %s , Uso de memória: %.2fMB""".formatted(processName, memoryUsage));
                }

                if (isEssentialProcess(processName)) {
                    System.out.println("Processo essencial encontrado: " + processName + ". Ignorando.");
                    limiteDaLeva++;
                    continue; // Pular para a próxima iteração do loop
                }

                System.out.println("Encerrando o processo: " + processName);
                finishProcessByName(processName);
            }

            process.waitFor();
        } catch (Exception e) {
            System.err.println("Erro ao verificar os processos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Limpar a lista de processos e linhas para a próxima verificação ocorrer sem erros
            processes.clear();
            lines.clear();
        }
    } //Método que finaliza os processos do computador a partir de quais utilizam mais memória RAM,
    // ignorando os nomes na lista de prioridade também

    private static boolean isEssentialProcess(String processName) {
        for (String essentialProcess : ESSENTIAL_PROCESSES) {
            if (processName.equalsIgnoreCase(essentialProcess)) {
                return true;
            }
        }
        return false;
    } //Método que verifica se o nome do processo faz parte da lista de prioridade

    private static void finishProcessByName(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//Método que finaliza um processo conforme o nome recebido
}
