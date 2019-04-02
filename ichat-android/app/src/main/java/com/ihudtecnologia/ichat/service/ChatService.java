package com.ihudtecnologia.ichat.service;

import com.ihudtecnologia.ichat.activity.MainActivity;
import com.ihudtecnologia.ichat.modelo.Mensagem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ChatService {

    @POST("polling")
    Call<Void> enviar(@Body Mensagem activity);

    @GET("polling")
    Call<Mensagem> ouvirMensagens();

}
