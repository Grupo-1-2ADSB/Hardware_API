package com.medtech.slack;

import com.medtech.dao.ComponenteDAO;
import com.medtech.inovacao.MemoryUsageFinisher;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class ChatGeralRAMAlta {
    private static final String SLACK_WEBHOOK_URL = "https://hooks.slack.com/services/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";
    private static double ramUsage = MemoryUsageFinisher.getSystemMemoryUsage(); // Por exemplo, 85% de uso da RAM


    public static void main(String[] args) throws SQLException {
            enviarMensagem();
    }

    public static void enviarMensagem() throws SQLException {
        JSONObject generalMessage = createSlackMessage();
        String generalMessageAsString = generalMessage.toString();
        sendMessageToSlackWebhook(SLACK_WEBHOOK_URL, generalMessageAsString);
    }

    public static void sendMessageToSlackWebhook(String webhookUrl, String message) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = message;

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully to Slack via webhook!");
            } else {
                System.out.println("Failed to send message to Slack via webhook. Response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Error sending message to Slack via webhook: " + ex.getMessage());
        }
    }
    public static JSONObject createSlackMessage() throws SQLException {
        MonitoramentoCpu cpu = new MonitoramentoCpu();
        ComponenteDAO componenteDAO = new ComponenteDAO();

        JSONObject blocks = new JSONObject()
                .put("blocks", new JSONArray()
                        .put(new JSONObject()
                                .put("type", "rich_text")
                                .put("elements", new JSONArray()
                                        .put(new JSONObject()
                                                .put("type", "rich_text_section")
                                                .put("elements", new JSONArray()
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", "🚨 Equipe do NOC, \n \n")
                                                                .put("style", new JSONObject().put("bold", true))
                                                        )
                                                )
                                        )
                                )
                        )
                        .put(new JSONObject()
                                .put("type", "rich_text")
                                .put("elements", new JSONArray()
                                        .put(new JSONObject()
                                                .put("type", "rich_text_section")
                                                .put("elements", new JSONArray()
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", "Por favor, precisamos de um técnico para investigar um problema persistente de ")
                                                        )
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", "alta urgência ")
                                                                .put("style", new JSONObject().put("bold", true))
                                                        )
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", " relacionado à memória RAM em um dos computadores da recepção.")
                                                        )
                                                )
                                        )
                                )
                        )
                        .put(new JSONObject().put("type", "divider"))
                        .put(new JSONObject()
                                .put("type", "rich_text")
                                .put("elements", new JSONArray()
                                        .put(new JSONObject()
                                                .put("type", "rich_text_section")
                                                .put("elements", new JSONArray()
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", "Detalhes do caso:\n")
                                                        )
                                                )
                                        )
                                        .put(new JSONObject()
                                                .put("type", "rich_text_list")
                                                .put("style", "bullet")
                                                .put("elements", new JSONArray()
                                                        .put(new JSONObject()
                                                                .put("type", "rich_text_section")
                                                                .put("elements", new JSONArray()
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", "Número de Identificação: ")
                                                                        )
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", cpu.getIdCPU())
                                                                        )
                                                                )
                                                        )
                                                        .put(new JSONObject()
                                                                .put("type", "rich_text_section")
                                                                .put("elements", new JSONArray()
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", "Nome do Computador: ")
                                                                        )
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", componenteDAO.obterNome(componenteDAO.obterConexaoMysql(), cpu.getIdCPU()))
                                                                        )
                                                                )
                                                        )
                                                        .put(new JSONObject()
                                                                .put("type", "rich_text_section")
                                                                .put("elements", new JSONArray()
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", "Localização: ")
                                                                        )
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", componenteDAO.obterLocalizacao(componenteDAO.obterConexaoMysql(), cpu.getIdCPU()))
                                                                        )
                                                                )
                                                        )
                                                        .put(new JSONObject()
                                                                .put("type", "rich_text_section")
                                                                .put("elements", new JSONArray()
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", "Endereço IP: ")
                                                                        )
                                                                        .put(new JSONObject()
                                                                                .put("type", "text")
                                                                                .put("text", "XX.XXX.XXX")
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .put(new JSONObject().put("type", "divider"))
                        .put(new JSONObject()
                                .put("type", "section")
                                .put("text", new JSONObject()
                                        .put("type", "mrkdwn")
                                        .put("text", "Técnico Designado: ")
                                )
                                .put("accessory", new JSONObject()
                                        .put("type", "users_select")
                                        .put("placeholder", new JSONObject()
                                                .put("type", "plain_text")
                                                .put("text", "Selecione um usuário")
                                                .put("emoji", true)
                                        )
                                        .put("action_id", "users_select_action")
                                )
                        )
                        .put(new JSONObject()
                                .put("type", "rich_text")
                                .put("elements", new JSONArray()
                                        .put(new JSONObject()
                                                .put("type", "rich_text_section")
                                                .put("elements", new JSONArray()
                                                        .put(new JSONObject()
                                                                .put("type", "text")
                                                                .put("text", "Atenciosamente, \nEquipe MedTech.")
                                                        )
                                                )
                                        )
                                )
                        )
                );

        return blocks;
    }
}
