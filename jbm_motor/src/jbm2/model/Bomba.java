package jbm2.model;

import java.awt.Point;

public class Bomba extends Espacio{

    public Bomba(Point punto) {
        super(punto);
    }
    
    @Override
    public String toString() {
//        return " B ("+(super.isDescubierto()?"t":"f")+")";
        if(super.isDescubierto()){
            return " B ";
        }else{
            return " x ";
        }
    }
}
