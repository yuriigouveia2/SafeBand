package com.example.yurig.aparencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AjudaActivity extends AppCompatActivity {

    private TextView textoAjuda;
    private String texto;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        textoAjuda = (TextView) findViewById(R.id.ajuda_texto);
        texto = "SafeBand é um aplicativo de segurança o qual informa a contatos de confiança " +
        "a sua situação ao informar que está em alguma situação de perigo. O aplicativo ainda " +
        "está em desenvolvimento";

        textoAjuda.setText(texto);

        btnVoltar = (Button) findViewById(R.id.ajuda_btn_voltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
