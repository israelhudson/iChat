package br.com.caelum.ichat.callback;

import br.com.caelum.ichat.activity.MainActivity;
import br.com.caelum.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagemCallback implements Callback<Mensagem> {

    private MainActivity activity;

    public OuvirMensagemCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response){
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();

            activity.colocaNaLista(mensagem);
            activity.ouvirMensagem();
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        activity.ouvirMensagem();
    }
}
