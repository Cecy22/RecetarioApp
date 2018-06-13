package com.example.garey.recetario;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity {
    EditText txtNom, txtUsu, txtFecha, txtpass;
    ImageView Imagen;
    ProgressDialog dialog;

    private static final int CAMERA_CODE = 801;
    public static final int REQUEST_PERMISSION_CAMERA = 900;
    public static final int REQUEST_PERMISSION_NET = 901;
    Uri fileUri;

    private static final String IMAGE_DIRECTORY_NAME = "babynometro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNom = (EditText) findViewById(R.id.txtNom);
        txtUsu = (EditText) findViewById(R.id.txtUsu);
        txtFecha = (EditText) findViewById(R.id.txtFnac);
        txtpass = (EditText) findViewById(R.id.txtContra);
        Imagen = (ImageView) findViewById(R.id.imageView2);
    }

    public void AddUsuario(View v) {
        if (!txtNom.getText().toString().equals("") && !txtpass.getText().toString().equals("")
                && !txtFecha.getText().toString().equals("") && !txtUsu.getText().toString().equals("")) {

            dialog = ProgressDialog.show(this, "", "Guardando.....", true);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
            Call<DtoResult> requestData = request.addUsuario(txtNom.getText().toString(), txtUsu.getText().toString(),
                    txtFecha.getText().toString(), txtpass.getText().toString(), "https://thumbs.dreamstime.com/b/icono-del-azul-del-usuario-43464682.jpg");

            requestData.enqueue(new Callback<DtoResult>() {
                @Override
                public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(Registro.this, "Request is Wrong", Toast.LENGTH_LONG).show();
                    DtoResult result = response.body();
                    dialog.dismiss();
                    String cadResult = "";
                    if (result.getEstado() == "no")
                        cadResult = "No se registro Usuario";
                    else
                        cadResult = "Usuario Registrado";
                    Toast.makeText(Registro.this, cadResult, Toast.LENGTH_LONG).show();

                    closeActivity();
                }

                @Override
                public void onFailure(Call<DtoResult> call, Throwable t) {
                    Toast.makeText(Registro.this, "Server not found", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "No puedes dejar los campos vacios.", Toast.LENGTH_SHORT).show();
        }
    }

    private void closeActivity() {
        this.finish();
    }

    public void tomarFoto(View v) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA, Manifest.permission.MEDIA_CONTENT_CONTROL},
                    REQUEST_PERMISSION_CAMERA);
            return;
        }
        Intent photoIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri();
        photoIntent.putExtra("camera", true);
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.putExtra("camera", false);
        List<Intent> intents = new ArrayList<>();

        intents.add(pickIntent);
        intents.add(photoIntent);

        Intent chooserIntent = Intent.createChooser(intents.get(0),
                getString(R.string.title_chooser));
        intents.remove(0);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[intents.size()]));
        startActivityForResult(chooserIntent, CAMERA_CODE);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        Log.e("DIRECTORY PHOTOS", mediaStorageDir.getAbsolutePath());
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdir()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Algo fallo "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {

            boolean isCamara = true;
            if (data != null)
                isCamara = data.getBooleanExtra("camera", false);
            Bitmap bitmap;
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (isCamara) {
                bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                        options);
                Imagen.setImageBitmap(bitmap);
            } else {
                fileUri = data.getData();
                Imagen.setImageURI(fileUri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
