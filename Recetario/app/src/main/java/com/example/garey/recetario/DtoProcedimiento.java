package com.example.garey.recetario;

public class DtoProcedimiento {
    /*
    p.IdP, p.Descripcion, p.Nom
     */
    private int IdP;
    private String Descripcion;

    public DtoProcedimiento() {
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


}
