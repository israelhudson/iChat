package com.ihudtecnologia.ichat.app;

import android.app.Application;

import com.ihudtecnologia.ichat.component.ChatComponent;
import com.ihudtecnologia.ichat.component.DaggerChatComponent;

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {

        component = DaggerChatComponent.builder().build();

    }

    public ChatComponent getComponent(){
        return component;
    }
}
