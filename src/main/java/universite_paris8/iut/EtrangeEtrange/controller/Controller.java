package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;

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
    private Circle spriteJoueur;
    int temps = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMonde();
        initJoueur();
        initPane();

        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMonde();

        /*for (int i = 0 ; i < 5 ; i++)
        {
            Lambda lambda = new Lambda(monde,i,i,Direction.GAUCHE,new Hitbox(0.50,0.50));
            monde.ajoutEntite(lambda);
            initSprite(lambda);
        }*/

        initAnimation();
        gameLoop.play();
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame
                (
                    Duration.seconds(0.1),

                    (ev ->
                    {
                        if(temps==100000)
                            gameLoop.stop();

                        for (Entite entite : monde.getEntities())
                        {
                            Lambda lambda1 = (Lambda) entite;
                            lambda1.action();
                        }
                        temps++;
                    })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void initPane(){
        // Initialisation taille en fonction de la taille de la map
        TilePaneSol.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneSol.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneSol.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneSol.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);

        TilePaneTraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneTraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneTraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneTraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);

        TilePaneNontraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneNontraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneNontraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneNontraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);

        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateX(-joueur.getPosition().getX()*Constantes.tailleTile+Constantes.largeurEcran/2.0);
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0);
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
        joueur = new Guerrier(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS) {
        };
        spriteJoueur = new Circle((ConstantesPersonnages.GUERRIER_HITBOX/2)*Constantes.tailleTile, Color.RED);

        joueur.getPosition().getXProperty().addListener((obs, old, nouv)->
                spriteJoueur.setTranslateX(joueur.getPosition().getX()*Constantes.tailleTile)
        );
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)->
                spriteJoueur.setTranslateY(joueur.getPosition().getY()*Constantes.tailleTile)
        );

        // Paramètres cercle pour visualiser le joueur
        spriteJoueur.setTranslateX(joueur.getPosition().getX()*Constantes.tailleTile);
        spriteJoueur.setTranslateY(joueur.getPosition().getY()*Constantes.tailleTile);

        // Ajout du cercle au panneau paneEntité
        paneEntite.getChildren().add(spriteJoueur);
    }


    private void initSprite(Entite entite)
    {
        Position position = entite.getPosition();
        
        Circle circle = new Circle((double) Constantes.tailleTile /4,Color.YELLOW);
        
        position.getXProperty().addListener(e-> {
            circle.setTranslateX(position.getX()*Constantes.tailleTile);
        });
        
        position.getYProperty().addListener(e-> {
            circle.setTranslateY(position.getY()*Constantes.tailleTile);
        });

        circle.setTranslateX(position.getX()*Constantes.tailleTile);
        circle.setTranslateY(position.getY()*Constantes.tailleTile);

        entite.getPv().getPvActuelleProperty().addListener(e-> {
            Color color;
            
            double pv = entite.getPv().getPvActuelle();
            
            if (pv > 75)
            {
                color = Color.YELLOW;
            }
            else if (pv > 50)
            {
                color = Color.ORANGE;
            }
            else if (pv > 0)
            {
                color = Color.RED;
                System.out.println(pv);
            }
            else
            {
                color = Color.BLACK;
                this.paneEntite.getChildren().remove(circle);
            }
            
            circle.setFill(color);
        });
        this.paneEntite.getChildren().add(circle);
    }



    public void keyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode){
            case Q:
                joueur.setDirection(Direction.GAUCHE);
                if (!joueur.collision()) {
                    joueur.seDeplace();
                }
                break;
            case D:
                joueur.setDirection(Direction.DROITE);
                if (!joueur.collision()) {
                    joueur.seDeplace();
                }
                break;
            case Z:
                joueur.setDirection(Direction.HAUT);
                if (!joueur.collision()) {
                    joueur.seDeplace();
                }
                break;
            case S:
                joueur.setDirection(Direction.BAS);
                if (!joueur.collision()) {
                    joueur.seDeplace();
                }
                break;
        }
        System.out.println(joueur.getPosition().getX()+" "+joueur.getPosition().getY());
    }

    public void mouseClick(MouseEvent mouseEvent) {
        this.paneEntite.requestFocus();
    }
}