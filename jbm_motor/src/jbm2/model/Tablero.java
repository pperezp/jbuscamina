package jbm2.model;

import java.awt.Point;
import jbm2.model.util.Util;

public class Tablero {

    private Espacio[][] espacios;
    private int filas;
    private int columnas;

    public Tablero(int filas, int columnas, int cantidadDeBombas) {
        this.filas = filas;
        this.columnas = columnas;

        espacios = new Espacio[filas][columnas];

        initTablero();
        initBombas(cantidadDeBombas);
        initNumeros();
//        printBombas();
    }

    /**
     * Llena el tablero de espacios vacios
     */
    private void initTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                espacios[i][j] = new Vacio(new Point(i, j));
            }
        }
    }

    public void print() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(espacios[i][j].toString());
            }
            System.out.println();
        }
    }

    private boolean isBomba(Point p) {
        return espacios[p.x][p.y] instanceof Bomba;
    }

    /**
     *
     * @return El punto máximo. EJ: si la matriz es de 10x10, devuelve el punto
     * 9,9. Esto se utiliza para crear los numeros random. Al método
     * getPuntoRandom, le debo entregar el valor máximo posible del punto
     */
    private Point getPuntoMaximo() {
        return espacios[filas - 1][columnas - 1].getPunto();
    }

    private void initBombas(int bombas) {
        int cont = 0;

        Point puntoRandom;
        while (cont < bombas) {
            do {
                puntoRandom = Util.getPuntoRandom(getPuntoMaximo());
            } while (isBomba(puntoRandom));

            espacios[puntoRandom.x][puntoRandom.y] = new Bomba(puntoRandom);
            cont++;
        }
    }

    private void printBombas() {
        System.out.println("Bombas");
        for (int i = 0; i < espacios.length; i++) {
            for (int j = 0; j < espacios[i].length; j++) {
                if (espacios[i][j] instanceof Bomba) {
                    System.out.println(espacios[i][j].getPunto());
                }
            }
        }
    }

    private Espacio getBombasAlrededor(Vacio vacio) {
        int cont = 0;
        Point p = vacio.getPunto();

        for (int i = p.x - 1; i <= p.x + 1; i++) {
            for (int j = p.y - 1; j <= p.y + 1; j++) {
                try {
                    if (espacios[i][j] instanceof Bomba) {
                        cont++;
                    }
                } catch (Exception e) {
                }
            }
        }

        // si no hay bombas, es un espacio vacio
        if (cont == 0) {
            return new Vacio(p);
        } else {
            // en cambio si hay bombas, es un Numero
            return new Numero(cont, p);
        }
    }

    public static void main(String[] args) {
//        new Tablero(10,10,10).getBombasAlrededor(new Point(9,9));
    }

    private void initNumeros() {
        Vacio vacio;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // solo veo si hay bombas alrededor, cuando el espacio actual
                // es vacio.
                if (espacios[i][j] instanceof Vacio) {
                    vacio = (Vacio) espacios[i][j];
                    espacios[i][j] = getBombasAlrededor(vacio);
                }
            }
        }
    }
}
