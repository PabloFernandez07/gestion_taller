/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Vehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dam211
 */
public class Vehiculo_CRUD {

    /**
     * Metodo para mostrar los Vehiculos por ID
     *
     * @param pkVehi
     * @return
     */
    public Vehiculo mostrarVehiculoPK(int pkVehi) {
        Vehiculo vehiculo = new Vehiculo();
        String sql = "Select * from vehiculo where pkVehiculo = ?;";

        try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
            ps.setInt(1, pkVehi);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vehiculo.setPkVehiculo(rs.getInt("pkVehiculo"));
                vehiculo.setFkTipo(rs.getInt("fkTipo"));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setFkCliente(rs.getInt("fkCliente"));

            } else {
                System.err.println("Vehiculo no encontrado con el id " + pkVehi);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return vehiculo;
    }

    public static Vehiculo mostrarVehiculo(String matricula) {
        Vehiculo vehiculo = new Vehiculo();
        String sql = "Select * from vehiculo where matricula = ?;";

        try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
            ps.setString(1, matricula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vehiculo.setPkVehiculo(rs.getInt("pkVehiculo"));
                vehiculo.setFkTipo(rs.getInt("fkTipo"));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setFkCliente(rs.getInt("fkCliente"));

            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return vehiculo;
    }

    /**
     * Metodo para insertar un nuevo vehiculo
     *
     * @param fk_Tipo
     * @param matricula
     * @param modelo
     * @param fk_Cliente
     * @return
     */
    public static boolean insertarVehiculo(int fk_Tipo, String matricula, String modelo, int fk_Cliente) {

        try {

            String sql = "insert into vehiculo(pkVehiculo, fkTipo, matricula, modelo, fkCliente) values (?,?,?,?,?);";
            int pk = pkVehiculoMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setInt(2, fk_Tipo);
                ps.setString(3, matricula);
                ps.setString(4, modelo);
                ps.setInt(5, fk_Cliente);

                if (ps.executeUpdate() == 1) {
                    return true;
                }
                ps.close();
            }

        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para encontrar el Id de vehiculo mas alto para al insertar sumar 1
     * a este.
     *
     * @return
     */
    public static int pkVehiculoMayor() throws SQLException {
        int max = 0;
        String sql = "SELECT MAX(pkVehiculo) FROM vehiculo";
        PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery(sql);

        rs.next();
        max = rs.getInt(1);
        return max;
    }

    /**
     * Metodo para modificar un vehiculo
     *
     * @param pkVehi
     * @param modelo
     * @return
     */
    public boolean modificarVehiculo(int pkVehi, String modelo) {

        try {

            String sql = "update vehiculo set modelo=? where pkVehiculo=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setString(1, modelo);
                ps.setInt(2, pkVehi);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Vehiculo modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el vehiculo");
                }
                ps.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar un cliente
     *
     * @param pkVehi
     * @return
     */
    public boolean eliminarVehiculo(int pkVehi) {

        try {

            String sql = "delete from vehiculo where pkVehiculo=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkVehi);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Vehiculo eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el vehiculo");
                }
                ps.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
