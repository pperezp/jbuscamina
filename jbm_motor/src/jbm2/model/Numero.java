package jbm2.model;

import java.awt.Point;

public class Numero extends Espacio{
    private int valor;

    public Numero(int valor, Point punto) {
        super(punto);
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
//        return " "+String.valueOf(valor)+" ("+(super.isDescubierto()?"t":"f")+")";
    
        if(super.isBandera()){
            return " B ";
        }else if(super.isDescubierto()){
            return " "+String.valueOf(valor)+" ";
        }else{
            return "   ";
        }
    }
    
    
    
    
    
}
