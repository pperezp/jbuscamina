/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archivo;

/**
 *
 * @author 16828943-k
 */

public class ArchivoExistente extends ArchivoDeConfiguracion{
    /**
     *
     * @param rutaDelArchivoExistente<br>
     * Es la ruta del Archivo de configración existente, el cual quiere trabajar<br>
     * si el archivo de configuracion no existe, se creará, no asi la carpeta contenedora<br>
     * <b>Ejemplo: <br>"c:\\carpeta\\arhivo.config"</b>
     * recuerde poner 2 blackslash<br><br>
     */
    public ArchivoExistente(String rutaDelArchivoExistente){
        super.ConstruirArchivoExistente(rutaDelArchivoExistente);
    }
}
