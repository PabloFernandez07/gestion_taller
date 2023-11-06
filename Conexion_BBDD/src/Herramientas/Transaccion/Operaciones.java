/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herramientas.Transaccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author dam211
 */
public class Operaciones {
    
    /**
     * Obtener datos de la tabla aparato
     * @param pkaparato
     * @return
     * @throws SQLException 
     */
    public Aparato getAparato(int pkaparato) throws SQLException{
        Aparato aparato = new Aparato();
        Conexion c = new Conexion();
        Connection conn = c.getConnection();
        
        String sql = "select * from aparato where pkaparato = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pkaparato);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            aparato.setPkaparato(rs.getInt(1));
            aparato.setModelo(rs.getString(2));
            aparato.setFkcliente(rs.getInt(3));
        }
        rs.close();
        ps.close();
        c.Desconecta(conn);
        
        return aparato;
    }
    
    /**
     * Obtener datos de la tabla cliente
     * @param pkcliente
     * @return
     * @throws SQLException 
     */
    public Cliente getCliente(int pkcliente) throws SQLException{
        Cliente cliente = new Cliente();
        Conexion c = new Conexion();
        Connection conn = c.getConnection();
        
        String sql = "select * from cliente where pkcliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pkcliente);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            cliente.setPkcliente(rs.getInt(1));
            cliente.setNombre(rs.getString(2));
        }
        rs.close();
        ps.close();
        c.Desconecta(conn);
        
        return cliente;
    }
    
    /**
     * Obtener datos de la tabla linearepara
     * @param pklinea
     * @return
     * @throws SQLException 
     */
    public LineaRepara getLineaRepara(int pklinea) throws SQLException{
        LineaRepara lineaR = new LineaRepara();
        Conexion c = new Conexion();
        Connection conn = c.getConnection();
        
        String sql = "select * from linearepara where pklinea = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pklinea);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            lineaR.setPklinea(rs.getInt(1));
            lineaR.setFkrepara(rs.getInt(2));
            lineaR.setFkservicio(rs.getInt(3));
            lineaR.setMinutos(rs.getInt(4));
        }
        rs.close();
        ps.close();
        c.Desconecta(conn);
        
        return lineaR;
    }
    
    /**
     * Obtener datos de la tabla repara
     * @param pkrepara
     * @return
     * @throws SQLException 
     */
    public Repara getRepara(int pkrepara) throws SQLException{
        Repara repara = new Repara();
        Conexion c = new Conexion();
        Connection conn = c.getConnection();
        
        String sql = "select * from repara where pkrepara = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pkrepara);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            repara.setPkrepara(rs.getInt(1));
            repara.setFkaparato(rs.getInt(2));
            repara.setFecha_ini(rs.getDate(3));
            repara.setFecha_fin(rs.getDate(4));
        }
        rs.close();
        ps.close();
        c.Desconecta(conn);
        
        return repara;
    }
    
    /**
     * Obtener datos de la tabla repara
     * @param pkservicio
     * @return
     * @throws SQLException 
     */
    public Servicio getServicio(int pkservicio) throws SQLException{
        Servicio servicio = new Servicio();
        Conexion c = new Conexion();
        Connection conn = c.getConnection();
        
        String sql = "select * from servicio where pkservicio = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pkservicio);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            servicio.setPkservicio(rs.getInt(1));
            servicio.setNombre(rs.getString(2));
            servicio.setPrecio(rs.getDouble(3));
        }
        rs.close();
        ps.close();
        c.Desconecta(conn);
        
        return servicio;
    }
    
}
