package universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;

public class GestionAffichageVieJoueur {
    @FXML
    private HBox hboxCoeurs;

    private Pv pv;

    public GestionAffichageVieJoueur(Pv pv) {
        this.pv = pv;

    }

    public void initialize() {
        pv.getPvActuelleProperty().addListener((obs, oldVal, newVal) -> actualiseCoeurs());
        actualiseCoeurs();
        System.out.println("Ajout d'un cœur : pleinide");

    }

    public void actualiseCoeurs() {
        if (hboxCoeurs != null) {
            hboxCoeurs.getChildren().clear();  // Nettoyage des enfants de HBox
            int full = pv.nbCoeurs();         // Nombre de cœurs pleins
            int max = pv.nbCoeursMax();       // Nombre total de cœurs

            for (int i = 0; i < max; i++) {
                ImageView coeur = new ImageView(i < full ? new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/0.png") :
                        new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/2.png"));
                System.out.println("Ajout d'un cœur : " + (i < full ? "plein" : "vide"));
                hboxCoeurs.getChildren().add(coeur);
            }
        }
    }
}
