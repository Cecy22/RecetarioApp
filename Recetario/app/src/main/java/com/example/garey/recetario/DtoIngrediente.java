package com.example.garey.recetario;

public class DtoIngrediente {
    /*
     i.IdI, i.Nom, a.Cant, a.Unidad
     */
    private int IdI;
    private String Nom;
    private String Cant;
    private String Unidad;

    public DtoIngrediente() {
    }

    public DtoIngrediente(String nom) {
        Nom = nom;
    }

    public int getIdI() {
        return IdI;
    }

    public void setIdI(int idI) {
        IdI = idI;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCant() {
        return Cant;
    }

    public void setCant(String cant) {
        Cant = cant;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String unidad) {
        Unidad = unidad;
    }
}
