package com.example.garey.recetario;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Procedimiento extends AppCompatActivity {
    @BindView(R.id.txtNom)
    TextView Titulo;

    @BindView(R.id.txtPaso)
    TextView Paso;

    @BindView(R.id.txtProc)
    TextView Procedimiento;

    int idr, cont = 0, t, idU;
    List<DtoProcedimiento> lista;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimiento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideVirtualButtons();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Action_Siguiente:
                        next();
                        //Toast.makeText(Procedimiento.this, "siquiente", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Action_atras:
                        anterior();
                        //Toast.makeText(Procedimiento.this, "Atras", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        Intent i = getIntent();
        Titulo.setText(i.getStringExtra("nom"));
        idr = i.getIntExtra("id", -1);
        idU = i.getIntExtra("usu", -1);
        VerListaProcesos();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // In KITKAT (4.4) and next releases, hide the virtual buttons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                hideVirtualButtons();
            }
        }
    }

    @TargetApi(19)
    private void hideVirtualButtons() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void next() {
        cont++;
        if (cont < t) {
            Paso.setText("Paso # " + (cont + 1));
            Procedimiento.setText(lista.get(cont).getDescripcion());
        } else {
            Intent i = new Intent(this, Calificacion.class);
            cont--;
            i.putExtra("id", idr);
            i.putExtra("nom", Titulo.getText().toString());
            i.putExtra("usu", idU);
            startActivity(i);
        }
    }

    public void anterior() {
        cont--;
        if (cont >= 0) {
            Paso.setText("Paso # " + (cont + 1));
            Procedimiento.setText(lista.get(cont).getDescripcion());
        } else {
            onBackPressed();
            //Toast.makeText(this, "Este es el Primer paso", Toast.LENGTH_SHORT).show();
            cont++;
        }
    }

    public void VerListaProcesos() {
        dialog = ProgressDialog.show(this, "Cargando Procedimiento", "Espera...", true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<DtoProcedimiento>> requestDatas = request.MostProc(Integer.toString(idr));
        requestDatas.enqueue(new Callback<List<DtoProcedimiento>>() {
            @Override
            public void onResponse(Call<List<DtoProcedimiento>> call, Response<List<DtoProcedimiento>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Procedimiento.this, "Request wrong!", Toast.LENGTH_LONG)
                            .show();
                }
                lista = response.body();
                t = lista.size();
                dialog.dismiss();
                Paso.setText("Paso # 1");
                Procedimiento.setText(lista.get(0).getDescripcion());
                //Toast.makeText(Procedimiento.this, Integer.toString(t), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<DtoProcedimiento>> call, Throwable t) {
                lista = null;
                Toast.makeText(Procedimiento.this, "No funciona el Servidor", Toast.LENGTH_LONG)
                        .show();
                t.printStackTrace();
            }
        });
    }
}
