/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archivo;

import clases.Fecha;
import clases.Hora;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patricio
 */
public class Guardar {
    private String ruta;
    private String[] extension;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter[] filtros;
    private File archivo;

    public Guardar(){
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
     * en guardar un archivo.<br>
     * Escriba los filtros de extensiones separados de una coma<br>
     * <p><b>Ejemplo:</b> exe,jpeg,bat</p>
     *
     * Si como parámetro le entregas null a extensiones, se podrán abrir todos los tipos,
     * en otras palabras no habrá filtros
     *
     *
     * @return Retornará TRUE si el usuario guarda algun archivo y
     * Retornará FALSE si el usuario no guarda algún archivo o
     * ocurrio un error
     */
    public boolean guardarComo(String nombreDeArchivo, String extensiones, String textoDeBotonDeAprovacion, String rutaDirectorioPorDefecto){
        if(extensiones != null){
            String[] ext = extensiones.split(",");
            sacarEspacios(ext);
            setExtension(ext);
        }

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setSelectedFile(new File(nombreDeArchivo));
        fileChooser.setCurrentDirectory(new File(rutaDirectorioPorDefecto));
        return guardar(textoDeBotonDeAprovacion);
    }

    public String getRuta(){
        return ruta;
    }

    private void sacarEspacios(String[] ext) {
        for(int i=0;i<ext.length;i++){
            ext[i] = ext[i].trim();
        }
    }

    private boolean guardar(String textoDeBotonDeAprovacion) {
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                ruta = archivo.getPath() + fileChooser.getFileFilter().getDescription().substring(1);
                if (new File(ruta).exists()) {//si el archivo existe, llamo a la misma funcion, recursivamente
                    mensajes.Mensajes.mensajeWarning("Archivo ya Existente", "El archivo ya existe. Cambie el nombre del Archivo");
                    return guardar(textoDeBotonDeAprovacion);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
