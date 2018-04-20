package com.recetario.modelo;

public class DtoUsuario {

    private int IdU;
    private String Nom;
    private String Usu;
    private String Fnac;
    private String Cont;
    private String Img;

    public int getIdU() {
        return IdU;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getUsu() {
        return Usu;
    }

    public void setUsu(String Usu) {
        this.Usu = Usu;
    }

    public String getFnac() {
        return Fnac;
    }

    public void setFnac(String Fnac) {
        this.Fnac = Fnac;
    }

    public String getCont() {
        return Cont;
    }

    public void setCont(String Cont) {
        this.Cont = Cont;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
}
