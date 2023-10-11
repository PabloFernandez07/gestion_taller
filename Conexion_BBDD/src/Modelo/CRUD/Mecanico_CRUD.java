/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Mecanico;
import Modelo.Clases.Vehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam211
 */
public class Mecanico_CRUD {

    /**
     * Metodo para mostrar los clientes por ID
     *
     * @param nombre
     * @return
     */
    public boolean insertaMecanico(String nombre) {
        try {
            String sql = "INSERT INTO mecanico VALUES (?, ?)";
            int pk = pkMecaMayor()+ 1; //pkmaxima + 1

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk); //sustituyo pkmecanico
                ps.setString(2, nombre); //sustituyo el nombre 

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio añadidido con exito");
                    return true;
                } else {
                    System.err.println("Problemas al añadir el servicio");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    public static ArrayList<Mecanico> mostrarMeca() {
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mecanico;";
            PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int pkMeca = rs.getInt("pkMecanico");
                String nombre = rs.getString("nombre");

                Mecanico mecanico = new Mecanico(pkMeca, nombre);
                mecanicos.add(mecanico);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mecanicos;
    }
    
    /**
     * Mostrar mecanico por nombre
     * @return 
     */
    public static Mecanico mostrarMecaNombre(String nombreMec) {
        Mecanico mecanico = new Mecanico();
        try {
            String sql = "SELECT * FROM mecanico WHERE nombre = ?;";
            PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);
            ps.setString(1, nombreMec);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mecanico.setPkMecanico(rs.getInt("pkMecanico"));
                mecanico.setNombre(rs.getString("nombre"));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mecanico;
    }

    /**
     * Metodo para encontrar el Id de cliente mas alto para al insertar sumar 1
     * a este.
     *
     * @return
     */
    public int pkMecaMayor() {
        int claveMayor = 0;
        try {

            String sql = "Select max(pkMecanico) from mecanico;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    claveMayor = rs.getInt("pkMecanico");
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }
        return claveMayor;
    }

    /**
     * Metodo para modificar un cliente
     *
     * @param pkCliente
     * @param nombre
     * @return
     */
    public boolean modificarMecanico(int pkMeca, String nombre) {

        try {

            String sql = "update mecanico set nombre=? where pkMecanico=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setInt(2, pkMeca);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Mecanico modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el mecanico");
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
     * @param pkCliente
     * @return
     */
    public boolean eliminarCliente(int pkMeca) {

        try {

            String sql = "delete from mecanico where pkMecanico=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkMeca);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Mecanico eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el mecanico");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
