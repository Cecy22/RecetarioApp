package com.example.garey.recetario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rv)
    RecyclerView rv;

    private LinearLayoutManager llm;
    List<DtoReceta> lista;
    public ProgressDialog dialog;

    public Toolbar toolbar;
    String usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent i = getIntent();
        usu = i.getStringExtra("Usu");
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navi);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_Datos) {
            Intent i = new Intent(this, DatosUsuario.class);
            i.putExtra("id", usu);
            startActivity(i);
        } else if (id == R.id.action_Inicio) {
            Intent i = new Intent(this, Inicio.class);
            startActivity(i);
        } else if (id == R.id.action_Nuevo) {
            Intent i = new Intent(this, NuevaR.class);
            i.putExtra("id", usu);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void llenarRecycler() {
        if (lista != null) {
            Adaptador adapter = new Adaptador(this, lista);
            llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog = ProgressDialog.show(this, "Cargando Recetas", "Espera...", true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<DtoReceta>> requestDatas = request.MostRecetas("");
        requestDatas.enqueue(new Callback<List<DtoReceta>>() {
            @Override
            public void onResponse(Call<List<DtoReceta>> call, Response<List<DtoReceta>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Inicio.this, "Request wrong!", Toast.LENGTH_LONG)
                            .show();
                }
                lista = response.body();
                dialog.dismiss();
                llenarRecycler();
            }

            @Override
            public void onFailure(Call<List<DtoReceta>> call, Throwable t) {
                lista = null;
                Toast.makeText(Inicio.this, "No funciona el Servidor", Toast.LENGTH_LONG)
                        .show();
                t.printStackTrace();
            }
        });
    }
}


