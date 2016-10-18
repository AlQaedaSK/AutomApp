package com.example.helder.automapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> myAdapter;

    private TextView textView;

    private TextView btnSair;

    private ListView lvSistemas;
    private String[] sistemas_supervisionados = {"CLP1", "CLP2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSair = (TextView) findViewById(R.id.btnSair);
        textView = (TextView) findViewById(R.id.textView);
        lvSistemas = (ListView) findViewById(R.id.lvSistemas);

        Bundle bundle = getIntent().getExtras();
        String user;
        if(bundle.containsKey("USUARIO")){
            user = bundle.getString("USUARIO");
            textView.setText("Welcome "+user);
        }

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //// TODO: 28/09/2016 leitura do banco dos sistemas a serem monitorados
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas_supervisionados);
        lvSistemas.setAdapter(myAdapter);
        lvSistemas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sistema_selecionado = (String)parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(),sistema_selecionado+" foi selecionado",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, SuperviseActivity.class);
                i.putExtra("SISTEMA", sistema_selecionado);
                startActivity(i);
            }
        });
    }
}
