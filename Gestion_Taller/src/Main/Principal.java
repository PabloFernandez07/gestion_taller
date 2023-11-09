/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Controlador.Controlador;
import Modelo.BBDD.Conexion_bbdd;
import java.sql.Connection;
import java.sql.SQLException;
import Vista.Ventana_gestion;
import Vista.Ventana_principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam211
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        // ------------- Diseño de interfaces -----------------
        Ventana_principal principal = new Ventana_principal();
        Ventana_gestion gestion = new Ventana_gestion();

        Controlador control = new Controlador(principal, gestion);
        control.iniciar();
        
    }

}
