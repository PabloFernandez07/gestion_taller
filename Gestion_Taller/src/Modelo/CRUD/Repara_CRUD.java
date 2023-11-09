/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Reparacion;
import Modelo.Clases.Tipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author dam211
 */
public class Repara_CRUD {

    /**
     * Metodo para mostrar las reparaciones por ID
     *
     * @param pkRepara
     * @return
     */
    public Reparacion mostrarReparaPk(int pkRepara) {
        Reparacion repara = new Reparacion();
        try {

            String sql = "Select * from repara where pkRepara = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);) {
                ps.setInt(1, pkRepara);

                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    repara.setPkReparacion(rs.getInt("pkRepara"));
                    repara.setFkVehiculo(rs.getInt("fkVehiculo"));
                    repara.setFechaIni(rs.getDate("fechaIni"));
                    repara.setFechaFin(rs.getDate("fechaFin"));
                } else {
                    System.err.println("Reparacion no encontrada con el id " + pkRepara);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return repara;
    }
    
    public static ArrayList<Reparacion> mostrarReparaciones(Connection conn) {
        ArrayList<Reparacion> reparaciones = new ArrayList<>();
        Reparacion rep = new Reparacion();
        
        try {
            String sql = "SELECT * FROM repara;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rep.setPkReparacion(rs.getInt(1));
                rep.setFkVehiculo(rs.getInt(2));
                rep.setFechaIni(rs.getDate(3));
                rep.setFechaFin(rs.getDate(4));

                
                reparaciones.add(rep);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reparaciones;
    }
    
    public static Reparacion mostrarRepara(int fkVehiculo) {
        Reparacion repara = new Reparacion();
        try {

            String sql = "Select * from repara where fkVehiculo = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);) {
                ps.setInt(1, fkVehiculo);

                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    repara.setPkReparacion(rs.getInt("pkRepara"));
                    repara.setFkVehiculo(rs.getInt("fkVehiculo"));
                    repara.setFechaIni(rs.getDate("fechaIni"));
                    repara.setFechaFin(rs.getDate("fechaFin"));
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return repara;
    }

    /**
     * Metodo para insertar una nueva reparación
     *
     * @param fkVehi
     * @param fechaIni
     * @param fechaFin
     * @return
     */
    public static boolean insertarReparacion(int fkVehi, Date fechaIni, Date fechaFin) {

        try {

            String sql = "insert into repara(fkVehiculo, fechaIni, fechaFin) values (?,?,?);";
            int pk = pkReparaMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setDate(2, fechaIni);
                ps.setDate(3, fechaFin);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Reparación añadidida con exito");
                    return true;
                } else {
                    System.err.println("Problemas al añadir la reparación");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para encontrar el Id de reparación mas alto para al insertar sumar
     * 1 a este.
     *
     * @return
     */
    public static int pkReparaMayor() {
        int claveMayor = 0;
        try {

            String sql = "Select max(pkRepara) from repara;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    claveMayor = rs.getInt("pkRepara");
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }
        return claveMayor;
    }

    /**
     * Metodo para modificar una reparación
     *
     * @param pkRepara
     * @param fkVehiculo
     * @return
     */
    public boolean modificarRepara(int pkRepara, int fkVehiculo) {

        try {

            String sql = "update repara set fkVehiculo=? where pkRepara=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkRepara);
                ps.setInt(2, fkVehiculo);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Reparación modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar la reparación");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar una reparación
     *
     * @param pkRepara
     * @return
     */
    public boolean eliminarRepara(int pkRepara) {

        try {

            String sql = "delete from repara where pkRepara=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkRepara);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Reparación eliminada con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar la reparación");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
