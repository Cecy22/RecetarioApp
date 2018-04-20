
package com.recetario.modelo;


public class DtoReceta {
    private int idR;
    private String Nom;
    private String Tipo;
    private String Valor;
    private String img;
    private int IdU;

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdU() {
        return IdU;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }
    
}
