/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

import java.sql.Date;

/**
 *
 * @author dam211
 */
public class Reparacion {
    
    private int pkRepara;
    private int fkVehiculo;
    private Date fechaIni;
    private Date fechaFin;

    public Reparacion() {
    }

    public Reparacion(int pkReparacion, int fkVehiculo, Date fechaIni, Date fechaFin) {
        this.pkRepara = pkReparacion;
        this.fkVehiculo = fkVehiculo;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public int getPkReparacion() {
        return pkRepara;
    }

    public void setPkReparacion(int pkReparacion) {
        this.pkRepara = pkReparacion;
    }

    public int getFkVehiculo() {
        return fkVehiculo;
    }

    public void setFkVehiculo(int fkVehiculo) {
        this.fkVehiculo = fkVehiculo;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
    
}
