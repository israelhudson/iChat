package com.ihudtecnologia.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ihudtecnologia.ichat.R;
import com.ihudtecnologia.ichat.adapter.MensagemAdapter;
import com.ihudtecnologia.ichat.app.ChatApplication;
import com.ihudtecnologia.ichat.callback.EnviarMensagemCallBack;
import com.ihudtecnologia.ichat.callback.OuvirMensagensCallBack;
import com.ihudtecnologia.ichat.modelo.Mensagem;
import com.ihudtecnologia.ichat.service.ChatService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;
    private EditText editText;
    private Button button;
    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);

        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.et_texto);

        ChatApplication app = (ChatApplication) getApplication();
        this.chatService = app.getChatService();

        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallBack(this));

        button = (Button) findViewById(R.id.btnEnviar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString()))
                        .enqueue(new EnviarMensagemCallBack());
            }
        });

    }

    public void colocaNaLista(Mensagem mensagem){
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        ouvirMensagem();

    }

    public void ouvirMensagem(){
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallBack(this));
    }
}
