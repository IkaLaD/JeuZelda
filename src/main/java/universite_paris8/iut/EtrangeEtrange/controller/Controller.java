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
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Controlable;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Loup;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.Deplacement;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.gestionAffichageSpriteEntite;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.SpriteEntite;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionCauseDegat;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.gestionAffichageSpriteEntite;
import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
    private int temps = 0;
    private Deplacement deplacement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMonde();
        initJoueur();
        initPane();

        gestionAffichageSpriteEntite gestionAffichageSprite = new gestionAffichageSpriteEntite(paneEntite);
        monde.setListenerListeEntites(gestionAffichageSprite);
        gestionAffichageSprite.ajouterJoueur(joueur);

        GestionCauseDegat gestionCauseDegat = new GestionCauseDegat(paneEntite);
        monde.setListenerProjectile(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMondeJSON();
        gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol = new gestionAffichageSpriteDropAuSol(paneEntite);
        monde.setListenerListeDropsAuSol(gestionAffichageDropAuSol);
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(23, 23), joueur));

        initLoups();
        monde.setJoueur(joueur);

        deplacement = new Deplacement(joueur);
        initGameLoop();
        gameLoop.play();

    }

    private void initLoups() {
        double rayonDetection = 5.0; // Augmenter le rayon de détection à 5 (ou toute autre valeur)
        Loup loup1 = new Loup(joueur, monde, 10.0, 10.0, Direction.BAS, new Hitbox(0.5, 0.5), new Aetoile(monde, joueur), rayonDetection);
        System.out.println("Ajout du Loup 1");
        Loup loup2 = new Loup(joueur, monde, 15.0, 15.0, Direction.BAS, new Hitbox(0.5, 0.5), new Aetoile(monde, joueur), rayonDetection);
        System.out.println("Ajout du Loup 2");
        monde.ajoutEntite(loup1);
        monde.ajoutEntite(loup2);
    }






    private void initGameLoop() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.1),
                (ev -> {
                    for (Entite entite : monde.getEntities()) {
                        if (entite instanceof Loup) {
                            Loup loup = (Loup) entite;
                            loup.seDeplacerVersJoueur(joueur, loup.getAetoile(), monde.getNontraversable());
                        } else if (entite instanceof Controlable) {
                            Controlable controlableEntite = (Controlable) entite;
                            controlableEntite.action();
                        }
                    }

                    monde.verificationCollisionAvecArme();
                    monde.miseAjourCauseDegats();
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
        paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0);
    }
    public void initMonde()
    {
        monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());
    }

    public void initJoueur(){
        // Initialisation Coordonnées centre monde et des listeners
        joueur = new Guerrier(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS);
    }

    public void keyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode){
            case Q:
                deplacement.addKeyCode(KeyCode.Q);
                break;
            case D:
                deplacement.addKeyCode(KeyCode.D);
                break;
            case Z:
                deplacement.addKeyCode(KeyCode.Z);
                break;
            case S:
                deplacement.addKeyCode(KeyCode.S);
                break;
            case E:
                joueur.ramasserObjet();
                break;
            case M:
                joueur.enlevePv(60);
                break;
        }

    }
    public void onKeyReleased(KeyEvent keyEvent) {
        deplacement.removeKeyCode(keyEvent.getCode());
    }

    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();

        if (mouseEvent.getButton() == MouseButton.PRIMARY)
            this.joueur.actionMainDroite();
    }

}