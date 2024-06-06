package universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class gestionAffichageVieJoueur {
    private ImageView barreDeVie;
    private Joueur joueur;
    private IntegerProperty degatsRecus;


    public gestionAffichageVieJoueur(Joueur joueur) {
        this.joueur = joueur;
        this.barreDeVie = new ImageView();
        this.degatsRecus = new SimpleIntegerProperty(0);


        miseAJourBarreDeVie((int) joueur.getPv());

        // Configurer l'observateur pour les dégâts reçus
        degatsRecus.addListener((obs, ancienDegat, nouveauDegat) -> {
            if ((nouveauDegat.intValue() / 5) > (ancienDegat.intValue() / 5)) {
                int degatsAAppliquer = (nouveauDegat.intValue() / 5) - (ancienDegat.intValue() / 5);
                int nouvelleSante = (int) Math.max(0, joueur.getPv() - degatsAAppliquer);
                joueur.setPv(nouvelleSante);
                miseAJourBarreDeVie(nouvelleSante);
                
            }
        });
    }

    private void miseAJourBarreDeVie(int pv) {
        String imagePath = "file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/" + pv + ".png";
        barreDeVie.setImage(new Image(imagePath));
    }

    public void recevoirDegats(int degats) {
        degatsRecus.set(degatsRecus.get() + degats);
    }
}
