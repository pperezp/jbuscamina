package jbm2.x;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jbm2.model.Mina;
import jbm2.model.Espacio;
import jbm2.model.Numero;
import jbm2.model.Tablero;
import jbm2.model.Vacio;
import jbm2.x.listeners.EspacioMouseListener;
import jbm2.x.model.Colores;
import jbm2.x.model.HiloTiempo;
import jbm2.x.model.Reglas;
import jbm2.x.listeners.JuegoListener;

public class XJbm2 extends javax.swing.JFrame implements JuegoListener {

    public static Tablero juego;
    public static boolean habilitado; // se usa cuando el usuario gana o pierde
    private JLabel[][] labels;
    private Thread hTiempo;
    private int contMinas;
    private static boolean standBy; // para que el reloj empieze solo cuando el usuario
                             // haga click en el tablero
    

    public XJbm2() {
        initComponents();

        
        
        initTablero();
        cargarFuentes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTiempo = new javax.swing.JLabel();
        lblMinas = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new java.awt.GridLayout(0, 3));

        lblTiempo.setBackground(new java.awt.Color(1, 1, 1));
        lblTiempo.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(249, 2, 25));
        lblTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempo.setText("00:00:00");
        lblTiempo.setOpaque(true);

        lblMinas.setBackground(new java.awt.Color(1, 1, 1));
        lblMinas.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        lblMinas.setForeground(new java.awt.Color(249, 2, 25));
        lblMinas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMinas.setText("10");
        lblMinas.setOpaque(true);

        lblTitulo.setBackground(new java.awt.Color(1, 1, 1));
        lblTitulo.setForeground(new java.awt.Color(0, 255, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscaminas");
        lblTitulo.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblMinas, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinas, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(lblTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(71, 71, 71))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        initTablero();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMinas;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JLabel lblTitulo;
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
    public void initTablero() {
        standBy = true
                ;
//        if(hTiempo != null){
//            hTiempo.interrupt();
//        }
//        
        
        
        habilitado = true;
        
        juego = new Tablero(
                Reglas.filas, 
                Reglas.columnas, 
                Reglas.bombas
        );
        
        contMinas = Reglas.bombas;
        
        Point p;
        Color c = Color.BLACK;

        panel.removeAll();
        labels = new JLabel[Reglas.filas][Reglas.columnas];

        panel.setLayout(new java.awt.GridLayout(0, Reglas.columnas));

        JLabel lbl;
        for (int i = 0; i < Reglas.filas; i++) {
            for (int j = 0; j < Reglas.columnas; j++) {
                p = new Point(i, j);
                lbl = new JLabel();
                lbl.setOpaque(true);
                
                
                lbl.setName(i + "-" + j);
                lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbl.setFont(new java.awt.Font("Noto Sans", 1, 30)); // NOI18N
                

                if (juego.isNumero(p)) {
                    Numero num = (Numero) juego.getEspacio(p);

                    switch (num.getValor()) {
                        case 1:
                            c = Color.decode(Colores.NUM1);
                            break;
                        case 2:
                            c = Color.decode(Colores.NUM2);
                            break;
                        case 3:
                            c = Color.decode(Colores.NUM3);
                            break;
                        case 4:
                            c = Color.decode(Colores.NUM4);
                            break;
                        case 5:
                            c = Color.decode(Colores.NUM5);
                            break;
                    }
                }else if(juego.isBandera(p)){
                    c = Color.decode(Colores.BANDERA);
                }else if(juego.isMina(p)){
                    c = Color.decode(Colores.BOMBA);
                }
                
                lbl.setForeground(c);

                lbl.addMouseListener(new EspacioMouseListener(this));
                panel.add(lbl);
                labels[i][j] = lbl;
            }
        }
        pack();
        pintarTableroCompleto();
        this.setBounds(0, 0, Reglas.ancho, Reglas.alto);
        this.setLocationRelativeTo(null);
        lblMinas.setText(String.valueOf(contMinas));
        lblTiempo.setText("00:00:00");
        lblTitulo.setText("Buscaminas");
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
        } 
        
        if (espacio.isDescubierto()) {
            lbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            lbl.setBackground(Color.decode(Colores.FONDO_DESCUBIERTO));
            
        } else {
            lbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lbl.setBackground(Color.decode(Colores.FONDO_NO_DESCUBIERTO));
        }

    }

    @Override
    public void actualizarVistaCompleta() {
        System.out.println("pintando completo...");
        pintarTableroCompleto();
    }

    @Override
    public void terminarPartida(boolean gano) {
        habilitado = false;
        hTiempo.interrupt();
        if (gano) {
            lblTitulo.setText("Ganaste!");
        } else {
            lblTitulo.setText("Perdiste");
            
            juego.explotarMinas();
            
            /*Ahora solo actualizo las bombas*/
            for(Mina b : juego.getMinas()){
                actualizarPunto(b.getPunto());
            }
        }
    }

    @Override
    public void actualizarPunto(Point p) {
        pintarLabel(labels[p.x][p.y], p);
        System.out.println("Sólo pintando un label");
    }

    private void cargarFuentes() {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/jbm2/x/font/calculator.ttf").getFile()));
            
            lblMinas.setFont(font.deriveFont(1).deriveFont(60f));
            lblMinas.setText(String.valueOf(contMinas));
            lblTiempo.setFont(font.deriveFont(1).deriveFont(60f));
            lblTitulo.setFont(font.deriveFont(1).deriveFont(60f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XJbm2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(XJbm2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XJbm2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void aumentarContadorMinas() {
        contMinas++;
        lblMinas.setText(String.valueOf(contMinas));
    }

    @Override
    public void disminuirContadorMinas() {
        contMinas--;
        lblMinas.setText(String.valueOf(contMinas));
    }

    @Override
    public void iniciarPartida() {
        standBy = false;
        hTiempo = new Thread(new HiloTiempo(lblTiempo));
        hTiempo.start();
    }
    
    public static boolean isStandBy(){
        return standBy;
    }
}
