/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Controlador.Controlador;
import Modelo.BBDD.Conexion_bbdd;
import Modelo.CRUD.Mecanico_CRUD;
import Modelo.Clases.Mecanico;
import java.sql.Connection;
import java.sql.SQLException;
import Vista.Ventana_gestion;
import Vista.Ventana_principal;
import java.util.ArrayList;

/**
 *
 * @author dam211
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Conexion_bbdd c = new Conexion_bbdd();
        Connection conn = c.getConnection();

        //Mecanico_CRUD.insertaMecanico("Pepe");

//        ArrayList<Mecanico> listaMecanicos = Mecanico_CRUD.mostrarMeca();
//        ArrayList<Reparacion> listaReparaciones = MetodosBBDD.mostrarReparaciones();
//        ArrayList<Servicio> listaServicios = MetodosBBDD.mostrarServicio();
//        ArrayList<Servicios_repara> listaServRep = MetodosBBDD.mostrarServRep();
//        ArrayList<Tipo> listaTipo = MetodosBBDD.mostrarTipo();
//        ArrayList<Vehiculo> listaVehiculo = Modelo.CRUD.Vehiculo_CRUD.mostrarClientePk(0);

        //---------- Mostrar cliente por ID
//
//        if (cliente.getNombre() != null) {
//            System.out.println(" ");
//            System.out.println("Id cliente: " + cliente.getPkCliente());
//            System.out.println("Nombre cliente: " + cliente.getNombre());
//            System.out.println("NIF cliente: " + cliente.getNif());
//        }
        //-------------- Mostrar vehiculos y dueños
        //MetodosBBDD.mostrarVehiculoCliente();
        //-------------- Mostrar vehiculos y dueños especificos
        //MetodosBBDD.mostrarVehiculoClientepk(1);
        //-------------- Insertar Cliente
        //MetodosBBDD.insertarCliente("11111111P", "Cliente 4");
        //-------------- Insertar Cliente
        //MetodosBBDD.modificarCliente(4,"Federico");
        //-------------- Insertar Cliente
//        MetodosBBDD.eliminarCliente(4);
        //System.out.println(MetodosBBDD.pkClienteMayor());
//        
//               
//        Cliente cliente = MetodosBBDD.mostrarClientePk(4);
////
//        if (cliente.getNombre() != null) {
//            System.out.println(" ");
//            System.out.println("Id cliente: " + cliente.getPkCliente());
//            System.out.println("Nombre cliente: " + cliente.getNombre());
//            System.out.println("NIF cliente: " + cliente.getNif());
//        }
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        /**
//         * Mostar Clientes
//         */
//        int contadorClientes=1;
//        for(Cliente c : listaClientes){
//            System.out.println("--------- Cliente "+contadorClientes+" ---------");
//            System.out.println("Id Cliente: "+c.getPkCliente());
//            System.out.println("Nombre Cliente: "+c.getNombre());
//            System.out.println("NIF Cliente: "+c.getNif());
//            System.out.println(" ");
//            contadorClientes++;
//        }
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        
//        /**
//         * Mostar Mecanicos
//         */
//        int contadorMeca=1;
//        for(Mecanico m : listaMecanicos){
//            System.out.println("--------- Mecanico "+contadorMeca+" ---------");
//            System.out.println("Id Mecanico: "+m.getPkMecanico());
//            System.out.println("Nombre Mecanico: "+m.getNombre());
//            System.out.println(" ");
//            contadorMeca++;
//        }
//        
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        
//        /**
//         * Mostar Reparaciones
//         */
//        int contadorRep=1;
//        for(Reparacion r : listaReparaciones){
//            System.out.println("--------- Reparacion "+contadorRep+" ---------");
//            System.out.println("Id Reparacion: "+r.getPkReparacion());
//            System.out.println("Id vehiculo: "+r.getFkVehiculo());
//            System.out.println("Fecha de inicio: "+r.getFechaIni());
//            System.out.println("Fecha de fin: "+r.getFechaFin());
//            System.out.println(" ");
//            contadorRep++;
//        }
//        
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        
//        /**
//         * Mostar Servicios
//         */
//        int contadorServ=1;
//        for(Servicio s : listaServicios){
//            System.out.println("--------- Servicio "+contadorServ+" ---------");
//            System.out.println("Id Servicio: "+s.getPkServicio());
//            System.out.println("Nombre: "+s.getNombre());
//            System.out.println("Precio: "+s.getPrecio());
//            System.out.println(" ");
//            contadorServ++;
//        }
//        
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        
//        /**
//         * Mostar Servicios Reparacion
//         */
//        int contadorServRep=1;
//        for(Servicios_repara sr : listaServRep){
//            System.out.println("--------- Servicio Reparación "+contadorServRep+" ---------");
//            System.out.println("Id Servicio Reparación: "+sr.getPkSr());
//            System.out.println("ID Reparación: "+sr.getFkRepara());
//            System.out.println("ID Mecanico: "+sr.getFkMecanico());
//            System.out.println("Tiempo: "+sr.getTiempo());
//            System.out.println(" ");
//            contadorServRep++;
//        }
//        
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//        
//        /**
//         * Mostar tipo
//         */
//        int contadorTipo=1;
//        for(Tipo t : listaTipo){
//            System.out.println("--------- Tipo "+contadorTipo+" ---------");
//            System.out.println("Id Tipo: "+t.getPkTipo());
//            System.out.println("Nombre: "+t.getNombre());
//            System.out.println(" ");
//            contadorTipo++;
//        }
//        
//        System.out.println("\n-------------------------------------------------------------------------------\n");
//
//        /**
//         * Mostar Vehiculos
//         */

        // ------------- Diseño de interfaces -----------------
        Ventana_principal principal = new Ventana_principal();
        Ventana_gestion gestion = new Ventana_gestion();
        
        Controlador control = new Controlador(principal, gestion);
        control.iniciar();
        System.out.println("");
    }

}
