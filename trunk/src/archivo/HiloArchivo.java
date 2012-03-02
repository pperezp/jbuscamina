/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archivo;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiola
 */
public class HiloArchivo extends Thread{
    private String ruta;
    private File ar;
    private String[][] valores;

    public HiloArchivo(String ruta, String[][] valoresDeConfiguracion){
        this.ruta = ruta;
        valores = valoresDeConfiguracion;
        ar = new File(ruta);
    }

    @Override
    public void run(){
        while(true){
            try {
                if (!ar.exists()) {
                    ArchivoDeConfiguracion a = new ArchivoNoExistente(ruta);
                    for(int i=0;i<valores.length;i++){
                        a.setCampoDeConfiguracion(valores[i][0], valores[i][1]);
                    }
                    a = null;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
