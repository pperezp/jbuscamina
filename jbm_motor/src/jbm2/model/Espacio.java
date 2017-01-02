package jbm2.model;

import java.awt.Point;

public class Espacio {
    private Point punto;
    private boolean descubierto;

    public Espacio(Point punto) {
        this.punto = punto;
        this.descubierto = false;
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
    
}
