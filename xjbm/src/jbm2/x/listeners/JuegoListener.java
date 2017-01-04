package jbm2.x.listeners;

import java.awt.Point;

public interface JuegoListener {
    
    void iniciarPartida();
    void actualizarVistaCompleta();
    void actualizarPunto(Point p);
    void terminarPartida(boolean gano);
    void aumentarContadorMinas();
    void disminuirContadorMinas();
    
}
