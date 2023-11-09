/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

/**
 *
 * @author dam211
 */
public class Cliente {
    
    private int pkCliente;
    private String nombre;
    private String nif;

    public Cliente() {
    }
    
    
    public Cliente(int pkCliente, String nombre, String nif) {
        this.pkCliente = pkCliente;
        this.nombre = nombre;
        this.nif = nif;
    }

    public int getPkCliente() {
        return pkCliente;
    }

    public void setPkCliente(int pkCliente) {
        this.pkCliente = pkCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    
    
    
}
