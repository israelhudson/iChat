package com.ihudtecnologia.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ihudtecnologia.ichat.app.ChatApplication;
import com.ihudtecnologia.ichat.R;
import com.ihudtecnologia.ichat.adapter.MensagemAdapter;
import com.ihudtecnologia.ichat.callback.EnviarMensagemCallBack;
import com.ihudtecnologia.ichat.callback.OuvirMensagensCallBack;
import com.ihudtecnologia.ichat.component.ChatComponent;
import com.ihudtecnologia.ichat.modelo.Mensagem;
import com.ihudtecnologia.ichat.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @BindView(R.id.et_texto)
    EditText editText;
    @BindView(R.id.btnEnviar)
    Button button;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallBack(this));

    }

    @OnClick(R.id.btnEnviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString()))
                .enqueue(new EnviarMensagemCallBack());
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
