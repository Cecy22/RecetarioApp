package com.recetario.modelo;

public class DtoReceta2 {

    //datos de aux
    private int id;
    private String Cant;
    private String Unidad;
    //datos de receta
    private int idR;
    private String NomR;
    private String Tipo;
    private String Valor;
    private String img;
    private int IdU;
    //datos de procedimiento
    private int idP;
    private String Des;
    private String NomP;
    //datos de ingrediente
    private int IdI;
    private String NomI;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCant() {
        return Cant;
    }

    public void setCant(String Cant) {
        this.Cant = Cant;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return NomR;
    }

    public void setNomR(String NomR) {
        this.NomR = NomR;
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

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String Des) {
        this.Des = Des;
    }

    public String getNomP() {
        return NomP;
    }

    public void setNomP(String NomP) {
        this.NomP = NomP;
    }

    public int getIdI() {
        return IdI;
    }

    public void setIdI(int IdI) {
        this.IdI = IdI;
    }

    public String getNomI() {
        return NomI;
    }

    public void setNomI(String NomI) {
        this.NomI = NomI;
    }

}
