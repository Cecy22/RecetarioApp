package com.example.garey.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in= (Button) findViewById(R.id.btnLogin);
    }

    public void iniciar(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
    public void registra(View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}
