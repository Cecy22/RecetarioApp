package com.example.garey.recetario;

public class DtoUsuario {
    //[{"IdU":"1","0":"1","Nom":"yo","1":"yo","
    // Usuario":"yo","2":"yo","FNac":"2012-12-12","3":"2012-12-12","Cont":"1","4":"1","Imagen":"bla","5":"bla"}]
    private int IdU;
    private String Nom;
    private String Usuario;
    private String FNac;
    private String Cont;
    private String Imagen;

    public int getIdU() {
        return IdU;
    }

    public void setIdU(int idU) {
        IdU = idU;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getFNac() {
        return FNac;
    }

    public void setFNac(String FNac) {
        this.FNac = FNac;
    }

    public String getCont() {
        return Cont;
    }

    public void setCont(String cont) {
        Cont = cont;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
