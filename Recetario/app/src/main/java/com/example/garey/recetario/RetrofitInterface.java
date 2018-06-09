package com.example.garey.recetario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    //public static final String url= "http://10.42.0.126/ServicioWebRecetario/";//tec
    public static final String url = "http://192.168.1.86/ServicioWebRecetario/";//Casa
    //public static final String url = "http://192.168.43.245/ServicioWebRecetario/";//erico


    @POST("ValidaUsuario.php")
    Call<List<DtoUsuario>> validaLogin(@Query("usu") String usu, @Query("pass") String pass);

    @POST("BuscarUsuario.php")
    Call<List<DtoDetalles>> BuscarUsuario(@Query("Id") int id);

    @GET("MostrarReceta.php")
    Call<List<DtoReceta>> MostRecetas(@Query("nom") String nom);

    @GET("MostrarProcedimiento.php")
    Call<List<DtoProcedimiento>> MostProc(@Query("idr") String idr);

    @GET("MostrarIngrediente.php")
    Call<List<DtoIngrediente>> MostIngre(@Query("idr") String idr);

    @POST("InsertarUsuario.php")
    Call<DtoResult> addUsuario(@Query("nom") String nom, @Query("usu") String usu,
                               @Query("fnac") String fnac, @Query("pass") String pass,
                               @Query("img") String img);

    @POST("InsertarReceta.php")
    Call<DtoResult> addReceta(@Query("nom") String nom, @Query("Tipo") String Tipo,
                              @Query("Valor") int Valor, @Query("img") int img,
                              @Query("IdU") int IdU);

    @POST("InsertarIngrediente.php")
    Call<DtoResult> addIngrediente(@Query("nom") String nom);

    @POST("InsertarProcedimiento.php")
    Call<DtoResult> addProcedimiento( @Query("des") String des);
}
