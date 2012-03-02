/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package encriptacion;

/**
 *
 * @author 16828943-k
 */
public class Encriptacion {
    public static String encriptarTexto(String texto){
        byte[] enc = texto.getBytes();
        String codificado = "";
        for(int i=0; i<enc.length;i++){
            codificado += Integer.toOctalString(Integer.parseInt(Byte.toString(enc[i]))*(i % 2 == 0 ? 2 : 4))+"8";
        }
        return codificado;
    }

    public static String desencriptar(String texto){
        String[] tex = texto.split("8");
        char[] car;
        String t = "";
        for(int i=0; i<tex.length;i++){
            car = Character.toChars(Integer.parseInt(tex[i], 8)/(i % 2 == 0 ? 2 : 4));
            if(car[0] == ' '){
                t+=" ";
            }else{
                t+=car[0];
            }
        }
        return t;
    }
}
