package com.example.garey.recetario;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevaR extends AppCompatActivity {

    ProgressDialog dialog;
    boolean EsR, EsI, EsP;
    private ArrayList<String> Ingrediente;
    private ArrayAdapter<String> adaptadorI;

    private ArrayList<String> Procedimientos;
    private ArrayAdapter<String> adaptadorP;

    private ListView lvIngrediente, lvProcedimiento;

    DtoResult dtoResult;
    Spinner spCant, spTipo;
    private EditText txtProcedimiento, txtIngrediente, txtCant, txtNom;

    int id, idr;
    ImageButton guarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_r);

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("id"));

        spCant = (Spinner) findViewById(R.id.spCant);
        spTipo = (Spinner) findViewById(R.id.SpTipo);
        lvIngrediente = (ListView) findViewById(R.id.ltsIngrediente);
        lvProcedimiento = (ListView) findViewById(R.id.ltsProcedimiento);
        txtProcedimiento = (EditText) findViewById(R.id.txtProcedimiento);
        txtCant = (EditText) findViewById(R.id.txtCant);
        txtIngrediente = (EditText) findViewById(R.id.txtIngrediente);
        txtNom = (EditText) findViewById(R.id.txtReceta);
        guarda = (ImageButton) findViewById(R.id.btnGuardar);

        Ingrediente = new ArrayList<String>();
        adaptadorI = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ingrediente);
        lvIngrediente.setAdapter(adaptadorI);

        lvIngrediente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(NuevaR.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este Ingrediente ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Ingrediente.remove(posicion);
                        adaptadorI.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });


        Procedimientos = new ArrayList<String>();
        adaptadorP = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Procedimientos);
        lvProcedimiento.setAdapter(adaptadorP);

        lvProcedimiento.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(NuevaR.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este teléfono ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Procedimientos.remove(posicion);
                        adaptadorP.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });

        String[] Unidades = {"Kg", "g", "L", "ml", "pz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Unidades);
        spCant.setAdapter(adapter);

        String[] Tipos = {"Acompañamiento", "Almuerzo", "Bocadillos", "Cena", "Desayuno",
                "Ensaladas", "Panes", "Panqueques", "Platillos Principales", "Postres", "Salsas y Aderezos",
                "Sopas y Guisados"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Tipos);
        spTipo.setAdapter(adapter2);
    }

    public void AddReceta(View v) {
        Log.e("PRESIONO", "SE PRESIONO");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<DtoResult> requestData = request.addReceta(txtNom.getText().toString(), spTipo.getSelectedItem().toString(), 0, 1, id);
        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if (!response.isSuccessful())
                    Toast.makeText(NuevaR.this, "Request is Wrong", Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
                if (result.getEstado() == "no")
                    EsR = false;
                else
                    EsR = true;
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(NuevaR.this, "Server not found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddIngrediente(String Ingred) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<DtoResult> requestData = request.addIngrediente(Ingred);
        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if (!response.isSuccessful())
                    Toast.makeText(NuevaR.this, "Request is Wrong", Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(NuevaR.this, "Server not found", Toast.LENGTH_LONG).show();
                Log.e("El error es:   ", t.getMessage());
            }
        });
    }


    public void AddProcedimiento(String pro) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<DtoResult> requestData = request.addProcedimiento(pro);
        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if (!response.isSuccessful())
                    Toast.makeText(NuevaR.this, "Request is Wrong", Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(NuevaR.this, "Server not found", Toast.LENGTH_LONG).show();
                Log.e("El error es:   ", t.getMessage());
            }
        });
    }

    private void closeActivity() {
        this.finish();
    }


    public void guardar(View v) {
        dialog = ProgressDialog.show(this, "", "Guardando.....", true);
        AddReceta(v);

        int ti, tp;
        ti = Ingrediente.size();
        for (String item : Ingrediente) {
            if (item != "" && item != null)
                AddIngrediente(item);
        }

        tp = Procedimientos.size();
        for (String item : Procedimientos) {
            if (item != "" && item != null)
                AddProcedimiento(item);
            else
                Log.e("PRESIONO", item);
        }
        dialog.dismiss();
        closeActivity();
    }

    public void agregarIngrediente(View v) {
        Ingrediente.add(txtCant.getText().toString() + " " + spCant.getSelectedItem().toString() + ". " + txtIngrediente.getText().toString());
        adaptadorI.notifyDataSetChanged();
        txtIngrediente.setText("");
        txtCant.setText("");
    }

    public void agregarProcedimiento(View v) {
        Procedimientos.add(txtProcedimiento.getText().toString());
        adaptadorI.notifyDataSetChanged();
        txtProcedimiento.setText("");
    }
}
