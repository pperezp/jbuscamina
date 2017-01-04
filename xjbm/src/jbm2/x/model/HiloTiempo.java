package jbm2.x.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class HiloTiempo implements Runnable {

    private JLabel lbl;

    public HiloTiempo(JLabel lbl) {
        this.lbl = lbl;
    }

    @Override
    public void run() {
        try {
            for (int hora = 0; hora < 99; hora++) {
                for (int min = 0; min < 60; min++) {
                    for (int seg = 0; seg < 60; seg++) {
                        lbl.setText((hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg));

                        Thread.sleep(1000);

                    }
                }
            }
        } catch (InterruptedException ex) {

        }
    }

}
