package jbm2.x;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jbm2.model.Espacio;
import jbm2.model.Tablero;
import jbm2.x.listeners.EspacioMouseListener;
import jbm2.x.listeners.JugoListener;

public class XJbm2 extends javax.swing.JFrame implements JugoListener {

    public static Tablero juego;
    private JLabel[][] labels;
    private boolean gano;
    private boolean perdio;

    public XJbm2() {
        initComponents();

        initTablero(10, 10, 10);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new java.awt.GridLayout(0, 3));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XJbm2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    /**
     * Sólo modifica todos los labels
     */
    private void pintarTableroCompleto() {
        int filas, columnas;
        filas = juego.getFilas();
        columnas = juego.getColumnas();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                pintarLabel(labels[i][j], new Point(i, j));
            }
        }
    }

    /**
     * Este método sirve para poner los labels en el panel y crea una matriz con
     * esos labels. Sólo se llama una vez
     */
    public void initTablero(int filas, int columnas, int bombas) {
        juego = new Tablero(filas, columnas, bombas);

        panel.removeAll();
        labels = new JLabel[filas][columnas];

        panel.setLayout(new java.awt.GridLayout(0, columnas));

        JLabel lbl;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                lbl = new JLabel();
                lbl.setName(i + "-" + j);
                lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbl.setFont(new java.awt.Font("Noto Sans", 1, 20)); // NOI18N
                lbl.setForeground(new java.awt.Color(255, 0, 35));
                

                lbl.addMouseListener(new EspacioMouseListener(this));
                panel.add(lbl);
                labels[i][j] = lbl;
            }
        }
        pack();
        pintarTableroCompleto();
    }

    /**
     * Se encarga de poner el texto y el borde de un label en una posicion
     * determinada
     *
     * @param lbl
     * @param espacio
     */
    private void pintarLabel(JLabel lbl, Point posicion) {

        Espacio espacio = juego.getEspacio(posicion);

        lbl.setText(espacio.toString());

        if (espacio.isBandera()) {
            lbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else if (espacio.isDescubierto()) {
            lbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        } else {
            lbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        }

    }

    @Override
    public void actualizarVistaCompleta() {
        System.out.println("pintando completo...");
        pintarTableroCompleto();
    }

    @Override
    public void terminarPartida(boolean gano) {
        if (gano) {
            System.out.println("WENA CULIAO!");
        } else {
            System.out.println("Cagaste!");
        }
    }

    @Override
    public void actualizarPunto(Point p) {
        pintarLabel(labels[p.x][p.y], p);
        System.out.println("Sólo pintando un label");
    }
}
