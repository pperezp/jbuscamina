package jbm2.x.listeners;

import java.awt.Point;

public interface JugoListener {
    
    void actualizarVistaCompleta();
    void actualizarPunto(Point p);
    void terminarPartida(boolean gano);
    
}
