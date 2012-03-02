/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases; //OJO CAMBIAR PAKETE

/**
 *
 * @author Cristian Estay Valenzuela
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
public class EmptyFinder {//gringo ql... xD
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
     * Un espacio no recorrido es un espacio en donde no se conoce su valor
     */
    public static final int ESPACIO_NO_RECORRIDO = -7;

    //<Limites Matriz>
    private static int limite_horizontal;
    private static int limite_vertical;
    //</Limites Matriz>

    private static int[][] espacios_vacios; // Matriz de numeros(ESPACIO_VACIO,NUMERO_BORDE,ESPACIO_NO_RECORRIDO)

    /**
     * Nota: <b>los ejes comienzan en la esquina superior derecha » tablero[Y][X] (tablero[0][0])</b>
     * @param tablero matriz de un tablero del tipo int en donde se encuentra una partida de buscaminas
     * @param x eje horizontal
     * @param y eje vertical
     * @return espacios_vacios matriz con el reconocimiento de espacios vacios
     * ,numeros de borde y espacios no reconozidos
     *
     */
    public static int[][] getEspaciosVacios(int[][] tablero,int x,int y){
        setLimites(tablero);
        initEspaciosvacios();
        buscarEspaciosVacios(tablero,x,y);
        return espacios_vacios;
    }

    private static void setLimites(int[][] tablero) { // determina el tamaño de la matriz recivida
        limite_horizontal = tablero[0].length;
        limite_vertical = tablero.length;

    }

    private static void initEspaciosvacios(){ // inicializa la matriz y le da un tamaño igual a la matriz recivida
        espacios_vacios = new int[limite_vertical][limite_horizontal];
        for(int i = 0 ; i < limite_vertical ; i++){
            for(int j = 0; j < limite_horizontal ; j++){
                espacios_vacios[i][j] = ESPACIO_NO_RECORRIDO;
            }
        }
    }

    private static void buscarEspaciosVacios(int[][] tablero, int xx, int yy) { //busca recursivamente los espacios vacios y limites de numeros de estos
        
        if ((yy < (limite_vertical ) && xx < (limite_horizontal ))&& (yy >= 0 && xx >= 0)) {
            if ((tablero[yy][xx] == 0) && (espacios_vacios[yy][xx] == ESPACIO_NO_RECORRIDO)) {
                espacios_vacios[yy][xx] = ESPACIO_VACIO;
                buscarEspaciosVacios(tablero, xx - 1, yy - 1);
                buscarEspaciosVacios(tablero, xx    , yy - 1);
                buscarEspaciosVacios(tablero, xx + 1, yy - 1);
                buscarEspaciosVacios(tablero, xx - 1, yy    );
                buscarEspaciosVacios(tablero, xx + 1, yy    );
                buscarEspaciosVacios(tablero, xx - 1, yy + 1);
                buscarEspaciosVacios(tablero, xx    , yy + 1);
                buscarEspaciosVacios(tablero, xx + 1, yy + 1);
//                System.gc();
            } else if ((tablero[yy][xx] >= 1 && tablero[yy][xx] <= 8)
                    && (espacios_vacios[yy][xx] == ESPACIO_NO_RECORRIDO)
                           && espacios_vacios[yy][xx] != ESPACIO_VACIO){

                espacios_vacios[yy][xx] = NUMERO_BORDE;
            }
        }
    }
}
