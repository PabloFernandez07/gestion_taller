/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

/**
 *
 * @author dam211
 */
public class Servicio {
    
    private int pkServicio;
    private String nombre;
    private float precio;

    public Servicio() {
    }

    public Servicio(int pkServicio, String nombre, float precio) {
        this.pkServicio = pkServicio;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getPkServicio() {
        return pkServicio;
    }

    public void setPkServicio(int pkServicio) {
        this.pkServicio = pkServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
    
}
