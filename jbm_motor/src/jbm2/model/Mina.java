package jbm2.model;

import java.awt.Point;

public class Mina extends Espacio{

    public Mina(Point punto) {
        super(punto);
    }
    
    @Override
    public String toString() {
//        return " B ("+(super.isDescubierto()?"t":"f")+")";
        if(super.isDescubierto()){
            return " X ";
        }else if(super.isBandera()){
            return " M ";
        }else{
            return "   ";
        }
    }
}
