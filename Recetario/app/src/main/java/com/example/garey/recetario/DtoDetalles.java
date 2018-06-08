package com.example.garey.recetario;

public class DtoDetalles {
    //u.Nom, u.Usuario,
    // @Mejor as Mejor, @total as Total,@Tipo as Tipo,@valor as Valor
    private String Nom,Usuario,Mejor,Total,Tipo,Valor;

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

    public String getMejor() {
        return Mejor;
    }

    public void setMejor(String mejor) {
        Mejor = mejor;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}
