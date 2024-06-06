package com.medtech.slack;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatUsuarioEspecifico {
    private static final String SLACK_API_URL = "https://slack.com/api/chat.postMessage";
    private static final String SLACK_USER_TOKEN = "xoxb-6735886365092-7008920321761-UpOclz27QA88VJVsk6p5yOT6";

    public static void main(String[] args) {
        JSONArray slackBlocks = createSlackBlocks();
        String technicianUserId = "U06MKCRE1PC";

        sendMessageToSlack(technicianUserId, slackBlocks);
    }

    public static void sendMessageToSlack(String technicianUserId, JSONArray blocks) {
        try {
            URL url = new URL(SLACK_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + SLACK_USER_TOKEN);

            // Construct JSON payload
            JSONObject payload = new JSONObject();
            payload.put("channel", technicianUserId);
            payload.put("blocks", blocks);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully to Slack!");
            } else {
                System.out.println("Failed to send message to Slack. Response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Error sending message to Slack: " + ex.getMessage());
        }
    }


    public static JSONArray createSlackBlocks () {
        JSONArray blocks = new JSONArray();

        blocks.put(new JSONObject()
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
        );
        blocks.put(new JSONObject()
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
                                                .put("text", " relacionado ao espaço em disco em um dos computadores da recepção.")
                                        )
                                )
                        )
                )
        );
        blocks.put(new JSONObject().put("type", "divider"));
        blocks.put(new JSONObject()
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
                                                                .put("text", "001")
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
                                                                .put("text", "Nominho do PC")
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
                                                                .put("text", "Recepção")
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
        );
        blocks.put(new JSONObject().put("type", "divider"));
        blocks.put(new JSONObject()
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
        );
        blocks.put(new JSONObject()
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
        );

        return blocks;
    }
}