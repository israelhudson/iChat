package com.ihudtecnologia.ichat.component;

import com.ihudtecnologia.ichat.activity.MainActivity;
import com.ihudtecnologia.ichat.module.ChatModule;

import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
}
