package com.example.yurig.aparencia.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseConfig {
    private FirebaseDatabase database;
    private DatabaseReference mref;

    //NO CONSTRUTOR AS CONFIGURAÇOES INICIAIS DO FIREBASE SAO DEFINIDAS
    public FirebaseConfig(){
        database = FirebaseDatabase.getInstance();
        mref = database.getReferenceFromUrl("https://onyx-silo-199918.firebaseio.com/").child("clientes");
    }

    //ADICIONA LOCALIZAÇAO ATUAL NO FIREBASE
    public void setLocalizacao(String userId, double latitude, double longitude){
        Localizacao localizacao = new Localizacao(latitude, longitude);
        mref.child(userId).child("localizacao").child("0").child("latitude").setValue(latitude);
        mref.child(userId).child("localizacao").child("0").child("longitude").setValue(longitude);
    }

    //RETORNA REFERENCIA DA BASE DE DADOS/FIREBASE PARA AÇAO DE EVENTOS
    public DatabaseReference getMref() {
        return mref;
    }

    //GETCHILDRENCOUNT
}
