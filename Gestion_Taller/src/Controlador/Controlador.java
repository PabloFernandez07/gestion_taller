/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.CRUD.Cliente_CRUD;
import Modelo.CRUD.Mecanico_CRUD;
import Modelo.CRUD.Servicio_CRUD;
import Modelo.CRUD.Tipo_CRUD;
import Modelo.CRUD.Vehiculo_CRUD;
import Modelo.Clases.Cliente;
import Modelo.Clases.Mecanico;
import Modelo.Clases.Servicio;
import Modelo.Clases.Tipo;
import Modelo.Clases.Vehiculo;
import Herramientas.Transaccion.TxRepara;
import Modelo.BBDD.Conexion_bbdd;
import Modelo.CRUD.Repara_CRUD;
import Modelo.Clases.Reparacion;
import Vista.Ventana_gestion;
import Vista.Ventana_principal;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dam211
 */
public class Controlador implements ActionListener {

    Ventana_principal principal;
    Ventana_gestion gestion;
    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono_taller.png"));

    //Creo la Conexion_bbdd con la BBDD
    Conexion_bbdd c = new Conexion_bbdd();
    Connection conn = c.getConnection();

    String[] columnas = {"Servicio", "Precio"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    public Controlador(Ventana_principal principal, Ventana_gestion gestion) {
        this.principal = principal;
        this.gestion = gestion;

        //Action Listeners
        this.principal.botonRegistrar.addActionListener(this);
        this.principal.botonBuscar.addActionListener(this);
        this.principal.botonMenuRegistrar.addActionListener(this);
        this.principal.botonMenuBuscar.addActionListener(this);
        this.principal.botonMenuAcerca.addActionListener(this);
        this.gestion.botonGuardar.addActionListener(this);
        this.gestion.botonCancelar.addActionListener(this);
        this.gestion.comboServicio.addActionListener(this);
        this.gestion.comboTipo.addActionListener(this);
        this.gestion.botonAñadir.addActionListener(this);
        this.gestion.botonEliminar.addActionListener(this);
        this.gestion.comboMatricula.addActionListener(this);

    }

    public void iniciar() {
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        principal.setIconImage(iconoPropio);
        gestion.tabla_servicios.setModel(modelo);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Boton menu principal Registrar
         */
        if (e.getSource() == principal.botonRegistrar || e.getSource() == principal.botonMenuRegistrar) {

            metodoBotonRegistrar();

            /**
             * Boton Menu Principal Buscar
             */
        } else if (e.getSource() == principal.botonBuscar || e.getSource() == principal.botonMenuBuscar) {

            metodoBotonBuscar();

            /**
             * Boton Menu principal Acerca de
             */
        } else if (e.getSource() == principal.botonMenuAcerca) {

            JOptionPane.showMessageDialog(principal, "Gestión del Taller V1.1", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);

            /**
             * Boton de guardar
             */
        } else if (e.getSource() == this.gestion.botonGuardar) {

            String nombre = gestion.txt_nombre.getText();
            String nif = gestion.txt_nif.getText();
            //String matricula = gestion.txt_matricula.getText();
            String modelo = gestion.txt_modelo.getText();
            String tipo = (String) gestion.comboTipo.getSelectedItem();
            Cliente cli = Cliente_CRUD.mostrarClienteNif(nif);
            String mecanicoNombre = (String) gestion.comboMecanico.getSelectedItem();
            Mecanico mecanico = Mecanico_CRUD.mostrarMecaNombre(mecanicoNombre);
            int pkVeh = (int) gestion.comboMatricula.getSelectedItem();
            Vehiculo veh = Vehiculo_CRUD.mostrarVehiculoPK(pkVeh);

            
            //Tipo tip = Tipo_CRUD.mostrarTipoPk(veh.getFkTipo());
            

            //Date fechaIni = (Date) gestion.spinFechaIni.getValue();
            Date fechaIni = Date.valueOf(LocalDate.now());
            //Date fechaFin = (Date) gestion.spinFechaFin.getValue();

            ArrayList<Servicio> servicios = new ArrayList<>();
            /*---*/
            for (int fila = 0; fila < this.modelo.getRowCount(); fila++) {
                Servicio serv = new Servicio();
                Object servicioObject = this.modelo.getValueAt(fila, 0);
                String servicio = servicioObject.toString();

                serv = Servicio_CRUD.mostrarServicioCompleto(servicio);

                servicios.add(serv);
            }

            //Comprobar si existe un cliente y si no existe lo añade
            if (!Cliente_CRUD.existeClienteNif(nif) && !nombre.isEmpty()) {

                //Añade un nuevo Cliente
                InsertaNuevoCliente(nif, nombre);

                //Si el nombre esta vacio salta ventana de error
            } else if (nombre.isEmpty()) {

                //Salta el ERROR
                JOptionPane.showMessageDialog(gestion, "Rellena el campo Nombre.", "Error", JOptionPane.ERROR_MESSAGE);

                //Comprobar el nombre del cliente con el NIF introducido.
                //Coincide con el nombre introducido
            } else if (cli.getNombre().equals(nombre)) {

                //Si los 3 campos estan vaciados, salta ERROR
                if (modelo.isEmpty()) {

                    JOptionPane.showMessageDialog(gestion, "Rellena los campos matricula y modelo.", "Error", JOptionPane.ERROR_MESSAGE);

                    //Compara el modelo de la BBDD con el introducido y el
                    //nombre del tipo coincide con el valor del comboBox
                } else {

                    TxRepara tx = new TxRepara();
                    
                    

                    //Creo la transaccion con la conexion fuera de esta.
                    try {
                        conn.setAutoCommit(false);

                        for (Servicio serv : servicios) {
                            tx.Reparacion(conn, veh.getPkVehiculo(), mecanico.getPkMecanico(), serv.getPkServicio(), fechaIni);
                        }

                        conn.commit();
                        conn.setAutoCommit(true);
                        //Si no es nada de lo anterior, añade el vehiculo
                    } catch (SQLException ex) {
                        try {
                            conn.rollback();
                        } catch (SQLException ex1) {
                            System.out.println("ERROR GRAVE, Problemas al realizar el rollback.");
                        }
                        System.out.println("Error al realizar el commit. " + ex.getMessage());
                    }

                }

                //Si el nombre no coincide con el Nif, salta error.
            } else {
                JOptionPane.showMessageDialog(gestion, "El nombre no coincide con el NIF");
            }

            //Boton de cancelar
        } else if (e.getSource() == this.gestion.botonCancelar) {

            metodoBotonCancelar();

            /**
             * Boton Guardar Tabla
             */
        } else if (e.getSource() == this.gestion.botonAñadir) {
            String nombreServ = (String) gestion.comboServicio.getSelectedItem();

            Servicio serv = Servicio_CRUD.mostrarServicioCompleto(nombreServ);
            modelo.addRow(new Object[]{serv.getNombre(), serv.getPrecio()});

            //gestion.precioTotal.setText(precioString);
            /**
             * Acciones del boton Eliminar
             */
        } else if (e.getSource()
                == this.gestion.botonEliminar) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                int filaSeleccionada = gestion.tabla_servicios.getSelectedRow();

                if (filaSeleccionada != -1) {
                    /**
                     * Borrar la fila seleccionada del modelo de tabla
                     */
                    modelo.removeRow(filaSeleccionada);
                }
            }
        }
    }

    /**
     * Metodo de las acciones del boton Guardar cliente
     *
     * @param nif
     * @param nombre
     * @throws HeadlessException
     */
    private void InsertaNuevoCliente(String nif, String nombre) {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "El cliente no exite, ¿Deseas guardar al cliente nuevo?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            Cliente_CRUD.insertarCliente(nif, nombre);
        } else {
        }
    }

    /**
     * Metodo del boton Cancelar
     *
     * @throws HeadlessException
     */
    private void metodoBotonCancelar() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas salir sin guardar los cambios?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.gestion.dispose();

            //Cierro la conexion con la BBDD
            try {
                conn.close();
                System.out.println("Cerrando conexion");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexion. " + ex.getMessage());
            }
        } else {

        }
    }

    private void metodoBotonBuscar() {

    }

    /**
     * Acciones cuando se presiona el boton Registrar del menu principal
     *
     * @throws HeadlessException
     */
    private void metodoBotonRegistrar() {
        setCamposNull();
        Conexion_bbdd c = new Conexion_bbdd();
        conn = c.getConnection();

        ArrayList<Mecanico> mecanicos = Mecanico_CRUD.mostrarMeca();
        ArrayList<Servicio> servicios = Servicio_CRUD.mostrarServ();
        ArrayList<Tipo> tipos = Tipo_CRUD.mostrarTipo();
        ArrayList<Vehiculo> vehiculos = Vehiculo_CRUD.mostrarVehiculos(conn);

        
        /**
         * Llenar el ComboBox con los pk de reparaciones
         */
        for (Vehiculo veh : vehiculos) {
            gestion.comboMatricula.addItem(Integer.toString(veh.getPkVehiculo()));
        }
        
        /**
         * Llenar el ComboBox con los tipos de vehiculos
         */
        for (Tipo tipo : tipos) {
            gestion.comboTipo.addItem(tipo.getNombre());
        }

        /**
         * Llenar el ComboBox con los nombres de los mecánicos
         */
        for (Mecanico mecanico : mecanicos) {
            gestion.comboMecanico.addItem(mecanico.getNombre());
        }

        /**
         * Llenar el ComboBox con los nombres de los servicios
         */
        for (Servicio servicio : servicios) {
            gestion.comboServicio.addItem(servicio.getNombre());
        }

        this.gestion.setLocationRelativeTo(principal);
        this.gestion.setTitle("Vehiculos");
        this.gestion.titulo.setText("VEHICULOS");
        this.gestion.setIconImage(iconoPropio);

        this.gestion.panelTransparente.setBackground(new Color(255, 255, 255, 128));
        this.gestion.setVisible(true);
    }

    public void setCamposNull() {
        gestion.txt_nombre.setText(null);
        gestion.txt_nif.setText(null);
        gestion.txt_modelo.setText(null);

        gestion.precioTotal.setText(null);

        gestion.comboTipo.removeAllItems();
        gestion.comboMecanico.removeAllItems();
        gestion.comboServicio.removeAllItems();

        gestion.spinFechaIni = new JSpinner();
        gestion.spinFechaFin = new JSpinner();

        modelo.setRowCount(0);
        gestion.tabla_servicios.repaint();

        gestion.comboTipo.setSelectedItem("Moto");
    }

}