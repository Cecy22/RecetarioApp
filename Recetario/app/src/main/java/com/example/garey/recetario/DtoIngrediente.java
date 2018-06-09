package com.example.garey.recetario;

public class DtoIngrediente {
    /*
     i.IdI, i.Nom, a.Cant, a.Unidad
     */
    private int IdI;
    private String Nom;

    public DtoIngrediente() {
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


}
