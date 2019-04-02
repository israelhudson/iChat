package com.ihudtecnologia.ichat.callback;

import com.ihudtecnologia.ichat.activity.MainActivity;
import com.ihudtecnologia.ichat.modelo.Mensagem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallBack implements Callback<Mensagem> {

    private MainActivity activity;

    public OuvirMensagensCallBack(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();
            activity.colocaNaLista(mensagem);
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

        activity.ouvirMensagem();
    }
}
