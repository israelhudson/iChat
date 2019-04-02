package com.ihudtecnologia.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ihudtecnologia.ichat.R;
import com.ihudtecnologia.ichat.adapter.MensagemAdapter;
import com.ihudtecnologia.ichat.modelo.Mensagem;
import com.ihudtecnologia.ichat.service.ChatService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        chatService = new ChatService(MainActivity.this);
        chatService.ouvirMensagens();

        button = (Button) findViewById(R.id.btnEnviar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString()));
            }
        });

    }

    public void colocaNaLista(Mensagem mensagem){
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        chatService.ouvirMensagens();

    }
}
