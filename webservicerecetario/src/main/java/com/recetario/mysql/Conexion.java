
package com.recetario.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexion {
    private static final String user = "root";
    private static final String pass = "root";
    private static final String server = "localhost";

    private static Connection con;
    private static Conexion objcon;

    public static Conexion newInstancia() {
        if (con == null || objcon == null) {
            objcon = new Conexion();
        }
        return objcon;
    }

    private Conexion() {
        String cadcon = "jdbc:mysql://" + server + "/"
                + "recetario?useUnicode"
                + "=true&useJDBCCompliantTimezone"
                + "Shift=true&useLegacyDatetime"
                + "Code=false&serverTimezone=UTC";
        try {
            con = DriverManager.getConnection(cadcon, user, pass);
            System.out.println("Se conecto");
        } catch (Exception e) {
            System.out.println("error al con ");
            e.printStackTrace();
        }

    }

    public boolean cambioBD(String sql, Object... p) {
        boolean ok = false;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                for (int i = 0; i < p.length; i++) {
                    ps.setObject(i + 1, p[i]);
                }
                ps.execute();
                ok = true;
            } catch (Exception e) {
                System.out.println("Error al ejecutar sql");
                e.printStackTrace();
            }
        }
        return ok;
    }

    public ResultSet query(String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareCall(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println("error al consultar");
            e.printStackTrace();
        }
        return rs;
    }
}
