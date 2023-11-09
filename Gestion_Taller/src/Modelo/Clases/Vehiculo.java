/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

/**
 *
 * @author dam211
 */
public class Vehiculo {

    private int pkVehiculo;
    private int fkTipo;
    private String matricula;
    private String modelo;
    private int fkCliente;

    public Vehiculo() {
    }

    public Vehiculo(int pkVehiculo, int fkTipo, String matricula, String modelo, int fkCliente) {
        this.pkVehiculo = pkVehiculo;
        this.fkTipo = fkTipo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.fkCliente = fkCliente;
    }

    public int getPkVehiculo() {
        return pkVehiculo;
    }

    public void setPkVehiculo(int pkVehiculo) {
        this.pkVehiculo = pkVehiculo;
    }

    public int getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(int fkCliente) {
        this.fkCliente = fkCliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(int fkTipo) {
        this.fkTipo = fkTipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    


}
