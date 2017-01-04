package jbm2.model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import jbm2.model.util.Util;

public class Tablero {

    private final Espacio[][] cuadrante;
    private final int filas;
    private final int columnas;

    public Tablero(int filas, int columnas, int cantidadDeBombas) {
        this.filas = filas;
        this.columnas = columnas;

        cuadrante = new Espacio[filas][columnas];

        initTablero();
        initBombas(cantidadDeBombas);
        initNumeros();
//        printBombas();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    
    

    /**
     * Llena el tablero de cuadrante vacios
     */
    private void initTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                cuadrante[i][j] = new Vacio(new Point(i, j));
            }
        }
    }

    public void print() {
        
        System.out.print("   ");
        
        for(int i=0; i<columnas; i++){
            System.out.print(" "+i+" ");
        }
        
        System.out.println();
        
        for (int i = 0; i < filas; i++) {
            System.out.print(" "+i+" ");
            for (int j = 0; j < columnas; j++) {
                System.out.print(cuadrante[i][j].toString());
            }
            System.out.println();
        }
    }

    private boolean isBomba(Point p) {
        return cuadrante[p.x][p.y] instanceof Bomba;
    }
    
    public boolean isBandera(Point p){
        return cuadrante[p.x][p.y].isBandera();
    }

    public boolean isVacio(Point p){
        return cuadrante[p.x][p.y] instanceof Vacio;
    }
    
    public boolean isDescubierto(Point p){
        return cuadrante[p.x][p.y].isDescubierto();
    }
    /**
     *
     * @return El punto máximo. EJ: si la matriz es de 10x10, devuelve el punto
     * 9,9. Esto se utiliza para crear los numeros random. Al método
     * getPuntoRandom, le debo entregar el valor máximo posible del punto
     */
    private Point getPuntoMaximo() {
        return cuadrante[filas - 1][columnas - 1].getPunto();
    }

    private void initBombas(int bombas) {
        int cont = 0;

        Point puntoRandom;
        while (cont < bombas) {
            do {
                puntoRandom = Util.getPuntoRandom(getPuntoMaximo());
            } while (isBomba(puntoRandom));

            cuadrante[puntoRandom.x][puntoRandom.y] = new Bomba(puntoRandom);
            cont++;
        }
    }

    private void printBombas() {
        System.out.println("Bombas");
        for (int i = 0; i < cuadrante.length; i++) {
            for (int j = 0; j < cuadrante[i].length; j++) {
                if (cuadrante[i][j] instanceof Bomba) {
                    System.out.println(cuadrante[i][j].getPunto());
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
                    if (cuadrante[i][j] instanceof Bomba) {
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

    public Espacio[][] getCuadrante() {
        return cuadrante;
    }
    
    public Espacio getEspacio(Point p){
        return cuadrante[p.x][p.y];
    }

    private void initNumeros() {
        Vacio vacio;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // solo veo si hay bombas alrededor, cuando el espacio actual
                // es vacio.
                if (cuadrante[i][j] instanceof Vacio) {
                    vacio = (Vacio) cuadrante[i][j];
                    cuadrante[i][j] = getBombasAlrededor(vacio);
                }
            }
        }
    }

    /**
     *
     * @param p
     * @param jugadaBandera si la jugada NO es bandera, el objeto es null
     * @return retorno true si puede seguir jugando y false si no
     */
    public boolean jugar(Point p, JugadaBandera jugadaBandera) {
        Espacio actual = cuadrante[p.x][p.y];
        
        if (jugadaBandera == null) {
            actual.setDescubierto(true);
            if (actual instanceof Bomba) {
                return false;
            } else if (actual instanceof Vacio) {
                // aca ver como recorrer los cuadrante para cambiarlos
                descubrir(p);
            }
        }else{
            actual.setBandera(jugadaBandera.isPonerBandera());
        }
        return true;
    }
    
//    private void ponerBandera(Point p){
//        Espacio actual = cuadrante[p.x][p.y];
//        actual.setBandera(true);
//    }
//    
//    private void sacarBandera(Point p){
//        Espacio actual = cuadrante[p.x][p.y];
//        actual.setBandera(false);
//    }

    private void descubrir(Point p) {
        Espacio actual;
        List<Vacio> vacios = new ArrayList<>();

        /*Acá recorro el alrededor del punto (2 ciclos)*/
        for (int i = p.x - 1; i <= p.x + 1; i++) {
            for (int j = p.y - 1; j <= p.y + 1; j++) {
                try {
                    actual = cuadrante[i][j];

                    // si no está descubierto...
                    if (!actual.isDescubierto()) {
                        // lo descubro
                        actual.setDescubierto(true);

                        if (actual instanceof Vacio) {
                            vacios.add((Vacio) actual);
                        }
                    }

                } catch (Exception e) {
                }
            }
        }

        /*Si existen vacios, lo debo recorrer de forma recursiva*/
        if (!vacios.isEmpty()) {
            for (Vacio vacio : vacios) {
                descubrir(vacio.getPunto());
            }
        }
    }

    public boolean gano() {
        Espacio actual;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                actual = cuadrante[i][j];
                
                if(actual instanceof Bomba && !actual.isBandera()){
                    return false;
                }
            }
        }
        
        return true;
    }
}
