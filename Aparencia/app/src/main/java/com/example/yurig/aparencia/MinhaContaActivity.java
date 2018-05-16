package com.example.yurig.aparencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yurig.aparencia.Firebase.FirebaseConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MinhaContaActivity extends AppCompatActivity {

    private TextView nome, usuario, celular;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        nome = (TextView) findViewById(R.id.conta_nome);
        usuario = (TextView) findViewById(R.id.conta_login);
        celular = (TextView) findViewById(R.id.conta_telefone);



        new FirebaseConfig().getMref().child("cliente2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nome.setText(dataSnapshot.child("nome").getValue(String.class));
                usuario.setText(dataSnapshot.child("usuario").getValue(String.class));
                celular.setText(dataSnapshot.child("celular").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnVoltar = (Button) findViewById(R.id.conta_btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
