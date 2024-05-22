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

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
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
    private SwitchScene switchDonnees;

    public void onClicked(MouseEvent mouseEvent) {
        // Renvoie le contenu de la pane et des tilePane du jeu qui était affiché en arrière-plan, pour pouvoir le re afficher dans le Controller du jeu
        switchDonnees.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        switchDonnees.getControllerJeu().recupererDonnees();
        switchDonnees.getStage().setScene(switchDonnees.getSceneJeu());
        switchDonnees.getStage().show();
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

        Joueur joueur = switchDonnees.getJoueur();
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
        paneEntite.setTranslateX(-joueur.getPosition().getX()*Constantes.tailleTile+Constantes.largeurEcran/2.0);
        paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Récupération Pane + Joueur
        switchDonnees = SwitchScene.getSwitchScene();

        // Effet sombre sur le jeu en arrière-plan
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        paneEntite.setEffect(colorAdjust);
        TilePaneSol.setEffect(colorAdjust);
        TilePaneTraversable.setEffect(colorAdjust);
        TilePaneNontraversable.setEffect(colorAdjust);

        initBackgroundJeu();
        initialisationInventaire();
    }

    public void initialisationInventaire(){
        // Placement du menu au milieu de l'écran et application de l'image de fond du menu
        Image fondInventaire = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/fondInventaire.png");
        TabPane.setTranslateX(0);
        TabPane.setTranslateY(0);
        TabPane.setMaxSize(fondInventaire.getWidth(), fondInventaire.getHeight());
        TabPane.setMinSize(fondInventaire.getWidth(), fondInventaire.getHeight());
        TabPane.setBackground(new Background(new BackgroundImage(fondInventaire, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));


        // Ajout des textes de la page inventaire : "Inventaire", "Main droite", "Main Gauche"
        titreInventaire.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/InventaireTitre.png"));
        titreMainDroite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/MainDroiteTitre.png"));
        titreMainGauche.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/MainGaucheTitre.png"));

        // Ajout des cases de stockage pour la main droite et la main gauche et des images des objets qui y seront présents
        Image caseStockage = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/caseStockage.png");

            // Placement des images des cases de stockage pour la main droite et la main gauche
            ImageView caseStockageMainDroite = new ImageView(caseStockage);
            ImageView caseStockageMainGauche = new ImageView(caseStockage);
            caseStockageMainDroite.setX(0);
            caseStockageMainGauche.setY(0);
            caseStockageMainGauche.setX(0);
            caseStockageMainDroite.setY(0);

            // Création des ImageView qui contiendront par la suite les images des objets présents dans les mains droite et gauche
            objetMainDroite = new ImageView();
            objetMainGauche = new ImageView();
            // Quelques propriétés pour agrandir l'image et pour la placer correctement dans la case
            objetMainDroite.setScaleX(1.5);
            objetMainGauche.setScaleY(1.5);
            objetMainGauche.setScaleX(1.5);
            objetMainDroite.setScaleY(1.5);
            objetMainGauche.setX(16);
            objetMainDroite.setX(16);
            objetMainGauche.setY(16);
            objetMainDroite.setY(16);

            // Ajout des cases de stockage et du futur emplacement des images pour les mains droite et gauche
            conteneurObjetMainDroite.getChildren().add(caseStockageMainDroite);
            conteneurObjetMainGauche.getChildren().add(objetMainGauche);
            conteneurObjetMainGauche.getChildren().add(caseStockageMainGauche);
            conteneurObjetMainDroite.getChildren().add(objetMainDroite);
    }
    public void gestionInventaire(){
        Joueur joueur = switchDonnees.getJoueur();

        // recupère l'image de l'objet présent dans la main droite
        objetMainDroite.setImage(getImageObjet(joueur.getObjetMainDroite().getClass()));
        // recupère l'image de l'objet présent dans la main gauche
        objetMainGauche.setImage(getImageObjet(joueur.getObjetMainDroite().getClass()));

    }

    public Image getImageObjet(Class<? extends Objet> objet){
        if (objet.equals(Arc.class))
            return new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/arc/droparc.png");

        System.out.println("Pas d'objet");
        return null;
    }

    public void recupererDonnees() {
        switchDonnees.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionInventaire();
        System.out.println(switchDonnees.getStage().getScene()+" MENU");
    }
}
