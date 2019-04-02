package com.ihudtecnologia.ichat.service;

import android.util.Log;

import com.ihudtecnologia.ichat.activity.MainActivity;
import com.ihudtecnologia.ichat.modelo.Mensagem;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ChatService {

    private MainActivity activity;

    public ChatService(MainActivity activity) {
        this.activity = activity;
    }

    public void enviar(final Mensagem mensagem){

        new Thread(new Runnable() {
            @Override
            public void run() {

                String texto = mensagem.getTexto();

                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://192.168.43.167:8080/polling").openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("content-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getId()).endObject();

                    OutputStream saida = httpURLConnection.getOutputStream();
                    PrintStream ps = new PrintStream(saida);

                    ps.println(json.toString());

                    httpURLConnection.connect();
                    httpURLConnection.getInputStream();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }

    public void ouvirMensagens(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://192.168.43.167:8080/polling").openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Accept", "application/json");

                    httpURLConnection.connect();
                    httpURLConnection.getInputStream();

                    Scanner scanner = new Scanner(httpURLConnection.getInputStream());
                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNextLine()){
                        builder.append(scanner.nextLine());
                    }

                    String json = builder.toString();

                    JSONObject jsonObject = new JSONObject(json);

                    final Mensagem mensagem = new Mensagem(jsonObject.getInt("id"), jsonObject.getString("text"));

                    Log.d("MENSAGEM", mensagem.getTexto());

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.colocaNaLista(mensagem);
                        }
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }

}
