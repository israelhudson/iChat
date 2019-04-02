package br.com.caelum.ichat.service;

import br.com.caelum.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatService {

    @GET("polling")
    Call<Mensagem> ouvirMensagens();
    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);
}