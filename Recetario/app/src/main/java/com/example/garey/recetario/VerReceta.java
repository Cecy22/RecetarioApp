package com.example.garey.recetario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerReceta extends AppCompatActivity {

    @BindView(R.id.rbPunt)
    RatingBar rb;

    @BindView(R.id.txtReceta)
    TextView Receta;

    @BindView(R.id.txtTipo)
    TextView Tipo;

    @BindView(R.id.ltsIngrediente)
    ListView Ingredientes;

    @BindView(R.id.imgReceta)
    CircleImageView Imagen;

    @BindView(R.id.btnVer)
    Button Ver;

    int idr, idI, idU;
    String ruta;
    ProgressDialog dialog;
    List<DtoIngrediente> lista;
    ArrayList<String> Ingre;
    ArrayAdapter<String> AdapterI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_receta);
        ButterKnife.bind(this);
        Intent i = getIntent();

        rb.setRating(Float.parseFloat(i.getStringExtra("Valor")));
        Receta.setText(i.getStringExtra("Nombre"));
        Tipo.setText(i.getStringExtra("Tipo"));
        idr = i.getIntExtra("IdR", -1);
        ruta = i.getStringExtra("Imagen");
        idI = i.getIntExtra("idImag", -1);
        idU = i.getIntExtra("idUsu", -1);

        //Lista de ingredientes.
        Ingre = new ArrayList<String>();
        AdapterI = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ingre);
        Ingredientes.setAdapter(AdapterI);
        VerListaIngredientes();
        llenarVista();

    }

    public static void createInstance(Activity activity, DtoReceta receta) {
        Intent intent = getLaunchIntent(activity, receta);
        activity.startActivity(intent);

    }

    private static Intent getLaunchIntent(Activity activity, DtoReceta receta) {
        //this.dtoReceta = (DtoReceta) receta;
        Intent intent = new Intent(activity, VerReceta.class);
        intent.putExtra("IdR", receta.getIdReceta());
        intent.putExtra("Valor", receta.getValoracion());
        intent.putExtra("Imagen", receta.getPathImg());
        intent.putExtra("Tipo", receta.getTipo());
        intent.putExtra("Nombre", receta.getNombre());
        intent.putExtra("idImg", receta.getImagen());
        intent.putExtra("idUsu", receta.getIdUsuario());
        return intent;
    }


    public void llenarLista() {
        //Toast.makeText(this, lista.get(0).getNom().toString(), Toast.LENGTH_SHORT).show();
        int t = lista.size();
        //Toast.makeText(this, Integer.toString(t), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < t; i++) {
            Ingre.add(lista.get(i).getNom().toString());
        }
        AdapterI.notifyDataSetChanged();
    }

    public void Ver(View v) {
        Intent i = new Intent(this, Procedimiento.class);
        i.putExtra("id", idr);
        i.putExtra("nom", Receta.getText().toString());
        startActivity(i);
    }

    public void VerListaIngredientes() {
        dialog = ProgressDialog.show(this, "Cargando Recetas", "Espera...", true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<DtoIngrediente>> requestDatas = request.MostIngre(Integer.toString(idr));
        requestDatas.enqueue(new Callback<List<DtoIngrediente>>() {
            @Override
            public void onResponse(Call<List<DtoIngrediente>> call, Response<List<DtoIngrediente>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(VerReceta.this, "Request wrong!", Toast.LENGTH_LONG)
                            .show();
                }
                lista = response.body();
                dialog.dismiss();
                llenarLista();
                //Toast.makeText(VerReceta.this, "Cronsulta", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<DtoIngrediente>> call, Throwable t) {
                lista = null;
                Toast.makeText(VerReceta.this, "No funciona el Servidor", Toast.LENGTH_LONG)
                        .show();
                t.printStackTrace();
            }
        });
    }

    public void llenarVista() {

        if (checkImage())
            Imagen.setImageDrawable(ContextCompat.getDrawable
                    (this, idI));
        else
            Picasso.with(this).load(ruta).into(Imagen);

    }

    public boolean checkImage() {
        if (ruta.equals("none")) {
            idI = (R.drawable.ic_receta);
            return true;
        } else
            return false;
    }
}
