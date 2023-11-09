/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Clases;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author dam211
 */
public class Servicios_repara {

    private int pkSr;
    private int fkServicio;
    private int fkRepara;
    private int fkMecanico;
    private int tiempo;

    public Servicios_repara() {
    }

    public Servicios_repara(int pkSr, int fkServicio, int fkRepara, int fkMecanico, int tiempo) {
        this.pkSr = pkSr;
        this.fkServicio = fkServicio;
        this.fkRepara = fkRepara;
        this.fkMecanico = fkMecanico;
        this.tiempo = tiempo;
    }

    public int getPkSr() {
        return pkSr;
    }

    public void setPkSr(int pkSr) {
        this.pkSr = pkSr;
    }

    public int getFkServicio() {
        return fkServicio;
    }

    public void setFkServicio(int fkServicio) {
        this.fkServicio = fkServicio;
    }

    public int getFkRepara() {
        return fkRepara;
    }

    public void setFkRepara(int fkRepara) {
        this.fkRepara = fkRepara;
    }

    public int getFkMecanico() {
        return fkMecanico;
    }

    public void setFkMecanico(int fkMecanico) {
        this.fkMecanico = fkMecanico;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

}
