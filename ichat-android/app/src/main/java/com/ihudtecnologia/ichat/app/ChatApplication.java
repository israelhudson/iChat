package com.ihudtecnologia.ichat.app;

import android.app.Application;

import com.ihudtecnologia.ichat.service.ChatService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApplication extends Application {

    public ChatService getChatService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

}
