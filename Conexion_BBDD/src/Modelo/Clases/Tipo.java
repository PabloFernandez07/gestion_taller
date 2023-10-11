/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

/**
 *
 * @author Pablo
 */
public class Tipo {
    
    private int pkTipo;
    private String nombre;

    public Tipo() {
    }

    public Tipo(int pkTipo, String nombre) {
        this.pkTipo = pkTipo;
        this.nombre = nombre;
    }

    public int getPkTipo() {
        return pkTipo;
    }

    public void setPkTipo(int pkTipo) {
        this.pkTipo = pkTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
