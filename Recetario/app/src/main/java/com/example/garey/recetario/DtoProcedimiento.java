package com.example.garey.recetario;

public class DtoProcedimiento {
    /*
    p.IdP, p.Descripcion, p.Nom
     */
    private int IdP;
    private String Descripcion;
    private String Nom;

    public DtoProcedimiento() {
    }

    public DtoProcedimiento(String nom) {
        Nom = nom;
    }

    public DtoProcedimiento(int idP) {
        IdP = idP;
    }

    public int getIdP() {
        return IdP;
    }

    public void setIdP(int idP) {
        IdP = idP;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
