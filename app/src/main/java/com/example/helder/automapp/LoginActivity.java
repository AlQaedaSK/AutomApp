package com.example.helder.automapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private Button btnEntrar;
    private TextView btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                //TODO verificar senha
                i.putExtra("USUARIO", etUsuario.getText().toString());
                startActivity(i);
            }

        });
        btnCadastrar = (TextView) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }

        });



    }
}
