package com.ihudtecnologia.ichat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ihudtecnologia.ichat.R;
import com.ihudtecnologia.ichat.modelo.Mensagem;

import java.util.List;

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private int idDocliente;

    public MensagemAdapter(int idDocliente, List<Mensagem> mensagens, Activity activity){
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDocliente = idDocliente;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, viewGroup, false);

        TextView texto = (TextView) linha.findViewById(R.id.tv_texto);

        Mensagem mensagem = getItem(i);

        if(idDocliente != mensagem.getId()){
            linha.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getTexto());

        return linha;
    }
}
