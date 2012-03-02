/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patricio
 */
public class Abrir {
    private String ruta;
    private String[] extension;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter[] filtros;
    private File archivo;
    private BufferedReader reader;

    public Abrir(){
        fileChooser = new JFileChooser();
    }

    private void setExtension(String[] extensiones){
        extension = new String[extensiones.length];
        extension = extensiones;
        escribirExtensiones();
    }

    private void escribirExtensiones(){
        filtros = new FileNameExtensionFilter[extension.length];
        for(int i=0;i<filtros.length;i++){
            filtros[i] = new FileNameExtensionFilter("*."+extension[i], extension[i]);
        }
        agregarFiltros();
    }

    private void agregarFiltros(){
        for(int i=0;i<filtros.length;i++){
            fileChooser.addChoosableFileFilter(filtros[i]);
        }
    }

    /**
     *
     * @param extensiones
     * Las extensiones son los Filtros con los cuales se basará
     * el abridor al abrir un archivo.<br>
     * Escriba los filtros de extensiones separados de una coma<br>
     * <p><b>Ejemplo:</b> exe,jpeg,bat</p>
     *
     * Si como parámetro le entregas null, se podrán abrir todos los tipos,
     * en otras palabras no habrá filtros
     *
     * @return Retornará TRUE si el usuario abre algun archivo y
     * Retornará FALSE si el usuario no abre algún archivo o
     * ocurrio un error
     */
    public boolean abrir(String extensiones, String textoDeBotonDeAprovacion, String rutaDirectorioPorDefecto){
        if(extensiones != null){
            String[] ext = extensiones.split(",");
            sacarEspacios(ext);
            setExtension(ext);
        }

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(rutaDirectorioPorDefecto));
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try{
            if (seleccion == JFileChooser.APPROVE_OPTION){
               archivo = fileChooser.getSelectedFile();
               ruta = archivo.getPath();
               reader = new BufferedReader(new FileReader(archivo));
               return true;
            }else{
                return false;
            }
        }catch(IOException e){
            return false;
        }
    }

    public String getRuta(){
        return ruta;
    }

    private void sacarEspacios(String[] ext) {
        for(int i=0;i<ext.length;i++){
            ext[i] = ext[i].trim();
        }
    }

    /**
     *
     * @return
     * Retornará NULL si hay algún Error o el archivo esta Vacío
     */
    public String getArchivoComoString(){
        String archiv = "";
        try {
            reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            while (linea != null) {
                archiv += linea + "\n";
                linea = reader.readLine();
            }

            if(archiv.equalsIgnoreCase("")){
                return null;
            }else{
                return archiv;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
