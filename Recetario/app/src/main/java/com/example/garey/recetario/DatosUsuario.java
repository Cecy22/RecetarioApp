package com.example.garey.recetario;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosUsuario extends AppCompatActivity {
    ProgressDialog dialog;
    DtoDetalles dtoDetalles;
    int usu;
    TextView txtNom, txtUsuario, txtMejor, txtTipo, txtNumero;
    RatingBar rbPunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        Intent i = getIntent();
        usu = Integer.parseInt(i.getStringExtra("id"));

        txtMejor = (TextView) findViewById(R.id.txtMejor);
        txtNom = (TextView) findViewById(R.id.txtNom);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtTipo = (TextView) findViewById(R.id.txtTipo);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        rbPunt = (RatingBar) findViewById(R.id.rbPunt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog = ProgressDialog.show(this, "", "Cargando...", true);
        Log.e("PRESIONO", "SE PRESIONO");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<DtoDetalles>> requestDatas = request.BuscarUsuario(usu);
        requestDatas.enqueue(new Callback<List<DtoDetalles>>() {
            @Override
            public void onResponse(Call<List<DtoDetalles>> call, Response<List<DtoDetalles>> response) {
                List<DtoDetalles> usaurio = response.body();
                if (usaurio != null) {
                    dtoDetalles = usaurio.get(0);
                    //Toast.makeText(DatosUsuario.this, dtoDetalles.getUsuario(), Toast.LENGTH_SHORT).show();
                    txtMejor.setText(dtoDetalles.getMejor());
                    txtNom.setText(dtoDetalles.getNom());
                    txtNumero.setText(dtoDetalles.getTotal());
                    txtTipo.setText(dtoDetalles.getTipo());
                    txtUsuario.setText(dtoDetalles.getUsuario());
                    rbPunt.setRating(Float.parseFloat(dtoDetalles.getValor()));
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<DtoDetalles>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
