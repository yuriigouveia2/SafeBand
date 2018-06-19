package com.example.yurig.aparencia;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yurig.aparencia.Firebase.FirebaseConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContatosActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPageAdapter;
    private Intent intent;
    private ListView listView;
    private List<String> lista;
    private FirebaseDatabase database;
    private DatabaseReference mref;
    private String nomeAmigo;
    private ArrayAdapter<String> arrayAdapter;
    private NotificationCompat.Builder nBuilder;
    NotificationManager notification;
    private TaskStackBuilder stackBuilder;
    private FloatingActionButton adcContatoBtn;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        finish();
                        Intent intent = new Intent(ContatosActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_contatos:

                        return true;
                }
                return false;
            }
        });

        //******************************************************************************************

        database = FirebaseDatabase.getInstance();
        mref = new FirebaseConfig().getMref();
        listView = (ListView) findViewById(R.id.contatos_list);
        lista = new ArrayList<>();

        mref.child("cliente1").child("friendlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnap) {
                for (DataSnapshot snapshot : dataSnap.getChildren()) {
                    nomeAmigo = snapshot.getKey();
                    lista.add(nomeAmigo);

                    mref.child(nomeAmigo).addValueEventListener(new ValueEventListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            arrayAdapter = new ArrayAdapter<>(ContatosActivity.this, R.layout.list,
                                    R.id.item, lista);
                            listView.setAdapter(arrayAdapter);

                            if (dataSnapshot.child("flagSafe").getValue(boolean.class)) {
                                nBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                                        .setSmallIcon(R.mipmap.perigo)
                                        .setColor(Color.rgb(220, 30, 30))
                                        .setContentTitle("SafeBand")
                                        .setContentText(nomeAmigo + " está em perigo")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH);

                                notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                notification.notify(001, nBuilder.build());


                            }
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    intent = new Intent(ContatosActivity.this, MapsActivity.class);
                                    intent.putExtra("Nome", lista.get(i));
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
