/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import javax.swing.JDialog;

/**
 *
 * @author Fabiola
 */
public class Redimensionar {

    public static void redimensionarFormulario(javax.swing.JFrame form, boolean redimensionable, String titulo){
        form.setVisible(false);
        form.setBounds(form.getX(),form.getY(),(int)form.getPreferredSize().getWidth()-2,(int)form.getPreferredSize().getHeight()-2);
        form.setLocationRelativeTo(null);
        form.setResizable(redimensionable);
        form.setTitle(titulo);
        form.setVisible(true);
        form.repaint();
    }

    public static void redimensionarFormulario(JDialog dia, boolean redimensionable, String titulo) {
        dia.setBounds(dia.getX(),dia.getY(),(int)dia.getPreferredSize().getWidth()+250,(int)dia.getPreferredSize().getHeight()-120);
        dia.setLocationRelativeTo(null);
        dia.setResizable(redimensionable);
        dia.setTitle(titulo);
        dia.setVisible(true);
    }
}
