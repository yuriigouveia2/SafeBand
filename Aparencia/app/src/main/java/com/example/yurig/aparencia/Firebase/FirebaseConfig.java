package com.example.yurig.aparencia.Firebase;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
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
    private Marker marker;

    //NO CONSTRUTOR AS CONFIGURAÇOES INICIAIS DO FIREBASE SAO DEFINIDAS
    public FirebaseConfig(){
        database = FirebaseDatabase.getInstance();
        mref = database.getReferenceFromUrl("https://onyx-silo-199918.firebaseio.com/").child("clientes");
    }

    //RETORNA REFERENCIA DA BASE DE DADOS/FIREBASE PARA AÇAO DE EVENTOS
    public DatabaseReference getMref() {
        return mref;
    }

    //PEGA O ARRAY DE LOCALIZAÇAO E MARCA A ROTA DOS PONTOS NO MAPA
    public void getArrayLoc(String userId, final GoogleMap map){
        final List<DatabaseReference> locList = new ArrayList<>();
        final DatabaseReference data = mref.child(userId).child("localizacao");
        final List<LatLng> loc = new ArrayList<>();

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Adicionar caminho percorrido no mapa
                PolylineOptions polyOpt = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        double lat = snapshot.child("latitude").getValue(double.class);
                        double lon = snapshot.child("longitude").getValue(double.class);

                        LatLng ponto = new LatLng(lat, lon);

                        if(ponto != null) {
                            map.clear();
                            loc.add(ponto);
                            polyOpt.add(ponto);

                            if(loc.size() > 0) {
                                //Define pontos da linha a ser tracejada
                                map.addPolyline(polyOpt);
                                //Marcador na posiçao original, de cor verde. Sempre sobrescreve ao pegar novos pontos.
                                map.addMarker(new MarkerOptions().position(polyOpt.getPoints().get(0))
                                        .icon(BitmapDescriptorFactory
                                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                                        .setTitle("Ponto de partida");


                                //Adiciona marcador na ultima posiçao traçada, de cor vermelha
                                if(loc.size() >= 1){
                                    map.addMarker(new MarkerOptions().position(polyOpt.getPoints().get(loc.size()-1))
                                            .icon(BitmapDescriptorFactory
                                                    .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                                            .setTitle("Localização atual");;
                                }

                                //Da zoom de fator 16.0 na camera do mapa
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(polyOpt.getPoints().get(loc.size()-1), 16.0f));
                            }
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //SALVA ARRAY DE LOCALIZAÇOES NO FIREBASE
    public void saveNewLoc(String userId, final LatLng loc){
        //--------
        final DatabaseReference data = mref.child(userId).child("localizacao");

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = (int) dataSnapshot.getChildrenCount();

                data.child(String.valueOf(size+1)).child("latitude").setValue(loc.latitude);
                data.child(String.valueOf(size+1)).child("longitude").setValue(loc.longitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
