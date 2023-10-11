/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Mecanico;
import Modelo.Clases.Servicio;
import Vista.Ventana_gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam211
 */
public class Servicio_CRUD {

    /**
     * Metodo para mostrar los Servicios por ID
     *
     * @param pkServ
     * @return
     */
    public Servicio mostrarServicioPk(int pkServ) {
        Servicio servicio = new Servicio();
        try {

            String sql = "Select * from servicio where pkServicio = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkServ);
                
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    servicio.setPkServicio(rs.getInt("pkServicio"));
                    servicio.setNombre(rs.getString("nombre"));
                    servicio.setPrecio(rs.getFloat("precio"));
                } else {
                    System.err.println("Servicio no encontrado con el id " + pkServ);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return servicio;
    }

    public float obtenerPrecioServicio(int pkServ) {
        float precio = 0;

        String sql = "Select precio from servicio where pkServicio = ?;";

        try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
            ps.setInt(1, pkServ);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                precio = rs.getFloat("precio");
            } else {
                System.err.println("Servicio no encontrado con el id " + pkServ);

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL. " +ex.getMessage());
        }
        return precio;
    }
    
    public static ArrayList<Servicio> mostrarServ() {
        ArrayList<Servicio> servicios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM servicio;";
            PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int pkServ = rs.getInt("pkServicio");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");

                Servicio servicio = new Servicio(pkServ, nombre, precio);
                servicios.add(servicio);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return servicios;
    }
    
    public static Servicio mostrarServicioCompleto(String nombre){
        Servicio servicio = new Servicio();
        try {

            String sql = "Select * from servicio where nombre = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setString(1, nombre);
                
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    servicio.setPkServicio(rs.getInt("pkServicio"));
                    servicio.setNombre(rs.getString("nombre"));
                    servicio.setPrecio(rs.getFloat("precio"));
                } else {
                    System.err.println("Servicio no encontrado con el nombre " + nombre);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return servicio;
    }

    /**
     * Metodo para insertar un nuevo servicio
     *
     * @param nombre
     * @param precio
     * @return
     */
    public boolean insertarServicio(String nombre, float precio) {

        try {

            String sql = "insert into servicio(pkServicio, nombre, precio) values (?,?,?);";
            int pk = pkServMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setString(2, nombre);
                ps.setFloat(3, precio);

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

    /**
     * Metodo para encontrar el Id de servicio mas alto para al insertar sumar 1
     * a este.
     *
     * @return
     */
    public int pkServMayor() {
        int claveMayor = 0;
        try {

            String sql = "Select max(pkServicio) from servicio;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    claveMayor = rs.getInt("pkServicio");
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }
        return claveMayor;
    }

    /**
     * Metodo para modificar un servicio
     *
     * @param pkServ
     * @param precio
     * @return
     */
    public boolean modificarServicio(int pkServ, float precio) {

        try {

            String sql = "update servicio set precio=? where pkServicio=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setFloat(1, precio);
                ps.setInt(2, pkServ);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el servicio");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar un servicio
     *
     * @param pkServ
     * @return
     */
    public boolean eliminarServicio(int pkServ) {

        try {

            String sql = "delete from servicio where pkServicio=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkServ);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Servicio eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el servicio");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
