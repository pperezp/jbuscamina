package jbm2.model;

import java.awt.Point;

public class Vacio extends Espacio{

    public Vacio(Point punto) {
        super(punto);
    }
    
    @Override
    public String toString() {
//        return " - ("+(super.isDescubierto()?"t":"f")+")";
        if(super.isDescubierto()){
            return " - ";
        }else{
            return " x ";
        }
    }
}
