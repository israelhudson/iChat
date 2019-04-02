package com.ihudtecnologia.ichat.module;

import com.ihudtecnologia.ichat.service.ChatService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ChatModule {

    @Provides
    public ChatService getChatService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

}
