package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.vues.Menus.Inventaire.gestionAffichageInventaire;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    /**
     * TilePane qui va stocker les images des objets dans l'inventaire
     */
    @FXML
    private TilePane objetsInventaire;
    /**
     * Tilepane qui va stocker les images des cases de stockage de l'inventaire
     */
    @FXML
    private TilePane caseStockageInventaire;
    /**
     * Tilepane qui va stocker les quantités de chaque objets présent dans l'inventaire
     */
    @FXML
    public TilePane quantitéObjetInventaire;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main droite
     */
    @FXML
    private Pane conteneurObjetMainDroite;
    /**
     * ImageView de l'objet dans la main droite
     */
    private ImageView objetMainDroite;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main gauche
     */
    @FXML
    private Pane conteneurObjetMainGauche;
    /**
     * ImageView de l'objet dans la main gauche
     */
    @FXML
    private ImageView objetMainGauche;
    @FXML
    private TabPane TabPane;
    /**
     * Texte "Inventaire" dans le menu inventaire
     */
    @FXML
    private ImageView titreInventaire;
    /**
     * Texte "Main droite" dans le menu inventaire
     */
    @FXML
    private ImageView titreMainDroite;
    /**
     * Texte "Main gauche" dans le menu inventaire
     */
    @FXML
    private ImageView titreMainGauche;
    /**
     * La pane et les 3 TilePane suivantes sont l'affichage du jeu, qui tourne en arrière-plan du menu.
     */
    @FXML
    private Pane paneEntite;
    @FXML
    private TilePane TilePaneSol;
    @FXML
    private TilePane TilePaneTraversable;
    @FXML
    private TilePane TilePaneNontraversable;

    /**
     * Switch donnéees qui permet de récupérer les données nécessaires (voir class SwitchScene)
     */
    private SwitchScene switchScene;

    public void onClicked(MouseEvent mouseEvent) {
        // Renvoie le contenu de la pane et des tilePane du jeu qui était affiché en arrière-plan, pour pouvoir le re afficher dans le Controller du jeu
        switchScene.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        switchScene.getControllerJeu().recupererDonnees();
        switchScene.getStage().setScene(switchScene.getSceneJeu());
        switchScene.getStage().show();
    }

    public void initBackgroundJeu(){
        // Initialisation taille en fonction de la taille de la map
        int largeur = Monde.getSizeMondeLargeur()* Constantes.tailleTile;
        int hauteur = Monde.getSizeMondeHauteur()*Constantes.tailleTile;

        TilePaneSol.setMaxSize(largeur, hauteur);
        TilePaneSol.setMinSize(largeur, hauteur);

        TilePaneTraversable.setMaxSize(largeur, hauteur);
        TilePaneTraversable.setMinSize(largeur, hauteur);

        TilePaneNontraversable.setMaxSize(largeur, hauteur);
        TilePaneNontraversable.setMinSize(largeur, hauteur);

        Joueur joueur = switchScene.getJoueur();
        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)-> {
            if (-joueur.getPosition().getX() * Constantes.tailleTile + Constantes.largeurEcran / 2.0 < 0)
                if (-joueur.getPosition().getX() * Constantes.tailleTile + Constantes.largeurEcran / 2.0 > -Monde.getSizeMondeLargeur()*Constantes.tailleTile+Constantes.largeurEcran )
                    paneEntite.setTranslateX(-joueur.getPosition().getX() * Constantes.tailleTile + Constantes.largeurEcran / 2.0);
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)-> {
            if(-joueur.getPosition().getY() * Constantes.tailleTile + Constantes.hauteurEcran / 2.0 < 0)
                if(-joueur.getPosition().getY() * Constantes.tailleTile + Constantes.hauteurEcran / 2.0  > -Monde.getSizeMondeHauteur()*Constantes.tailleTile+Constantes.hauteurEcran)
                    paneEntite.setTranslateY(-joueur.getPosition().getY() * Constantes.tailleTile + Constantes.hauteurEcran / 2.0);
        });
        if (-joueur.getPosition().getX() * Constantes.tailleTile + Constantes.largeurEcran / 2.0 < 0)
            if (-joueur.getPosition().getX() * Constantes.tailleTile + Constantes.largeurEcran / 2.0 > -Monde.getSizeMondeLargeur()*Constantes.tailleTile+Constantes.largeurEcran )
                paneEntite.setTranslateX(-joueur.getPosition().getX()*Constantes.tailleTile+Constantes.largeurEcran/2.0);
        if(-joueur.getPosition().getY() * Constantes.tailleTile + Constantes.hauteurEcran / 2.0 < 0)
            if(-joueur.getPosition().getY() * Constantes.tailleTile + Constantes.hauteurEcran / 2.0  > -Monde.getSizeMondeHauteur()*Constantes.tailleTile+Constantes.hauteurEcran)
                paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Récupération Pane + Joueur
        switchScene = SwitchScene.getSwitchScene();

        gestionAffichageInventaire gestionAffichageInventaire = new gestionAffichageInventaire(switchScene.getJoueur(), objetsInventaire, caseStockageInventaire, quantitéObjetInventaire,
                conteneurObjetMainDroite, objetMainDroite, conteneurObjetMainGauche,  objetMainGauche, titreInventaire,
                titreMainDroite, titreMainGauche);



        // Effet sombre sur le jeu en arrière-plan
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        paneEntite.setEffect(colorAdjust);
        TilePaneSol.setEffect(colorAdjust);
        TilePaneTraversable.setEffect(colorAdjust);
        TilePaneNontraversable.setEffect(colorAdjust);

        initBackgroundJeu();

        // Placement du menu au milieu de l'écran et application de l'image de fond du menu
        Image fondInventaire = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/fondInventaire.png");
        TabPane.setTranslateX(0);
        TabPane.setTranslateY(0);
        TabPane.setMaxSize(fondInventaire.getWidth(), fondInventaire.getHeight());
        TabPane.setMinSize(fondInventaire.getWidth(), fondInventaire.getHeight());
        TabPane.setBackground(new Background(new BackgroundImage(fondInventaire, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }




    public void recupererDonnees() {
        switchScene.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        System.out.println(switchScene.getStage().getScene()+" MENU");
    }
}
