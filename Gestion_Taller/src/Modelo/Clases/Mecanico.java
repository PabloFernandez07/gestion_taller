/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

/**
 *
 * @author dam211
 */
public class Mecanico {
    
    private int pkMecanico;
    private String nombre;

    public Mecanico() {
    }

    public Mecanico(int pkMecanico, String nombre) {
        this.pkMecanico = pkMecanico;
        this.nombre = nombre;
    }

    public int getPkMecanico() {
        return pkMecanico;
    }

    public void setPkMecanico(int pkMecanico) {
        this.pkMecanico = pkMecanico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
