package jbm2.test;

import java.awt.Point;
import jbm2.model.Tablero;
import java.util.Scanner;
import jbm2.model.JugadaBandera;

public class Test {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero(10, 10, 4);

        tablero.print();

        Point punto = new Point();
        int bandera;
        boolean seguirJugando = false;

        while (!tablero.gano()) {
            System.out.print("x: ");
            punto.x = scan.nextInt();
            System.out.print("y: ");
            punto.y = scan.nextInt();
            
            System.out.print("Bandera? 1.-si 2.-no ");
            bandera = scan.nextInt();
            
            JugadaBandera jb = null;
            if(bandera == 1){
                // si quiero jugar bandera, switcheo el estado de la bandera
                jb = new JugadaBandera(!tablero.isBandera(punto));
            }
            
            seguirJugando = tablero.jugar(punto, jb);
            tablero.print();
            
            if(!seguirJugando){
                System.out.println("Perdiste!");
                break;
            }
            
            
        }
        
        if(seguirJugando){
            System.out.println("Gano!");
        }
    }

}
