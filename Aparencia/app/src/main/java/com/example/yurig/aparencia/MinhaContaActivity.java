package com.example.yurig.aparencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MinhaContaActivity extends AppCompatActivity {

    private TextView nome, login, telefone;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        nome = (TextView) findViewById(R.id.conta_nome);
        login = (TextView) findViewById(R.id.conta_login);
        telefone = (TextView) findViewById(R.id.conta_telefone);


        nome.setText("Yuri Gouveia");
        login.setText("yuriigouveia");
        telefone.setText("83987360638");

        btnVoltar = (Button) findViewById(R.id.conta_btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
