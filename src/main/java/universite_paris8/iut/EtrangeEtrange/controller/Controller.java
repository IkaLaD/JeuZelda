package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;


import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Slime;
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

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.AfficheBulleConversation;
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

    @FXML
    private Pane paneInteraction;

    private Monde monde;
    private Joueur joueur;
    private Timeline gameLoop;
    private long tour = 0;
    private Deplacement deplacement;
    private SwitchScene switchDonnees;




    //-----------------------------------------------//

    private boolean interactionAvecPnj = false;
    private ListView<String> listProposition;
    private Label textePnj;
    private AfficheBulleConversation afficheBulleConversation;

    private GestionPrompt gestionPrompt;





    //---------------------------------------------------//






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
        for (int i = 10;i<15;i++)
        {
            for (int j = 10;j<15;j++)
            {
                if (monde.getNontraversable()[i][j] == -1)
                    monde.ajoutActeur(new Slime(monde,i,j,Direction.HAUT,new Hitbox(0.1,0.1)));
            }
        }
        initGameLoop();
        gameLoop.play();

    }


    private void initGameLoop() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), (ev -> {
            if (!interactionAvecPnj)
                monde.unTour();}));

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

    }




    public void keyPressed(KeyEvent keyEvent) throws IOException {
        KeyCode touche = keyEvent.getCode();;

            if (!interactionAvecPnj)
            {
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
                else if(touche== KeyCode.B)
                    interaction();
            }
            else
            {
                handleInteractionPnj(keyEvent);
            }
    }




    public void onKeyReleased(KeyEvent keyEvent)
    {
            KeyCode touche = keyEvent.getCode();

            if (!interactionAvecPnj)
            {
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



    }

    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();

        if (mouseEvent.getButton() == MouseButton.PRIMARY)
            this.joueur.actionMainDroite();
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

    }

    public void recupererDonnees() {
        switchDonnees.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
    }




    //-------------------------------------------------------------------------------------------//
    //                                      PNJ                                                 //
    //-----------------------------------------------------------------------------------------//




    public void interaction()
    {
        Acteur acteur = monde.interactionAvecActeur();

        if (acteur != null)
        {
            Prompt prompt = acteur.getPrompt();

            if (prompt != null)
            {

                this.interactionAvecPnj = true;

                this.afficheBulleConversation = new AfficheBulleConversation(joueur,acteur,paneInteraction);
                this.listProposition = afficheBulleConversation.getListProposition();
                this.textePnj = afficheBulleConversation.getTextePnj();


                this.gestionPrompt = new GestionPrompt(acteur.getPrompt());
                this.afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());
            }
        }
    }


    // Permet de passer un tour du prompt

    private void promptSuivant()
    {
        gestionPrompt.promptSuivant(choixSelectionner());

        if (gestionPrompt.getPrompt() != null)
            this.afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());
        else
        {
            interactionFinie();
            System.out.println("fini");
        }


    }






    //  Permet de changer le choix de réponse
    private void defile(int scroll)
    {
        int index = listProposition.getSelectionModel().getSelectedIndex();
        int indexSuivant = index + scroll;

        if (indexSuivant >= 0 && indexSuivant < listProposition.getItems().size())
        {
            listProposition.getSelectionModel().select(indexSuivant);
            listProposition.scrollTo(indexSuivant);
        }
    }


    public void interactionFinie()
    {
        this.textePnj.setVisible(false);
        this.listProposition.setVisible(false);
        this.interactionAvecPnj = false;

    }


    //  Retourne le choix séléctionner
    private String choixSelectionner()
    {
        return listProposition.getSelectionModel().getSelectedItem();
    }



    //
    private void handleInteractionPnj(KeyEvent event)
    {
        KeyCode keyCode = event.getCode();

        if (keyCode == KeyCode.ENTER)
        {
            promptSuivant();
        }
        else if (keyCode == KeyCode.S || keyCode == KeyCode.D)
        {
            defile(1);
        }
        else if (keyCode == KeyCode.Z || keyCode == KeyCode.Q)
        {
            defile(-1);
        }
    }



















}