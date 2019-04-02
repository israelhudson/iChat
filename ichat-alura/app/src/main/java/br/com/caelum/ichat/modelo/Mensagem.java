package br.com.caelum.ichat.modelo;

import com.google.gson.annotations.SerializedName;

public class Mensagem {

    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagem(int id, String texto) {
        this.texto = texto;
        this.id = id;
    }

    public String getText() {
        return texto;
    }

    public int getId() {
        return id;
    }

}