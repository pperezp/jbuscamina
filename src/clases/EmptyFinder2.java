/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases; //OJO CAMBIAR PAKETE

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cristian Estay Valenzuela & Fidel Castro Silva
 *
 * provado y funcional en la matriz:
 * int[][] a ={  {5,6,1,0,-1,0,0},
 *               {1,2,2,0,7,3,3},
 *               {3,0,0,0,-1,3,3},
 *               {1,0,0,0,5,3,0},
 *               {2,2,3,0,0,0,0},
 *               {2,2,0,0,0,3,3},
 *               {2,4,0,0,7,0,0}
 *            };
 * ya que no tenia como generar tableros >=(
 */
public class EmptyFinder2 {//gringo ql... xD
    /**
     * Un espacio vacio se define como un espacio en donde <b>no</b>
     * se encuentran numeros o minas (del juego)
     */
    public static final int ESPACIO_VACIO        = -5;
    /**
     * Un numero de borde se define como el numero que se encuentra
     * en la periferia de un espacio vacio
     */
    public static final int NUMERO_BORDE         = -6;
    /**
     * Un espacio no recorrido es un espacio en donde no se conose su valor
     */
    public static final int ESPACIO_NO_RECORRIDO = -7;

    //<Limites Matriz>
    private static int limite_horizontal;
    private static int limite_vertical;
    //</Limites Matriz>

    private static int[][] espacios_vacios; // Matriz de numeros(ESPACIO_VACIO,NUMERO_BORDE,ESPACIO_NO_RECORRIDO)
    private static int[][] tablero;//el tablero weon
    public static long count;//para contar las ejecuciones del metodo, nada más, se puede borrar sin problemas
    private static long countLimit2; //wea para contar veces que se ejecuta el metodo hasta llegar al limite y entonces vuelve a cero
    private static long recursionLimit2; //wea para limitar el número de ejecuciones, antes de iniciar un nuevo thread

    /**
     * Nota: <b>los ejes comienzan en la esquina superior derecha » tablero[Y][X] (tablero[0][0])</b>
     * @param tablero matriz de un tablero del tipo int en donde se encuentra una partida de buscaminas
     * @param x eje vertical
     * @param y eje horizontal
     * @return espacios_vacios matriz con el reconocimiento de espacios vacios
     * ,numeros de borde y espacios no reconozidos
     *
     */
    public static int[][] getEspaciosVacios(int[][] tablero,int x,int y,int limite_de_recursion) throws InterruptedException{
        count=0;//contador a 0
        countLimit2=0;//contador a 0
        recursionLimit2=limite_de_recursion;//asignar limite
        EmptyFinder2.tablero=tablero;//asignar tablero
        setLimites(tablero);
        initEspaciosvacios();
        buscarEspaciosVacios1(x,y);
        return espacios_vacios;
    }

    private static void setLimites(int[][] tablero) { // determina el tamaño de la matriz recivida
        limite_horizontal = tablero[0].length;
        limite_vertical = tablero.length;
        //System.out.println(limite_horizontal);
        //System.out.println(limite_vertical);
    }

    private static void initEspaciosvacios(){ // inicializa la matriz y le da un tamaño igual a la matriz recivida
        espacios_vacios = new int[limite_vertical][limite_horizontal];
        for(int i = 0 ; i < limite_vertical ; i++){
            for(int j = 0; j < limite_horizontal ; j++){
                espacios_vacios[i][j] = ESPACIO_NO_RECORRIDO;
            }
        }
    }

    private static void buscarEspaciosVacios1(int x, int y) throws InterruptedException { //busca recursivamente los espacios vacios y limites de numeros de estos
        buscarEspacios b = new buscarEspacios(x, y);//nuevo thread
        b.start();//arrancar
        b.join();//esperar al termino, para continuar
    }

private static class buscarEspacios extends Thread {
    private int x; // the x
    private int y; // the y
    private static buscarEspacios a;

    public buscarEspacios(int x, int y){
    this.x=x;
    //System.out.println("x="+x);
    this.y=y;
    //System.out.println("y="+y);
    }

    private static void crearIniciarYEsperarProcesos(int valX,int valY) throws InterruptedException{
    a = new buscarEspacios(valX, valY);//nuevo thread
    a.start();//arrancar
    a.join();//esperar al termino, para continuar
    }

    private static void buscarEspaciosVacios( int x, int y) throws InterruptedException { //busca recursivamente los espacios vacios y limites de numeros de estos
        count++;
        //System.out.println(countLimit2+" "+recursionLimit2);
        if(countLimit2>=recursionLimit2){countLimit2=0;crearIniciarYEsperarProcesos( x, y);}
        else{
        countLimit2++;
        if ((y < (limite_vertical ) && x < (limite_horizontal ))&& (y >= 0 && x >= 0)) {
            if ((tablero[y][x] == 0) && (espacios_vacios[y][x] == ESPACIO_NO_RECORRIDO)) {
                espacios_vacios[y][x] = ESPACIO_VACIO;
                if ((y-1 < (limite_vertical ) && x-1 < (limite_horizontal ))&& (y-1 >= 0 && x-1 >= 0))if(tablero[y-1][x-1]==0&& (espacios_vacios[y-1][x-1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x - 1, y - 1);
                else if ((tablero[y-1][x-1] >= 1 && tablero[y-1][x-1] <= 8)&& (espacios_vacios[y-1][x-1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y-1][x-1] != ESPACIO_VACIO)espacios_vacios[y-1][x-1] = NUMERO_BORDE;
                if ((y-1 < (limite_vertical ) && x < (limite_horizontal ))&& (y-1 >= 0 && x >= 0))if(tablero[y-1][x]==0&& (espacios_vacios[y-1][x] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x    , y - 1);
                else if ((tablero[y-1][x] >= 1 && tablero[y-1][x] <= 8)&& (espacios_vacios[y-1][x] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y-1][x] != ESPACIO_VACIO)espacios_vacios[y-1][x] = NUMERO_BORDE;
                if ((y-1 < (limite_vertical ) && x+1 < (limite_horizontal ))&& (y-1 >= 0 && x+1 >= 0))if(tablero[y-1][x+1]==0&& (espacios_vacios[y-1][x+1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x + 1, y - 1);
                else if ((tablero[y-1][x+1] >= 1 && tablero[y-1][x+1] <= 8)&& (espacios_vacios[y-1][x+1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y-1][x+1] != ESPACIO_VACIO)espacios_vacios[y-1][x+1] = NUMERO_BORDE;
                if ((y < (limite_vertical ) && x-1 < (limite_horizontal ))&& (y >= 0 && x -1>= 0))if(tablero[y][x-1]==0&& (espacios_vacios[y][x-1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x - 1, y    );
                else if ((tablero[y][x-1] >= 1 && tablero[y][x-1] <= 8)&& (espacios_vacios[y][x-1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y][x-1] != ESPACIO_VACIO)espacios_vacios[y][x-1] = NUMERO_BORDE;
                if ((y < (limite_vertical ) && x+1 < (limite_horizontal ))&& (y >= 0 && x+1 >= 0))if(tablero[y][x+1]==0&& (espacios_vacios[y][x+1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x + 1, y    );
                else if ((tablero[y][x+1] >= 1 && tablero[y][x+1] <= 8)&& (espacios_vacios[y][x+1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y][x+1] != ESPACIO_VACIO)espacios_vacios[y][x+1] = NUMERO_BORDE;
                if ((y+1 < (limite_vertical ) && x-1 < (limite_horizontal ))&& (y+1 >= 0 && x-1 >= 0))if(tablero[y+1][x-1]==0&& (espacios_vacios[y+1][x-1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x - 1, y + 1);
                else if ((tablero[y+1][x-1] >= 1 && tablero[y+1][x-1] <= 8)&& (espacios_vacios[y+1][x-1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y+1][x-1] != ESPACIO_VACIO)espacios_vacios[y+1][x-1] = NUMERO_BORDE;
                if ((y+1 < (limite_vertical ) && x < (limite_horizontal ))&& (y+1 >= 0 && x >= 0))if(tablero[y+1][x]==0&& (espacios_vacios[y+1][x] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x    , y + 1);
                else if ((tablero[y+1][x] >= 1 && tablero[y+1][x] <= 8)&& (espacios_vacios[y+1][x] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y+1][x] != ESPACIO_VACIO)espacios_vacios[y+1][x] = NUMERO_BORDE;
                if ((y+1 < (limite_vertical ) && x+1 < (limite_horizontal ))&& (y+1 >= 0 && x+1 >= 0))if(tablero[y+1][x+1]==0&& (espacios_vacios[y+1][x+1] == ESPACIO_NO_RECORRIDO))
                    buscarEspaciosVacios(x + 1, y + 1);
                else if ((tablero[y+1][x+1] >= 1 && tablero[y+1][x+1] <= 8)&& (espacios_vacios[y+1][x+1] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y+1][x+1] != ESPACIO_VACIO)espacios_vacios[y+1][x+1] = NUMERO_BORDE;
            } else if ((tablero[y][x] >= 1 && tablero[y][x] <= 8)&& (espacios_vacios[y][x] == ESPACIO_NO_RECORRIDO)&& espacios_vacios[y][x] != ESPACIO_VACIO)espacios_vacios[y][x] = NUMERO_BORDE;
        }
      }
    }
    public void run() {
            try {
                buscarEspaciosVacios(x, y);
            } catch (InterruptedException ex) {
                Logger.getLogger(EmptyFinder2.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
}
