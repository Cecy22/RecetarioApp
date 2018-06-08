package com.example.garey.recetario;

import java.util.Arrays;

public class DtoReceta {
    //[{"idReceta":"1","0":"1","Nombre":"pizza","1":"pizza",
    // "Tipo":"chatarra","2":"chatarra","Valoracion":"3","3":"3","imagen":"sa","4":"sa","IdUsuario":"1","5":"1"},
    private int idReceta;
    private String Nombre;
    private String Tipo;
    private String Valoracion;
    private int imagen;
    private String RutaImagen;
    private int IdUsuario;

    public DtoReceta() {
    }

    public DtoReceta(String nombre) {
        Nombre = nombre;
    }

    public DtoReceta(String nombre, int imagen) {
        Nombre = nombre;
        this.imagen = imagen;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getValoracion() {
        return Valoracion;
    }

    public void setValoracion(String valoracion) {
        Valoracion = valoracion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getPathImg() {
        return RutaImagen;
    }

    public void setPathImg(String pathImg) {
        this.RutaImagen = pathImg;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
    public boolean checkImage(){
        if(getPathImg().equals("none")) {
            setImagen(R.drawable.ic_receta);
            return true;
        }else
            return false;
    }
}