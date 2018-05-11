package com.example.yurig.aparencia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContatosActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPageAdapter;
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private FloatingActionButton adcContatoBtn;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_contatos);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        finish();
                        startActivity(new Intent(ContatosActivity.this, MainActivity.class));
                        return true;
                    case R.id.navigation_mapa:
                        finish();
                        startActivity(new Intent(ContatosActivity.this, MapsActivity.class));
                        return true;
                    case R.id.navigation_contatos:

                        return true;
                }
                return false;
            }
        });

        //******************************************************************************************

        listView = (ListView) findViewById(R.id.contatos_list);
        adcContatoBtn = (FloatingActionButton) findViewById(R.id.adc_contato_btn);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list,R.id.item, arrayList);
        listView.setAdapter(arrayAdapter);
        builder = new AlertDialog.Builder(getApplicationContext());

        adcContatoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Adicionar Contato");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //PAREI AQUI
                    }
                });
                AlertDialog dialog = builder.create();
            }
        });

    }
}
