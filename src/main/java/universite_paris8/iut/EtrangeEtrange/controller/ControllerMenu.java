package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    @FXML
    private Tab Inventaire;
    @FXML
    private TabPane TabPane;

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
    private ColorAdjust ombreArrierePlan;

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
        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateX(switchScene.getControllerJeu().scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateY(switchScene.getControllerJeu().scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
        });

        paneEntite.setTranslateX(switchScene.getControllerJeu().scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        paneEntite.setTranslateY(switchScene.getControllerJeu().scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchScene = SwitchScene.getSwitchScene();

        TabPane.getSelectionModel().selectedItemProperty().addListener((old, obs, nouv)->
                TabPane.getSelectionModel().getSelectedItem().getContent().requestFocus()
        );

        // Effet sombre sur le jeu en arrière-plan
        ombreArrierePlan = new ColorAdjust();
        ombreArrierePlan.setBrightness(-0.5);
        initBackgroundJeu();

        // Placement du menu au milieu de l'écran et application de l'image de fond du menu
        Image fondMenu = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/fondInventaire.png");
        TabPane.setTranslateX(0);
        TabPane.setTranslateY(0);
        TabPane.setMaxSize(fondMenu.getWidth(), fondMenu.getHeight());
        TabPane.setMinSize(fondMenu.getWidth(), fondMenu.getHeight());
        TabPane.setBackground(new Background(new BackgroundImage(fondMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void setOmbreArrierePlan(){
        paneEntite.setEffect(ombreArrierePlan);
        TilePaneSol.setEffect(ombreArrierePlan);
        TilePaneTraversable.setEffect(ombreArrierePlan);
        TilePaneNontraversable.setEffect(ombreArrierePlan);
    }
    public void delOmbreArrierePlan(){
        paneEntite.setEffect(null);
        TilePaneSol.setEffect(null);
        TilePaneTraversable.setEffect(null);
        TilePaneNontraversable.setEffect(null);
    }


    public void recupererDonnees() {
        setOmbreArrierePlan();
        switchScene.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
    }

    public void onSroll(ScrollEvent scrollEvent) {
        if(scrollEvent.getDeltaY()>0) {
            // Renvoie le contenu de la pane et des tilePane du jeu qui était affiché en arrière-plan, pour pouvoir le re afficher dans le Controller du jeu
            switchScene.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
            switchScene.getControllerJeu().recupererDonnees();
            switchScene.getStage().setScene(switchScene.getSceneJeu());
            switchScene.getStage().show();
            delOmbreArrierePlan();
        }
    }
}
