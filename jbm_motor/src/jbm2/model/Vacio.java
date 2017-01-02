package jbm2.model;

import java.awt.Point;

public class Vacio extends Espacio{

    public Vacio(Point punto) {
        super(punto);
    }
    
    @Override
    public String toString() {
        return " - ";
    }
}
