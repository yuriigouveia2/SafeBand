package com.example.yurig.aparencia.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConfig {
    private FirebaseDatabase database;
    private DatabaseReference mref;

    //NO CONSTRUTOR AS CONFIGURAÇOES INICIAIS DO FIREBASE SAO DEFINIDAS
    public FirebaseConfig(){
        database = FirebaseDatabase.getInstance();
        mref = database.getReferenceFromUrl("https://onyx-silo-199918.firebaseio.com/").child("clientes");
    }

    //ADICIONA LOCALIZAÇAO ATUAL NO FIREBASE
    public void setLocalizacao(String userId, double latitude, double longitude){//ADICIONAR POSIÇAO DA LOCALIZACAO (...,int pos)
        Localizacao localizacao = new Localizacao(latitude, longitude);
        mref.child(userId).child("localizacao").child("0").child("latitude").setValue(latitude);
        mref.child(userId).child("localizacao").child("0").child("longitude").setValue(longitude);
    }

    //RETORNA REFERENCIA DA BASE DE DADOS/FIREBASE PARA AÇAO DE EVENTOS
    public DatabaseReference getMref() {
        return mref;
    }

    //PEGA O ARRAY DE LOCALIZAÇAO E GUARDA NA LISTA PASSADA COMO PARAMETRO
    public void getArrayLoc(String userId, final List<DatabaseReference> localizacaoArray){
        final List<DatabaseReference> locList = new ArrayList<>();
        final DatabaseReference data = mref.child(userId).child("localizacao");

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cont = (int) dataSnapshot.getChildrenCount();
                for(int i = 0; i < cont; i++){
                    locList.add(data.child(String.valueOf(i)));
                }
                localizacaoArray.addAll(locList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //SALVA ARRAY DE LOCALIZAÇOES NO FIREBASE
    public void saveArrayLoc(String userId, final List<DatabaseReference> localizacaoArray, Localizacao loc){
        int size = localizacaoArray.size();

        localizacaoArray.add(localizacaoArray.get(size + 1).child(String.valueOf(size + 1)));

        localizacaoArray.get(size + 1).child(String.valueOf(size + 1)).child("latitude").setValue(loc.latitude);
        localizacaoArray.get(size + 1).child(String.valueOf(size + 1)).child("longitude").setValue(loc.longitude);
    }
}
