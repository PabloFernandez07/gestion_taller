/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.CRUD;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dam211
 */
public class Cliente_CRUD {

    /**
     * Metodo para mostrar los clientes por ID
     *
     * @param pkCliente
     * @return
     */
    public Cliente mostrarClientePk(int pkCliente) {
        Cliente cliente = new Cliente();
        try {

            String sql = "Select * from cliente where pkCliente = ?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkCliente);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    cliente.setPkCliente(rs.getInt("pkCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNif(rs.getString("nif"));
                } else {
                    System.err.println("Cliente no encontrado con el id " + pkCliente);
                }

            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL." + ex.getMessage());
        }

        return cliente;
    }

    public static Cliente mostrarClienteNif(String nif) {
        Cliente cliente = new Cliente();
        String sql = "Select * from cliente where nif = ?;";

        try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
            ps.setString(1, nif);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setPkCliente(rs.getInt("pkCliente"));
                cliente.setNif(rs.getString("nif"));
                cliente.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL." + ex.getMessage());
        }

        return cliente;
    }

    public static boolean existeClienteNif(String Nif) {

        String sql = "SELECT * FROM cliente WHERE nif=?;";

        try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
            ps.setString(1, Nif);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis del SQL." + ex.getMessage());
        }

        return false;
    }

    /**
     * Metodo para insertar un nuevo cliente
     *
     * @param nif
     * @param nombre
     * @return
     */
    public static boolean insertarCliente(String nif, String nombre) {

        try {

            String sql = "insert into cliente(pkCliente, nif, nombre) values (?,?,?);";
            int pk = pkClienteMayor() + 1;

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pk);
                ps.setString(2, nif);
                ps.setString(3, nombre);

                if (ps.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, "Nuevo cliente creado con exito.");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas al añadir el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo para encontrar el Id de cliente mas alto para al insertar sumar 1
     * a este.
     *
     * @return
     */
    public static int pkClienteMayor() throws SQLException {
        int max = 0;
        String sql = "SELECT MAX(pkCliente) FROM cliente";
        PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery(sql);

        rs.next();
        max = rs.getInt(1);
        return max;
    }

    /**
     * Metodo para modificar un cliente
     *
     * @param pkCliente
     * @param nombre
     * @return
     */
    public boolean modificarCliente(int pkCliente, String nombre) {

        try {

            String sql = "update cliente set nombre=? where pkCliente=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setInt(2, pkCliente);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Cliente modificado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al modificar el cliente");
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
    public boolean eliminarCliente(int pkCliente) {

        try {

            String sql = "delete from cliente where pkCliente=?;";

            try (PreparedStatement ps = Conexion_bbdd.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pkCliente);

                if (ps.executeUpdate() == 1) {
                    System.out.println("Cliente eliminado con exito");
                    return true;
                } else {
                    System.err.println("Problemas al eliminar el cliente");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en la sintaxis " + ex.getMessage());
        }
        return false;
    }

}
