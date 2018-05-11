package com.example.yurig.aparencia;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import static com.example.yurig.aparencia.R.id.imagem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private ImageView imageView;
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

        listView = (ListView) findViewById(R.id.list_view);
        imageView = (ImageView) findViewById(imagem);
        arrayList = new ArrayList<String>();
        arrayList.add("Minha Conta");
        arrayList.add("Ajuda");
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list,R.id.item, arrayList);

        listView.setAdapter(arrayAdapter);
        listView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(MainActivity.this, MinhaContaActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, AjudaActivity.class));
                        break;
                }
            }
        });

        //CustomListAdapter adapter = new CustomListAdapter(this, arrayList, R.layout.list);
        /*
        Integer[] img = {R.mipmap.meus_dados, R.mipmap.ajuda};
        Map<String, Integer> map = new HashMap<>();
        map.put("Minha Conta", img[0]);
        map.put("Ajuda", img[1]);
        listView = (ListView) findViewById(R.id.list_view);
        imageView = (ImageView) findViewById(imagem);
        arrayList = new ArrayList<Map<String,Integer>>();
        arrayList.add(map);
        arrayAdapter = new SimpleAdapter(MainActivity.this, arrayList, R.layout.list, new String[]{"Minha Conta","Ajuda"}, new int[] {R.id.imagem, R.id.item});

        listView.setAdapter(arrayAdapter);
        listView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
         */

        //ADD VIEW PAGER
    }
}
