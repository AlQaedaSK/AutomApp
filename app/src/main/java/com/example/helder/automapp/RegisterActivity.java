package com.example.helder.automapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etEmail;
    private EditText etTelefone;
    private EditText etPass1;
    private EditText etPass2;

    private Button btnConfirmar;
    private Button btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etPass1 = (EditText) findViewById(R.id.etPass1);
        etPass2 = (EditText) findViewById(R.id.etPass2);

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO verificar campos e armazenar usuário

                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                i.putExtra("USUARIO", etUsuario.getText().toString());
                startActivity(i);
                finish();

            }

        });
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

