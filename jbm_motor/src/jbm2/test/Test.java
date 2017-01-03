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

        while (true) {
            x = scan.nextInt();
            y = scan.nextInt();

            t.jugar(new Point(x, y));
            t.print();
        }
    }

}
