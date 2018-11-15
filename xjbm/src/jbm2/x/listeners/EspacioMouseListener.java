package jbm2.x.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import jbm2.model.JugadaBandera;
import jbm2.x.XJbm2;

public class EspacioMouseListener implements MouseListener {

    private final JuegoListener juegoListener;

    public EspacioMouseListener(JuegoListener jugoListener) {
        this.juegoListener = jugoListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (XJbm2.habilitado) {
            if(XJbm2.isStandBy()){
                juegoListener.iniciarPartida();
            }
            
            JLabel lbl = (JLabel) e.getComponent();
            JugadaBandera jugadaBandera = null;
            int x, y;
            boolean jugadaValida = true;

            x = Integer.parseInt(lbl.getName().split("-")[0]);
            y = Integer.parseInt(lbl.getName().split("-")[1]);
            Point p = new Point(x, y);

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (XJbm2.juego.isDescubierto(p)) {
                    jugadaValida = false;
                } else {
                    boolean isBandera = XJbm2.juego.isBandera(p);
                    jugadaBandera = new JugadaBandera(!isBandera);
                    
                    if(jugadaBandera.isPonerBandera()){
                        juegoListener.disminuirContadorMinas();
                    }else{
                        juegoListener.aumentarContadorMinas();
                    }
                }

            }

            if (jugadaValida) {
                boolean seguirJugando = XJbm2.juego.jugar(p, jugadaBandera);

                if (!XJbm2.juego.gano()) {
                    if (!seguirJugando) {
                        juegoListener.terminarPartida(false);
                    }
                } else {
                    // gano
                    juegoListener.terminarPartida(true);
                }

                if (XJbm2.juego.isDescubierto(p)) {
                    if (XJbm2.juego.isVacio(p)) {
                        juegoListener.actualizarVistaCompleta();
                    } else {
                        juegoListener.actualizarPunto(p);
                    }
                } else {
                    juegoListener.actualizarPunto(p);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
