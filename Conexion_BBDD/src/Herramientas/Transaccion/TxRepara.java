/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herramientas.Transaccion;

import Modelo.BBDD.Conexion_bbdd;
import Modelo.Clases.Reparacion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author dam211
 */
public class TxRepara {

    static String sql_1 = "INSERT INTO repara(pkRepara, fkVehiculo, fechaIni) values (?,?,?);";
    static String sql_2 = "INSERT INTO servicio_repara(pkSr, fkServicio, fkRepara, fkMecanico) VALUES (?,?,?,?);";
    static String sql_3 = "SELECT * FROM repara WHERE fkVehiculo = ? AND fechaFin IS NULL;";

    public static void Reparacion(Connection conn, int pkVehiculo, int pkMecanico, int pkServicio, Date fechaIni) throws SQLException {

            Reparacion repara = new Reparacion();

            int pkReparaMax = maxPkRepara(conn) + 1;
            
            //Comprueba si existe una reparacion
            //Si existe, la introduce a repara.
            //No existe, la crea y la introduce a repara
            repara = ExisteReparacion(conn, pkVehiculo, pkReparaMax, fechaIni);

            //Si se ha creado la Reparacion, inserta una nueva linea de Servicio_repara.
            NuevoServicioRepara(conn, pkServicio, repara.getPkReparacion(), pkMecanico);


    }

    private static Reparacion ExisteReparacion(Connection conn, int pkVehiculo, int pkReparaMax, Date fechaIni) throws SQLException {
        Reparacion repara = new Reparacion();
        
        PreparedStatement ps = conn.prepareStatement(sql_3);
        ps.setInt(1, pkVehiculo);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            repara.setPkReparacion(rs.getInt(1));
            repara.setFkVehiculo(rs.getInt(2));
            repara.setFechaIni(rs.getDate(3));
            repara.setFechaFin(rs.getDate(4));
        } else {
            //Inserta nueva Reparación
            AñadirReparacion(conn, pkReparaMax, pkVehiculo, fechaIni);
            
            repara.setPkReparacion(pkReparaMax);
            repara.setFkVehiculo(pkVehiculo);
            repara.setFechaIni(fechaIni);
            repara.setFechaFin(null);
        }
        
        return repara;
    }

    private static int AñadirReparacion(Connection conn, int maxPkRepara, int pkVehiculo, Date fechaIni) throws SQLException {
        int exito = 0;

        //Ejecuto la sentencia de añadir una nueva Reparacion (SQL_1)
        PreparedStatement ps = conn.prepareStatement(sql_1);
        ps.setInt(1, maxPkRepara);
        ps.setInt(2, pkVehiculo);
        ps.setDate(3, fechaIni);

        if (ps.executeUpdate() == 1) {
            exito = 1;
        }
        return exito;
    }

    private static int NuevoServicioRepara(Connection conn, int pkServicio, int maxPkRepara, int pkMecanico) throws SQLException {
        int insertado = 0;
        int makPkSr = maxPkSr(conn) + 1;

        PreparedStatement ps = conn.prepareStatement(sql_2);
        ps.setInt(1, makPkSr);
        ps.setInt(2, pkServicio);
        ps.setInt(3, maxPkRepara);
        ps.setInt(4, pkMecanico);

        if (ps.executeUpdate() == 1) {
            insertado = 1;
        }
        return insertado;
    }

    private static int maxPkRepara(Connection conn) throws SQLException {
        int pkMax = 0;
        String sql = "SELECT max(pkRepara) from repara;";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pkMax = rs.getInt(1);
        }

        //Cierro los PreparedStatement y los ResultSet
        rs.close();
        ps.close();
        return pkMax;

    }

    private static int maxPkSr(Connection conn) throws SQLException {
        int pkMax = 0;
        String sql = "SELECT max(pkSr) from servicio_repara;";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pkMax = rs.getInt(1);
        }

        //Cierro los PreparedStatement y los ResultSet
        rs.close();
        ps.close();
        return pkMax;

    }

}
