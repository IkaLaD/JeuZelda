package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import universite_paris8.iut.EtrangeEtrange.Runner;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.Deplacement;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.gestionAffichageSpriteEntite;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActionDegat;

import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TilePane TilePaneSol;
    @FXML
    private TilePane TilePaneTraversable;
    @FXML
    private TilePane TilePaneNontraversable;
    @FXML
    private Pane paneEntite;
    private Monde monde;
    private Joueur joueur;
    private Timeline gameLoop;
    private long tour = 0;
    private Deplacement deplacement;
    private SwitchScene switchDonnees;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchDonnees = SwitchScene.getSwitchScene();
        initMonde();
        initJoueur();
        switchDonnees.setControllerJeu(this);
        switchDonnees.setJoueur(joueur);
        initPane();


        gestionAffichageSpriteEntite gestionAffichageSprite = new gestionAffichageSpriteEntite(paneEntite);
        gestionAffichageSprite.ajouterJoueur(joueur);

        GestionActionDegat gestionCauseDegat = new GestionActionDegat(paneEntite);
        monde.setListenerActeur(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMondeJSON();
        gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol = new gestionAffichageSpriteDropAuSol(paneEntite);
        monde.setListenerListeDropsAuSol(gestionAffichageDropAuSol);
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(23, 23), joueur));
        
        monde.setJoueur(joueur);


        deplacement = new Deplacement(joueur);
        initGameLoop();
        gameLoop.play();

    }


    private void initGameLoop() {
        gameLoop = new Timeline();

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame
                (
                    Duration.seconds(0.1),

                    (ev ->
                    {
                        tour++;
                        monde.unTour(tour);



                    })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void initPane(){
        // Initialisation taille en fonction de la taille de la map
        int largeur = Monde.getSizeMondeLargeur()*Constantes.tailleTile;
        int hauteur = Monde.getSizeMondeHauteur()*Constantes.tailleTile;

        TilePaneSol.setMaxSize(largeur, hauteur);
        TilePaneSol.setMinSize(largeur, hauteur);

        TilePaneTraversable.setMaxSize(largeur, hauteur);
        TilePaneTraversable.setMinSize(largeur, hauteur);

        TilePaneNontraversable.setMaxSize(largeur, hauteur);
        TilePaneNontraversable.setMinSize(largeur, hauteur);


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
        paneEntite.setTranslateY(-joueur.getPosition().getY()* Constantes.tailleTile+Constantes.hauteurEcran/2.0);
    }
    public void initMonde()
    {
        monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());
    }

    public void initJoueur()
    {
        String guerrier = switchDonnees.getClasseJoueur();

        if (guerrier.equals("Guerrier"))
        {
            joueur = new Guerrier(monde,Monde.getxPointDeDepart(),Monde.getyPointDeDepart(), Direction.BAS);
        }
        else if (guerrier.equals("Archer"))
        {
            joueur = new Archer(monde,Monde.getxPointDeDepart(),Monde.getyPointDeDepart(), Direction.BAS);
        }
        else if (guerrier.equals("Mage"))
        {
            // pas encore implementer
        }
        else if (guerrier.equals("Necromancier"))
        {
            // pas encore implementer
        }

    }



    public void keyPressed(KeyEvent keyEvent) throws IOException {

        /*KeyCode keyCode = keyEvent.getCode();
        if(keyCode==KeyCode.A)
            actionJoueur = new ActionUtiliserSort1();
        else if(keyCode==KeyCode.F)
            actionJoueur = new ActionUtiliserSort2();
        else if (keyCode==keyCode.R)
            actionJoueur = new ActionUtiliserSort3();
        else if(keyCode==ConstantesClavier.deplacementHaut)
            deplacement.ajoutDirection(Direction.HAUT);
        else if(keyCode==ConstantesClavier.deplacementDroite)
            deplacement.ajoutDirection(Direction.DROITE);
        else if(keyCode==ConstantesClavier.deplacementGauche)
            deplacement.ajoutDirection(Direction.GAUCHE);
        else if(keyCode==ConstantesClavier.deplacementBas)
            deplacement.ajoutDirection(Direction.BAS);
        else if(keyCode==ConstantesClavier.recupererObjetSol)
            joueur.ramasserObjet();*/

        switch (keyEvent.getCode())
        {
            case A :
                joueur.lanceUnSort(1);
                break;
            case F :
                joueur.lanceUnSort(2);
                break;
            case R :
                joueur.lanceUnSort(3);
                break;
            case Z :
                deplacement.ajoutDirection(Direction.HAUT);
                break;
            case D :
                deplacement.ajoutDirection(Direction.DROITE);
                break;

            case Q :
                deplacement.ajoutDirection(Direction.GAUCHE);
                break;
            case S :
                deplacement.ajoutDirection(Direction.BAS);
                break;
            case SHIFT:
                joueur.estEntrainDeCourir(true);
                break;
            case I :
                FXMLLoader fxmlLoaderJeu = new FXMLLoader(Runner.class.getResource("CompetenceView.fxml"));
                switchDonnees.getStage().setScene(new Scene(fxmlLoaderJeu.load(), Constantes.largeurEcran, Constantes.hauteurEcran));

                break;
        }

    }




    public void onKeyReleased(KeyEvent keyEvent)
    {
        switch (keyEvent.getCode())
        {
            case Z :
                deplacement.enleveDirection(Direction.HAUT);
                break;
            case D :
                deplacement.enleveDirection(Direction.DROITE);
                break;
            case Q :
                deplacement.enleveDirection(Direction.GAUCHE);
                break;
            case S :
                deplacement.enleveDirection(Direction.BAS);
                break;
            case SHIFT:
                joueur.estEntrainDeCourir(false);
                break;
        }
    }

    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();

        if (mouseEvent.getButton() == MouseButton.PRIMARY)
            this.joueur.actionMainDroite();
    }

    public void onScroll(ScrollEvent scrollEvent) {
        if(scrollEvent.getDeltaY()<0) {
            switchDonnees.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
            switchDonnees.getControllerMenu().recupererDonnees();
            switchDonnees.getStage().setScene(switchDonnees.getSceneMenu());
            switchDonnees.getStage().show();
            System.out.println("Changement de scène vers Menu");
        }
    }

    public void recupererDonnees() {
        switchDonnees.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        System.out.println(switchDonnees.getStage().getScene()+" JEU");
    }

}