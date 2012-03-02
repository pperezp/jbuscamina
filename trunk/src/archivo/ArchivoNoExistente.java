/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archivo;

/**
 *
 * @author 16828943-k
 */

public class ArchivoNoExistente extends ArchivoDeConfiguracion{
    /**
     *
     * @param rutaDelArchivo<br>
     * Es la ruta del Archivo en la cual usted quiere que se cree el nuevo Archivo de configuracion<br>
     * si la carpeta no existe, se creará<br>
     * <b>Ejemplo: <br>"c:\\carpeta\\"</b>
     * recuerde poner 2 blackslash<br><br>
     * @param nombreDelArchivo<br>
     * Es el nombre del nuevo archivo de configuracion<br><br>
     * @param extensionDelArchivo<br>
     * Es la extension del nuevo archivo de configuracion
     * <br><br>
     * <b>Ejemplo de Construccion: </b><br>
     * ArchivoDeConfiguracion ar = <br>new ArchivoNoExistente("c:\\carpeta\\","archivo","config");<br><br>
     * Asi se creará el siguiente archivo: <b>"c:\carpeta\archivo.config"</b>
     */
    public ArchivoNoExistente(String rutaDelArchivo, String nombreDelArchivo, String extensionDelArchivo){
        super.ConstruirArchivo(rutaDelArchivo, nombreDelArchivo, extensionDelArchivo);
    }

    public ArchivoNoExistente(String rutaCompletaDelArchivo){
        super.ConstruirArchivo(rutaCompletaDelArchivo);
    }

}
