/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Servicios_repara;
import Modelo.Clases.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam211
 */
public class ServRep_CRUD {

    /**
     * Metodo para mostrar los Serv Rep por ID
     *
     * @param pkSr
     * @return
     */
    public static Servicios_repara mostrarSerRepPk(int pkSr) {
        Servicios_repara sr = new Servicios_repara();
        try {

            String sql = "Select * from servicio_repara where pkSr = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);) {
                ps.setInt(1, pkSr);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    sr.setPkSr(rs.getInt("pkSr"));
                    sr.setFkServicio(rs.getInt("fkServicio"));
                    sr.setFkRepara(rs.getInt("fkRepara"));
                    sr.setFkMecanico(rs.getInt("fkMecanico"));
                    sr.setTiempo(rs.getInt("tiempo"));
                } else {
                    System.err.println("Servicio/Repara no encontrado con el id " + pkSr);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return sr;
    }

    /**
     * Metodo para insertar un nuevo Servicio/Repara
     *
     * @param fkRepara
     * @param fkMecanico
     * @param tiempo
     * @return
     */
    public static boolean insertarSr(int fkServicio, int fkRepara, int fkMecanico, int tiempo) {

        try {

            String sql = "insert into servicio_repara(pkSr,fkServicio, fkRepara, fkMecanico, tiempo) values (?,?,?,?,?);";
            int pk = pkSrMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setInt(2, fkServicio);
                ps.setInt(3, fkRepara);
                ps.setInt(4, fkMecanico);
                ps.setInt(5, tiempo);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio/Repara añadidido con exito");
                    return true;
                } else {
                    System.err.println("Problemas al añadir el Servicio/Repara");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para encontrar el Id de Servicio/Repara mas alto para al insertar
     * sumar 1 a este.
     *
     * @return
     */
    public static int pkSrMayor() {
        int claveMayor = 0;
        try {

            String sql = "Select max(pkSr) from servicio_repara;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    claveMayor = rs.getInt("pkSr");
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }
        return claveMayor;
    }

    /**
     * Metodo para modificar un Servicio/Repara
     *
     * @param pkSr
     * @param fkRepara
     * @return
     */
    public boolean modificarServRep(int pkSr, int fkRepara) {

        try {

            String sql = "update servicio_repara set fkRepara=? where pkSr=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, fkRepara);
                ps.setInt(2, pkSr);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio/Repara modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el Servicio/Repara");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar un cliente
     *
     * @param pkSr
     * @return
     */
    public boolean eliminarServRep(int pkSr) {

        try {

            String sql = "delete from servicio_repara where pkSr=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkSr);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio/Repara eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el Servicio/Repara");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
