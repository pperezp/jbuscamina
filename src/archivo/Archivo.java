/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiola
 */
public class Archivo {

    private static File archivo;


    public static void setArchivoSoloLectura(String rutaArchivo){
        archivo = new File(rutaArchivo);
        archivo.setReadOnly();
    }

    public static void escribirEnAchivo(String nombreArchivo, String texto, boolean sobreEscribir)
            throws IOException {
        archivo = new File(nombreArchivo);
        archivo.createNewFile();

        
        PrintWriter archivoWriter = null;
        archivoWriter = new PrintWriter(new FileWriter(archivo, !sobreEscribir));
        archivoWriter.println(texto);
        if(archivoWriter != null) archivoWriter.close();
    }
    public static boolean existeArchivo(String ruta){
        archivo = new File(ruta);
        return archivo.isFile();
    }
    public static long getFileSize(String ruta){
        archivo = new File(ruta);
        if(archivo.isFile()){
            return archivo.length();
        }
        return 0;
    }

    /**
     * deve permanecer en la mismas ruta del jar
     * @param nombreArchivo
     */
    public static String leerArchivo(String nombreArchivo) {
        String texto = "";
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(nombreArchivo);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
               texto += linea + "\n";
            }
            return texto;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return texto;
    }
    

}



    
