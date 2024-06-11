package universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;

public class GestionAffichageVieJoueur {
    @FXML
    private HBox hboxCoeurs;
    private Pv pv;

    // Chemins d'accès aux images
    private final String coeurPleinPath = "/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/0.png";
    private final String coeurMoitiéPath = "/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/1.png";
    private final String coeurVidePath = "/universite_paris8/iut/EtrangeEtrange/texture/BarreDeVie/2.png";

    public GestionAffichageVieJoueur(Pv pv) {
        this.pv = pv;
    }

    public void setHboxCoeurs(HBox hbox) {
        this.hboxCoeurs = hbox;
        initialize();
    }

    public void initialize() {
        // Ajout d'un listener à la propriété pv pour actualiser les cœurs à chaque changement
        pv.getPvActuelleProperty().addListener((obs, oldVal, newVal) -> actualiseCoeurs());
        actualiseCoeurs(); // Initialisation de l'affichage des cœurs
    }

    private void actualiseCoeurs() {
        if (hboxCoeurs != null) {
            hboxCoeurs.getChildren().clear();

            // Déterminer le nombre total de cœurs
            int nombreCoeursMax = (int) Math.ceil(pv.getPvMaximum() / 20.0);
            double pointsDeVieActuels = pv.getPv();

            for (int i = 0; i < nombreCoeursMax; i++) {
                ImageView coeur;
                System.out.println("ahvrmt");

                if (pointsDeVieActuels >= 20) {
                    coeur = new ImageView(new Image(getClass().getResourceAsStream(coeurPleinPath)));
                    pointsDeVieActuels -= 20;
                } else if (pointsDeVieActuels >= 10) {
                    coeur = new ImageView(new Image(getClass().getResourceAsStream(coeurMoitiéPath)));
                    pointsDeVieActuels = 0;
                } else {
                    coeur = new ImageView(new Image(getClass().getResourceAsStream(coeurVidePath)));
                    System.out.println(coeur.getX());
                    System.out.println(coeur.getY());
                }

                coeur.setFitWidth(30);  // Définir la largeur de l'image du cœur
                coeur.setFitHeight(30); // Définir la hauteur de l'image du cœur
                hboxCoeurs.getChildren().add(coeur);
            }
        }
    }
}

