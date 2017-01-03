package jbm2.test;

import java.awt.Point;
import jbm2.model.Tablero;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Tablero t = new Tablero(10, 10, 4);

        t.print();

        Scanner scan = new Scanner(System.in);

        int x, y;
        int bandera;
        boolean seguirJugando = false;

        while (!t.gano()) {
            System.out.print("x: ");
            
            x = scan.nextInt();
            System.out.print("y: ");
            y = scan.nextInt();

            System.out.print("Bandera? 1.-si 2.-no ");
            bandera = scan.nextInt();
            
            seguirJugando = t.jugar(new Point(x, y), bandera == 1);
            t.print();
            
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
