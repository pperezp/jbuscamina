package clases;

import java.awt.event.MouseListener;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class BuscaMina {
    public static int MINA = -1;
    private static int MINA_ENCONTRADA = -2;
    private static int VACIO = 0;
    public static String NO_JUGADO = "nj";
    public static String BANDERA = "b";
    public static String SIGNO_PREGUNTA = "?";
    public static String JUGADO = "j";
    private static int JMAYOR;
    private static int IMAYOR;
    private static Random ran = new Random();
    private static int[][] tab;
    private static String[][] tab_aux;


    private static void inicializarBomba(int x, int y){
        if (x == 0 && y == 0) {
            if (getTab()[x][1] != MINA) {
                tab[x][1] += 1;
            }
            if (getTab()[x+1][0] != MINA) {
                tab[x+1][0] += 1;
            }
            if (getTab()[x+1][1] != MINA) {
                tab[x+1][1] += 1;
            }
        } else if (x == 0 && y == JMAYOR-1) {
            if (getTab()[x][JMAYOR-2] != MINA) {
                tab[x][JMAYOR-2] += 1;
            }
            if (getTab()[x+1][JMAYOR-2] != MINA) {
                tab[x+1][JMAYOR-2] += 1;
            }
            if (getTab()[x+1][JMAYOR-1] != MINA) {
                tab[x+1][JMAYOR-1] += 1;
            }
        } else if (x == IMAYOR-1 && y == 0) {
            if (getTab()[IMAYOR-2][0] != MINA) {
                tab[IMAYOR-2][0] += 1;
            }
            if (getTab()[IMAYOR-2][1] != MINA) {
                tab[IMAYOR-2][1] += 1;
            }
            if (getTab()[IMAYOR-1][1] != MINA) {
                tab[IMAYOR-1][1] += 1;
            }
        } else if (x == IMAYOR-1 && y == JMAYOR-1) {
            if (getTab()[IMAYOR-2][JMAYOR-1] != MINA) {
                tab[IMAYOR-2][JMAYOR-1] += 1;
            }
            if (getTab()[IMAYOR-2][JMAYOR-2] != MINA) {
                tab[IMAYOR-2][JMAYOR-2] += 1;
            }
            if (getTab()[IMAYOR-1][JMAYOR-2] != MINA) {
                tab[IMAYOR-1][JMAYOR-2] += 1;
            }
        } else if (x == 0) {
            for (int i = x; i <= (x+1); i++) {
                for (int j = (y - 1); j <= (y + 1); j++) {
                    if (i != x || j != y) {
                        if (getTab()[i][j] != MINA) {
                            tab[i][j] += 1;
                        }
                    }
                }
            }
        } else if (x == IMAYOR-1) {
            for (int i = (x - 1); i <= x; i++) {
                for (int j = (y - 1); j <= (y + 1); j++) {
                    if (i != x || j != y) {
                        if (getTab()[i][j] != MINA) {
                            tab[i][j] += 1;
                        }
                    }
                }
            }
        } else if (y == 0) {
            for (int i = (x-1); i <= (x+1); i++) {
                for (int j = y; j <= y+1; j++) {
                    if (i != x || j != y) {
                        if (getTab()[i][j] != MINA) {
                            tab[i][j] += 1;
                        }
                    }
                }
            }
        } else if (y == JMAYOR-1) {
            for (int i = (x-1); i <= (x+1); i++) {
                for (int j = (y - 1); j <= y; j++) {
                    if (i != x || j != y) {
                        if (getTab()[i][j] != MINA) {
                            tab[i][j] += 1;
                        }
                    }
                }
            }
        } else {
            for (int i = (x - 1); i <= (x + 1); i++) {
                for (int j = (y - 1); j <= (y + 1); j++) {
                    if (i != x || j != y) {
                        if (getTab()[i][j] != MINA) {
                            tab[i][j] += 1;
                        }
                    }
                }
            }
        }
    }


    public static Punto buscarMinas(){
        Punto p = new Punto();
        for(int i=0; i<getTab().length; i++){
            for(int j=0; j<getTab()[i].length; j++){
                if(getTab()[i][j] == MINA){
                    p.setX(i);
                    p.setY(j);
                    tab[i][j] = MINA_ENCONTRADA;
                    return p;
                }
            }
        }
        return null;
    }

    public static void deshabilitarLabels(javax.swing.JLabel[][] lbl){
        Punto p = new Punto();
        for(int i=0; i<getTab().length; i++){
            for(int j=0; j<getTab()[i].length; j++){
                MouseListener[] mouseListeners = lbl[i][j].getMouseListeners();
                for(int k = 0; k<mouseListeners.length; k++){
                    lbl[i][j].removeMouseListener(mouseListeners[k]);
                }
            }
        }
    }

    public static void imprimirTablero(){
        for(int i=0; i<getTab().length; i++){
            for(int j=0; j<getTab()[i].length; j++){
                System.out.print(" "+getTab()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void imprimirTableroAux(){
        for(int i=0; i<getTabVirtual().length; i++){
            for(int j=0; j<getTabVirtual()[i].length; j++){
                System.out.print(" "+getTabVirtual()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void inicializarTablero(int cantidadDeMinas, int filasTablero, int columnasTablero){
        setTab(new int[filasTablero][columnasTablero]);
        setTabVirtual(new String[filasTablero][columnasTablero]);
        IMAYOR = getTab().length;
        JMAYOR = getTab()[0].length;
        for(int i=0; i<getTab().length; i++){
            for(int j=0; j<getTab()[i].length; j++){
                tab[i][j] = VACIO;
                tab_aux[i][j] = NO_JUGADO;
            }
        }
        int c = 0, x, y;
        while(c<cantidadDeMinas){
            x = ran.nextInt(IMAYOR);
            y = ran.nextInt(JMAYOR);
            if(getTab()[x][y] != MINA){
                setBomba(x,y);
                c++;
            }
        }
    }

    private static void setBomba(int x, int y){
        tab[x][y] = MINA;
        inicializarBomba(x, y);
    }

    public static int getPosicion(int x, int y){
        return getTab()[x][y];
    }

    /**
     * @return the tab
     */
    public static int[][] getTab() {
        return tab;
    }

    public static String getNombreIcono(String rutaCompletaIcono) {
        String[] ruta = rutaCompletaIcono.split("/");
        String nombre = ruta[ruta.length-1].replaceAll(".PNG", "");
        return nombre;
    }

    public static void setPosicionTabAux(int i, int j, String valor){
        tab_aux[i][j] = valor;
    }

    /**
     * @return the tab_aux
     */
    public static String[][] getTabVirtual() {
        return tab_aux;
    }

    /**
     * @param aTab the tab to set
     */
    public static void setTab(int[][] aTab) {
        tab = aTab;
    }

    /**
     * @param aTab_aux the tab_aux to set
     */
    public static void setTabVirtual(String[][] aTab_aux) {
        tab_aux = aTab_aux;
    }
}
