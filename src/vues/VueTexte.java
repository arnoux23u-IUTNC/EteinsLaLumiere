package vues;

import modeles.GrilleLampe;
import utils.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueTexte extends JPanel implements Observer {

    private final JButton config;
    private final JButton aleatoire;
    private final JButton jouer;
    private final JButton quitter;

    private final JLabel count;
    private final JLabel deplacement;

    private GrilleLampe grille;

    public VueTexte() {
        this.setPreferredSize(new Dimension(250, 800));
        this.setLayout(new GridLayout(6, 1));

        config = new JButton("Configurer");
        aleatoire = new JButton("Aleatoire");
        jouer = new JButton("Jouer");
        jouer.setEnabled(false);
        quitter = new JButton("Quitter");
        quitter.setEnabled(false);

        deplacement = new JLabel();
        deplacement.setHorizontalAlignment(SwingConstants.CENTER);

        count = new JLabel();
        count.setHorizontalAlignment(SwingConstants.CENTER);
        count.setFont(new Font("Arial", Font.BOLD, 20));

        deplacement.setText("Nombre de deplacement:");
        count.setText("0");

        config.addActionListener(e -> goToConfigure());
        aleatoire.addActionListener(e -> goToAlea());
        jouer.addActionListener(e -> goToPlay());
        quitter.addActionListener(e -> leave());

        this.add(config);
        this.add(aleatoire);
        this.add(jouer);
        this.add(deplacement);
        this.add(count);
        this.add(quitter);
    }

    private void leave() {
        grille.changerMode(Mode.NORMAL);
        grille.shutdownAll();
    }

    private void goToConfigure() {
        grille.changerMode(Mode.CONFIGURATION);
    }

    private void goToPlay() {
        grille.changerMode(Mode.JOUER);
    }

    private void goToAlea() {
        grille.changerMode(Mode.ALEATOIRE);
        grille.regenerer(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (grille.getMode()) {
            case CONFIGURATION -> {
                config.setEnabled(false);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(!grille.checkFini());
            }
            case JOUER -> {
                if(grille.checkFini()){
                    grille.changerMode(Mode.NORMAL);
                    return;
                }
                config.setEnabled(false);
                aleatoire.setEnabled(false);
                quitter.setEnabled(true);
                jouer.setEnabled(false);
            }
            case NORMAL -> {
                config.setEnabled(true);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(false);
            }
            case ALEATOIRE -> {
                config.setEnabled(true);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(!grille.checkFini());
            }
        }
        count.setText(String.valueOf(grille.getDeplacements()));
    }

    @Override
    public void update(Observable o, Object arg) {
        grille = (GrilleLampe) o;
        repaint();
    }
}
