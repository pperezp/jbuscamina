package jbm2.model;

import java.awt.Point;

/**
 * Es un espacio en la cuadricula
 * @author prez
 */
public class Espacio {
    private Point punto;
    private boolean descubierto;
    private boolean bandera;

    public Espacio(Point punto) {
        this.punto = punto;
        this.descubierto = false;
        this.bandera = false;
    }
    
    public Point getPunto() {
        return punto;
    }

    public boolean isDescubierto() {
        return descubierto;
    }

    public void setDescubierto(boolean descubierto) {
        this.descubierto = descubierto;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    
    
    
}
