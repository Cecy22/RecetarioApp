package com.example.garey.recetario;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity {
 EditText txtNom,txtUsu,txtFecha,txtpass;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNom=(EditText) findViewById(R.id.txtNom);
        txtUsu=(EditText) findViewById(R.id.txtUsu);
        txtFecha=(EditText) findViewById(R.id.txtFnac);
        txtpass=(EditText) findViewById(R.id.txtContra);
    }

    public void AddUsuario(View v) {

        dialog = ProgressDialog.show(this,"","Guardando.....",true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<DtoResult> requestData = request.addUsuario(txtNom.getText().toString(),txtUsu.getText().toString(),
                txtFecha.getText().toString(),txtpass.getText().toString(),"https://thumbs.dreamstime.com/b/icono-del-azul-del-usuario-43464682.jpg");

        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Registro.this,"Request is Wrong",Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
                dialog.dismiss();
                String cadResult = "";
                if(result.getEstado()=="no")
                    cadResult = "No se registro Usuario";
                else
                    cadResult = "Usuario Registrado";
                Toast.makeText(Registro.this,cadResult,Toast.LENGTH_LONG).show();

                closeActivity();
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(Registro.this,"Server not found",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void closeActivity() {
        this.finish();
    }

}
