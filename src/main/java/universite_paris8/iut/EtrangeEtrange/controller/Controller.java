package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;


import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Bloc.Bloc;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.Deplacement;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActeur;
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
        switchDonnees = switchDonnees.getSwitchScene();
        switchDonnees.setControllerJeu(this);
        initMonde();
        initJoueur();
        initPane();

        GestionAffichageSpriteEntite gestionAffichageSprite = new GestionAffichageSpriteEntite(paneEntite);
        monde.setListenerListeEntites(gestionAffichageSprite);
        gestionAffichageSprite.ajouterJoueur(joueur);

        GestionActeur gestionCauseDegat = new GestionActeur(paneEntite);
        monde.setListenerActeur(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMondeJSON();

        gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol = new gestionAffichageSpriteDropAuSol(paneEntite);
        monde.setListenerListeDropsAuSol(gestionAffichageDropAuSol);
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(23, 23), joueur));
        


        deplacement = new Deplacement(joueur);
        Bloc bloc = new Bloc( monde, 11, 11, Direction.BAS, 1, 0, new Hitbox(1,1));
        monde.ajoutActeur(new Slime(monde,13,13,Direction.HAUT,new Hitbox(0.5,0.5)));
        monde.ajoutActeur(bloc);
        monde.ajoutActeur(new Squelette( monde,  15, 25, Direction.BAS, 50, 5, 5, 10, 10, 0.025, new Hitbox(0.5, 0.5), new Aetoile(monde), joueur));

        initGameLoop();
        gameLoop.play();
        AudioClip audioClip = new AudioClip("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/epee.mp3");
        audioClip.play();

    }


    private void initGameLoop() {
        gameLoop = new Timeline();

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame
                (
                    Duration.seconds(0.07),

                    (ev ->
                    {
                        monde.unTour(tour++);

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


    public void initJoueur()
    {
        String guerrier = switchDonnees.getClasseJoueur();

        System.out.println(guerrier);
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
        switchDonnees.setJoueur(joueur);
        monde.setJoueur(joueur);
        joueur.getSac().ajoutItem(new Epee());
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

    public void ouvrirMenu() throws IOException {
        if(switchDonnees.getSceneMenu()==null){
            FXMLLoader fxmlLoaderMenu = new FXMLLoader(Runner.class.getResource("menuView.fxml"));
            Scene sceneMenu = new Scene(fxmlLoaderMenu.load(), Constantes.largeurEcran, Constantes.hauteurEcran);
            switchDonnees.setSceneMenu(sceneMenu);
            switchDonnees.setControllerMenu(fxmlLoaderMenu.getController());
        }
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