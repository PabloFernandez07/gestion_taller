/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;


import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Mecanico;
import Modelo.Clases.Tipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam211
 */
public class Tipo_CRUD {

    /**
     * Metodo para mostrar los tipos de vehiculo por ID
     *
     * @param pkTipo
     * @return
     */
    public static Tipo mostrarTipoPk(int pkTipo) {
        Tipo tipo = new Tipo();
        try {

            String sql = "Select * from tipo where pkTipo = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);) {
                ps.setInt(1, pkTipo);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    tipo.setPkTipo(rs.getInt("pkTipo"));
                    tipo.setNombre(rs.getString("nombre"));
                } else {
                    System.err.println("Tipo no encontrado con el id " + pkTipo);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return tipo;
    }
    
    public static Tipo mostrarTipoNombre(String nombre) {
        Tipo tipo = new Tipo();
        try {

            String sql = "Select * from tipo where nombre = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);) {
                ps.setString(1, nombre);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    tipo.setPkTipo(rs.getInt("pkTipo"));
                    tipo.setNombre(rs.getString("nombre"));
                } else {
                    System.err.println("Tipo no encontrado con el nombre " + nombre);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }

        return tipo;
    }

    /**
     * Metodo para insertar un nuevo Tipo
     *
     * @param nombre
     * @return
     */
    public boolean insertarTipo(String nombre) {

        try {

            String sql = "insert into tipo(pkTipo, nombre) values (?,?);";
            int pk = pkTipoMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setString(2, nombre);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Tipo añadidido con exito");
                    return true;
                } else {
                    System.err.println("Problemas al añadir el Tipo");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para encontrar el Id del tipo mas alto para al insertar sumar 1 a
     * este.
     *
     * @return
     */
    public int pkTipoMayor() {
        int claveMayor = 0;
        try {

            String sql = "Select max(pkTipo) from tipo;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    claveMayor = rs.getInt("pkTipo");
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL.");
        }
        return claveMayor;
    }

    /**
     * Metodo para modificar un Tipo
     *
     * @param pkTipo
     * @param nombre
     * @return
     */
    public boolean modificarTipo(int pkTipo, String nombre) {

        try {

            String sql = "update tipo set nombre=? where pkTipo=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setInt(2, pkTipo);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Tipo modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el tipo");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }
    
    
    public static ArrayList<Tipo> mostrarTipo() {
        ArrayList<Tipo> tipos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tipo;";
            PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int pktipo = rs.getInt("pkTipo");
                String nombre = rs.getString("nombre");

                Tipo tipo = new Tipo(pktipo, nombre);
                tipos.add(tipo);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tipos;
    }

    /**
     * Metodo para eliminar un Tipo
     *
     * @param pkTipo
     * @return
     */
    public boolean eliminarTipo(int pkTipo) {

        try {

            String sql = "delete from tipo where pkTipo=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkTipo);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Tipo eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el tipo");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
