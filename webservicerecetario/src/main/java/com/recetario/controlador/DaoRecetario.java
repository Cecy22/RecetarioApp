package com.recetario.controlador;

import com.recetario.mysql.Conexion;
import com.recetario.modelo.DtoReceta2;
import com.recetario.modelo.DtoIngrediente;
import com.recetario.modelo.DtoProcedimiento;
import com.recetario.modelo.DtoReceta;
import com.recetario.modelo.DtoUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/*
    private int IdU;
    private String Nom;
    private String Usu;
    private String Fnac;
    private String Cont;
    private String Img;
*/
public class DaoRecetario {
    public boolean insertarUsu(DtoUsuario Usuario) {
        String sql = "INSERT INTO contacto VALUES (?,?,?,?,?)";
        Object p[] = new Object[6];
        p[0] = Usuario.getIdU();
        p[1] = Usuario.getNom();
        p[2] = Usuario.getUsu();
        p[3] = Usuario.getFnac();
        p[4] = Usuario.getCont();
        p[5]= Usuario.getImg();
        Conexion con = Conexion.newInstancia();
        boolean ok = con.cambioBD(sql, p);
        return ok;
    }
}
