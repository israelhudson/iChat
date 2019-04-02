package com.ihudtecnologia.ichat.modelo;

import com.google.gson.annotations.SerializedName;

public class Mensagem {

    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagem(int id, String texto) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
