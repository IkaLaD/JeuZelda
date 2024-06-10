package universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;

public class GestionAffichageVieJoueur {
    private HBox hboxCoeurs;
    private Pv pv;

    public GestionAffichageVieJoueur(Pv pv) {
        this.pv = pv;
    }

    public void setHboxCoeurs(HBox hbox) {
        this.hboxCoeurs = hbox;
    }

    public void initialize() {
        // Ajout d'un listener à la propriété pv pour actualiser les cœurs à chaque changement
        pv.getPvActuelleProperty().addListener((obs, oldVal, newVal) -> actualiseCoeurs());
        actualiseCoeurs(); // Initialisation de l'affichage des cœurs
    }

    private void actualiseCoeurs() {
        // Assurez-vous que hboxCoeurs est pas null
        if (hboxCoeurs != null) {
            hboxCoeurs.getChildren().clear();
            int nombreCoeurs = (int) Math.ceil(pv.getPv() / 20.0); // Division par 20 pour déterminer le nombre de cœurs
            for (int i = 0; i < nombreCoeurs; i++) {
                System.out.println("tes");
                ImageView coeur = new ImageView(new Image(getClass().getResourceAsStream("/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/0.png")));
                coeur.setFitWidth(100);  // Définir la largeur de l'image du cœur
                coeur.setFitHeight(100); // Définir la hauteur de l'image du cœur
                hboxCoeurs.getChildren().add(coeur);
            }
        }
    }
}

