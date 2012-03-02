package archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricio
 */
public abstract class ArchivoDeConfiguracion extends Ejecutar{
    private File archivo;
    private String ruta;
    private String simboloComentario = "#";
    private String nombreArchivo;
    private String extension;

    public ArchivoDeConfiguracion(){}

    public void ConstruirArchivoExistente(String rutaDelArchivoExistente){
        ruta = rutaDelArchivoExistente;
        archivo = new File(rutaDelArchivoExistente);

        /*String[] r = ruta.split("\\\\");

        String[] r2 = r[r.length-1].split("\\.");

        nombreArchivo = r2[0];
        extension = r2[1];*/
    }

    public void ConstruirArchivo(String rutaDelArchivo, String nombreDelArchivo, String extensionDelArchivo){
        if(!rutaDelArchivo.endsWith("\\")){
            rutaDelArchivo += "\\";
        }

        ruta = rutaDelArchivo+nombreDelArchivo+"."+extensionDelArchivo;
        nombreArchivo = nombreDelArchivo;
        extension = extensionDelArchivo;

        crearCarpeta(rutaDelArchivo);
        archivo = new File(rutaDelArchivo+nombreDelArchivo+"."+extensionDelArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDeConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ConstruirArchivo(String rutaCompletaDelArchivo){
        String rutaDelArchivo = "";

        String[] r = rutaCompletaDelArchivo.split("\\\\");
        String[] r2 = r[r.length-1].split("\\.");

        for(int i=0;i<r.length-1;i++){
            rutaDelArchivo += r[i] + "\\";
        }


        ruta = rutaCompletaDelArchivo;
        nombreArchivo = r2[0];
        extension = r2[1];

        crearCarpeta(rutaDelArchivo);
        archivo = new File(rutaCompletaDelArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDeConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void setCampoDeConfiguracion(String campo, String valor){
        escribirEnArchivo(campo+"="+valor+";", false);
    }

    public void setComentario(String comentario){
        escribirEnArchivo(simboloComentario+comentario, false);
    }

    public synchronized void actualizarCampo(String campo, String valor){
        String[] texto;
        String[] lineasDelArchivo = new String[getCantidadDeLineas()];
        FileInputStream fis = null;
        BufferedReader reader = null;
        int i = 0;

        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith(simboloComentario)){
                    texto = linea.split("=");
                    if(texto[0].trim().equalsIgnoreCase(campo)){
                        texto[1] = ";";
                        texto[1] = texto[1].replaceAll(";", valor+";");
                        linea = texto[0]+"="+texto[1];
                    }
                    lineasDelArchivo[i] = linea;
                }else{
                    lineasDelArchivo[i] = linea;
                }
                i++;
            }

            for(i=0;i<lineasDelArchivo.length;i++){
                escribirEnArchivo(lineasDelArchivo[i], (i==0 ? true : false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    public synchronized void actualizarCampo(String campo, java.awt.Color obj){
        String[] texto;
        String[] lineasDelArchivo = new String[getCantidadDeLineas()];
        FileInputStream fis = null;
        BufferedReader reader = null;
        int i = 0;

        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith(simboloComentario)){
                    texto = linea.split("=");
                    if(texto[0].trim().equalsIgnoreCase(campo)){
                        texto[1] = ";";
                        texto[1] = texto[1].replaceAll(";", obj.getRGB()+";");
                        linea = texto[0]+"="+texto[1];
                    }
                    lineasDelArchivo[i] = linea;
                }else{
                    lineasDelArchivo[i] = linea;
                }
                i++;
            }

            for(i=0;i<lineasDelArchivo.length;i++){
                escribirEnArchivo(lineasDelArchivo[i], (i==0 ? true : false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    public void setRuta(String rutaDelArchivo){
        ruta = rutaDelArchivo;
    }

    public synchronized String getValorDeConfiguracion(String campo){
        String[] texto;
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith(simboloComentario)){
                    texto = linea.split("=");
                    if(texto[0].trim().equalsIgnoreCase(campo)){
                        return texto[1].replaceAll(";", "").trim();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchivoDeConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public synchronized void setSimboloDeComentario(String simboloAntiguo, String simboloNuevo){
        simboloComentario = simboloAntiguo;
        cambiarSimboloDeComentarioEnArchivo(simboloNuevo);
        simboloComentario = simboloNuevo;
    }

    public synchronized void setSimboloDeComentario(String simbolo){
        simboloComentario = simbolo;
    }

    public synchronized int getCantidadDeCampos(){
        FileInputStream fis = null;
        BufferedReader reader = null;
        int cont = 0;
        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith(simboloComentario)){
                    cont++;
                }
            }

            return cont;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    public synchronized void comentarCampo(String campo){
        String[] texto;
        String[] lineasDelArchivo = new String[getCantidadDeLineas()];
        FileInputStream fis = null;
        BufferedReader reader = null;
        int i = 0;

        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith(simboloComentario)){
                    texto = linea.split("=");
                    if(texto[0].trim().equalsIgnoreCase(campo)){
                        lineasDelArchivo[i] = simboloComentario+linea;
                    }else{
                        lineasDelArchivo[i] = linea;
                    }
                }else{
                    lineasDelArchivo[i] = linea;
                }
                i++;
            }

            
            for(i=0;i<lineasDelArchivo.length;i++){
                escribirEnArchivo(lineasDelArchivo[i], (i==0 ? true : false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    public synchronized void descomentarCampo(String campo){
        String[] texto;
        String[] lineasDelArchivo = new String[getCantidadDeLineas()];
        FileInputStream fis = null;
        BufferedReader reader = null;
        int i = 0;

        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(linea.startsWith(simboloComentario)){
                    texto = linea.split("=");
                    texto[0] = texto[0].trim().substring(1);
                    if(texto[0].trim().equalsIgnoreCase(campo)){
                        lineasDelArchivo[i] = linea.substring(1);
                    }else{
                        lineasDelArchivo[i] = linea;
                    }
                }else{
                    lineasDelArchivo[i] = linea;
                }
                i++;
            }

            for(i=0;i<lineasDelArchivo.length;i++){
                escribirEnArchivo(lineasDelArchivo[i], (i==0 ? true : false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    public String getNombreDeArchivo(){
        return this.nombreArchivo;
    }

    public String getExtension(){
        return this.extension;
    }

    private void escribirEnArchivo(String linea, boolean SobreEscribir) {
        try {
            PrintWriter archivoWriter = null;
            archivoWriter = new PrintWriter(new FileWriter(archivo, !SobreEscribir));
            archivoWriter.println(linea);
            if (archivoWriter != null) {
                archivoWriter.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDeConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void cambiarSimboloDeComentarioEnArchivo(String simboloNuevo) {
        String[] texto;
        String[] lineasDelArchivo = new String[getCantidadDeLineas()];
        FileInputStream fis = null;
        BufferedReader reader = null;
        int i = 0;

        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(linea.startsWith(simboloComentario)){
                    linea = linea.replaceFirst(simboloComentario, simboloNuevo);
                    lineasDelArchivo[i] = linea;
                }else{
                    lineasDelArchivo[i] = linea;
                }
                i++;
            }

            for(i=0;i<lineasDelArchivo.length;i++){
                escribirEnArchivo(lineasDelArchivo[i], (i==0 ? true : false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }

    private int getCantidadDeLineas() {
        FileInputStream fis = null;
        BufferedReader reader = null;
        int cont = 0;
        try {
            fis = new FileInputStream(ruta);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                cont++;
            }

            return cont;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                fis.close();
                reader.close();
            } catch (IOException ex) {
            }
        }
    }
}
