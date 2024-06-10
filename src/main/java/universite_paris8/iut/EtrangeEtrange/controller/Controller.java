package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.EpeeDeSoldat;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
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
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActionDegat;

import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Loup;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Boss.RoiSquelette;

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
    private Deplacement deplacement;
    private SwitchScene switchDonnees;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchDonnees = switchDonnees.getSwitchScene();
        switchDonnees.setControllerJeu(this);
        initMonde();
        initJoueur();
        initPane();

        GestionAffichageSpriteEntite gestionAffichageSprite = new GestionAffichageSpriteEntite(paneEntite);
        monde.setListenerListeEntites(gestionAffichageSprite);
        gestionAffichageSprite.ajouterJoueur(joueur);

        GestionActionDegat gestionCauseDegat = new GestionActionDegat(paneEntite);
        monde.setListenerProjectile(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMondeJSON();

        gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol = new gestionAffichageSpriteDropAuSol(paneEntite);
        monde.setListenerListeDropsAuSol(gestionAffichageDropAuSol);
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(23, 23), joueur));
        

        // Création des entités avec l'algo A* qui leur permet de rejoindre le joueur
        Aetoile aetoile = new Aetoile(monde);
        initLoups(aetoile);
        initBoss(monde, joueur, aetoile);


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

                        for (Entite entite : monde.getEntities())
                        {
                            PNJ lambda1 = (PNJ) entite;
                            lambda1.action();
                        }

                        monde.verificationCollisionAvecArme();
                        monde.miseAjourCauseDegats();

                    })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void initPane(){
        // Initialisation taille en fonction de la taille de la map
        int largeur = Monde.getSizeMondeLargeur()* Constantes.tailleTile;
        int hauteur = Monde.getSizeMondeHauteur()* Constantes.tailleTile;

        TilePaneSol.setMaxSize(largeur, hauteur);
        TilePaneSol.setMinSize(largeur, hauteur);

        TilePaneTraversable.setMaxSize(largeur, hauteur);
        TilePaneTraversable.setMinSize(largeur, hauteur);

        TilePaneNontraversable.setMaxSize(largeur, hauteur);
        TilePaneNontraversable.setMinSize(largeur, hauteur);


        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateX(scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateY(scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
        });

        paneEntite.setTranslateX(scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        paneEntite.setTranslateY(scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
    }

    /**
     * Permet de déplacer l'affichage lorsque le joueur se déplace :
     * @param position : Position du joueur
     * @param longueurAxe : Hauteur ou largeur de l'écran
     */
    public double scrollMap(double position, int longueurAxe, double positionInitiale){
        if (-position * Constantes.tailleTile + longueurAxe / 2.0 < 0)
            if (-position * Constantes.tailleTile + longueurAxe / 2.0 > -Monde.getSizeMondeLargeur()*Constantes.tailleTile+longueurAxe )
                return -position * Constantes.tailleTile + longueurAxe / 2.0;
        return positionInitiale;
    }
    public void initMonde()
    {
        monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());
    }

    public void initJoueur(){
        // Initialisation Coordonnées centre monde et des listeners
        joueur = new Guerrier(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS);
        joueur.getSac().ajoutItem(new EpeeDeSoldat());
        monde.setJoueur(joueur);
        switchDonnees.setJoueur(joueur);
    }
    private void initLoups(Aetoile aetoile) {
        for(int i = 16 ; i < Monde.getSizeMondeHauteur()-10 ; i++){
            for(int j = 16 ; j < Monde.getSizeMondeLargeur()-10 ; j++){
                if(monde.getNontraversable()[i][j]==-1){
                    Loup loup1 = new Loup(joueur, monde, j+0.5, i+0.5, Direction.BAS, new Hitbox(0.5, 0.5), aetoile);
                    monde.ajoutEntite(loup1);
                }
            }
        }
    }

    private void initBoss(Monde monde, Joueur joueur, Aetoile aetoile) {
        RoiSquelette roiSquelette = new RoiSquelette(1000, 20, 100, 15, 5, 0.1, monde, 6, 28, Direction.BAS, new Hitbox(0.5, 0.5));
        monde.ajoutEntite(roiSquelette);
    }


    public void keyPressed(KeyEvent keyEvent) throws IOException {
        KeyCode touche = keyEvent.getCode();;

            if(touche==ConstantesClavier.deplacementHaut)
                deplacement.ajoutDirection(Direction.HAUT);
            else if (touche==ConstantesClavier.deplacementDroite)
                deplacement.ajoutDirection(Direction.DROITE);
            else if (touche==ConstantesClavier.recupererObjetSol)
                joueur.ramasserObjet();
            else if(touche==ConstantesClavier.deplacementGauche)
                deplacement.ajoutDirection(Direction.GAUCHE);
            else if(touche==ConstantesClavier.deplacementBas)
                deplacement.ajoutDirection(Direction.BAS);
            else if(touche==ConstantesClavier.courrir)
                joueur.estEntrainDeCourir(true);
            else if(touche==ConstantesClavier.attaquer)
                this.joueur.actionMainDroite();
            else if(touche==ConstantesClavier.inventaire)
                ouvrirMenu();
    }




    public void onKeyReleased(KeyEvent keyEvent)
    {
            KeyCode touche = keyEvent.getCode();

            if(touche==ConstantesClavier.deplacementHaut)
                deplacement.enleveDirection(Direction.HAUT);
            else if(touche==ConstantesClavier.deplacementDroite)
                deplacement.enleveDirection(Direction.DROITE);
            else if(touche==ConstantesClavier.deplacementGauche)
                deplacement.enleveDirection(Direction.GAUCHE);
            else if(touche==ConstantesClavier.deplacementBas)
                deplacement.enleveDirection(Direction.BAS);
            else if(touche==ConstantesClavier.courrir)
                joueur.estEntrainDeCourir(false);


    }

    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();
    }

    public void ouvrirMenu() {
        switchDonnees.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        switchDonnees.getControllerMenu().recupererDonnees();
        switchDonnees.getStage().setScene(switchDonnees.getSceneMenu());
        switchDonnees.getStage().show();
        System.out.println("Changement de scène vers Menu");
    }

    public void recupererDonnees() {
        switchDonnees.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        System.out.println(switchDonnees.getStage().getScene()+" JEU");
    }

}