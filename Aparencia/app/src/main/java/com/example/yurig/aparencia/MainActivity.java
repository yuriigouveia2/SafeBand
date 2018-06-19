package com.example.yurig.aparencia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static com.example.yurig.aparencia.R.id.imagem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private Button button;
    private ImageView imageView;
    private TextView textView;
    private FirebaseDatabase database;
    private DatabaseReference mref;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_mapa:
                    startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    return true;
                case R.id.navigation_contatos:
                    startActivity(new Intent(MainActivity.this, ContatosActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        arrayList = new ArrayList<String>();
        arrayList.add("Minha Conta");
        arrayList.add("Ajuda");
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list,R.id.item, arrayList);

        //ADD VIEW PAGER
        imageView = (ImageView) findViewById(R.id.img_status);
        textView = (TextView) findViewById(R.id.nome);
        button = (Button) findViewById(R.id.muda_status);

        database = FirebaseDatabase.getInstance();
        mref = database.getReferenceFromUrl("https://onyx-silo-199918.firebaseio.com/").child("clientes").child("cliente1");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                textView.setText("Usuário: " + dataSnapshot.child("nome").getValue().toString());

                if(!dataSnapshot.child("flagSafe").getValue(boolean.class)){
                    imageView.setImageResource(R.mipmap.seguro);
                    imageView.setColorFilter(Color.GREEN);
                }else {
                    imageView.setImageResource(R.mipmap.perigo);
                    imageView.setColorFilter(Color.RED);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mref.child("flagSafe").setValue(false);
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
