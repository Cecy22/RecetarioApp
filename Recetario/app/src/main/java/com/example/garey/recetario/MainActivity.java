package com.example.garey.recetario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button in;
    EditText txtUsu, txtContra;
    String usu, pass;
    ProgressDialog dialog;
    List<DatosUsuario> list;
    DtoUsuario dtoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = (Button) findViewById(R.id.btnLogin);
        txtContra = (EditText) findViewById(R.id.txtContra);
        txtUsu = (EditText) findViewById(R.id.txtUsu);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //conRetrofit(null);
    }

    public void conRetrofit(View v) {
        //Toast.makeText(this, txtUsu.getText().toString()+" "+ txtContra.getText().toString(), Toast.LENGTH_SHORT).show();

        dialog = ProgressDialog.show(this, "", "Cargando...", true);
        Log.e("PRESIONO", "SE PRESIONO");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<DtoUsuario>> requestDatas = request.validaLogin(txtUsu.getText().toString(), txtContra.getText().toString());
        requestDatas.enqueue(new Callback<List<DtoUsuario>>() {
            @Override
            public void onResponse(Call<List<DtoUsuario>> call, Response<List<DtoUsuario>> response) {
                List<DtoUsuario> usaurio = response.body();
                if (usaurio != null) {
                    dtoUsuario = usaurio.get(0);
                    //String id = String.valueOf(dtoUsuario.getIdU());
                    //Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                    llameVentana();

                }else {
                dialog.dismiss();
                    Toast.makeText(MainActivity.this, "No se encontro Usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DtoUsuario>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void llameVentana() {
        Intent i = new Intent(MainActivity.this, Inicio.class);
        i.putExtra("Usu", String.valueOf(dtoUsuario.getIdU()));
        startActivity(i);
    }

    public void registra(View v) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

}
