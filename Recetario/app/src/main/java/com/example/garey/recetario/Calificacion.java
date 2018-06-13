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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Calificacion extends AppCompatActivity {

    int idr, idU;

    ProgressDialog dialog;

    @BindView(R.id.txtNombre)
    TextView Titulo;

    @BindView(R.id.rbPunt)
    RatingBar valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
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
                        Valorar();
                        break;
                    case R.id.Action_atras:
                        onBackPressed();
                        break;
                }
                return true;
            }
        });
        Intent i = getIntent();
        Titulo.setText(i.getStringExtra("nom"));
        idr = i.getIntExtra("id", -1);
        idU = i.getIntExtra("usu", -1);
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

    public void Valorar() {
        dialog = ProgressDialog.show(this, "", "Guardando.....", true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<DtoResult> requestData = request.Valorar(idr, valor.getRating());

        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if (!response.isSuccessful())
                    Toast.makeText(Calificacion.this, "Request is Wrong", Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
                dialog.dismiss();
                String cadResult = "";
                if (result.getEstado() == "no")
                    cadResult = "No se pudo guardar su opinion";
                else
                    cadResult = "Se guardo su opinion";
                Toast.makeText(Calificacion.this, cadResult, Toast.LENGTH_LONG).show();
                closeActivity();
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(Calificacion.this, "Server not found", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void closeActivity() {
        this.finish();
        Intent i = new Intent(this, Inicio.class);
        i.putExtra("Usu", idU);
        startActivity(i);
    }
}
