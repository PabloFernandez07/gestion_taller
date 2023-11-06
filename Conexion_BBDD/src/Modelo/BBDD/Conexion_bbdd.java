/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BBDD;
 
import java.sql.*;
/**
 *
 * @author dam114
 */
public class Conexion_bbdd {
    static Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER="admin_taller";
    static final String PASS = "admin123";
    static final String BD="taller";
    static final String DBURL = "jdbc:mysql://localhost:3306/"+BD+"?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static Conexion_bbdd INSTANCE;

    /**
     * 
     * Conexion a la BBDD
     */
    
    public Conexion_bbdd(){
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            conn=DriverManager.getConnection(DBURL, USER, PASS);
            if(conn!=null){
                System.out.println("Conexión a la base de datos CORRECTA\n");
            }
        } 
        catch(SQLException | InstantiationException | IllegalAccessException ex){
            System.err.println("Problemas al conectar con la Base de datos");
        } 
        catch (ClassNotFoundException ex){
            System.err.println("Problemas al encontrar el Driver JDBC.");
        }
    }
    
    public void Desconecta (Connection connection){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Cerrando conexion....");
            } catch (SQLException ex) {
                System.err.println("No se puede cerrar la conexion con la BBDD. "+         ex.getMessage());
            }
        }
    }

    /**
     * 
     * Getter
     * @return
     */
    public static Conexion_bbdd getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Conexion_bbdd();
        }
        return INSTANCE;
    }
    
    public static Connection getConnection() {
        return conn;
    }

    



}
