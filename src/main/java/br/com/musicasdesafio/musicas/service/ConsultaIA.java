package br.com.musicasdesafio.musicas.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;


public class ConsultaIA {

    public static String obterInformacao(String texto) {
        String apiKey = System.getenv("GROQ_APIKEY");
        String url = "https://api.groq.com/openai/v1/chat/completions";

        String jsonDesejado = """
            {
              "model": "llama-3.3-70b-versatile",
              "messages": [
                {
                  "role": "user",
                  "content": "Me dê um resumo curto e profissional sobre o artista musical: %s"
                }
              ]
            }
            """.formatted(texto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonDesejado))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Erro Groq (Status " + response.statusCode() + "): " + response.body();
            }

            JSONObject obj = new JSONObject(response.body());
            return obj.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            return "Erro ao processar: " + e.getMessage();
        }
    }
}