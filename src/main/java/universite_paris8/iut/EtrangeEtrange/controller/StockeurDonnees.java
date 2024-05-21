package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class StockeurDonnees {

    private static final StockeurDonnees instanceStockerDonnees = new StockeurDonnees();
    private Stage stage;
    private Scene sceneJeu;
    private Scene sceneMenu;
    private TilePane TilePaneSol;
    private TilePane TilePaneTraversable;
    private TilePane TilePaneNontraversable;
    private Pane paneEntite;
    private Joueur joueur;
    public StockeurDonnees() {}
    public static StockeurDonnees getInstance(){
        return instanceStockerDonnees;
    }

    public void setStage(Stage stage){
        this.stage = this.stage == null ? stage : this.stage;
    }
    public void setSceneJeu(Scene scene){
        this.sceneJeu = this.sceneJeu == null ? scene : sceneJeu;
    }
    public void setSceneMenu(Scene scene){
        this.sceneMenu = this.sceneMenu == null ? scene : sceneMenu;
    }

    public void setTilePaneSol(TilePane TilePane){
        TilePaneSol = TilePaneSol==null ? TilePane : TilePaneSol;
    }
    public void setTilePaneTraversable(TilePane TilePane){
        TilePaneTraversable = TilePaneTraversable==null ? TilePane : TilePaneTraversable;
    }
    public void setTilePaneSolNonTraversable(TilePane TilePane){
        TilePaneNontraversable = TilePaneNontraversable==null ? TilePane : TilePaneNontraversable;
    }
    public void setPaneEntite(Pane pane){
        paneEntite = paneEntite == null ? pane : paneEntite;
    }

    public void setJoueur(Joueur joueur){
        this.joueur = this.joueur == null ? joueur : this.joueur;
    }

    public Stage getStage() {
        return stage;
    }
    public Scene getSceneJeu(){
        return this.sceneJeu;
    }

    public Scene getSceneMenu() {
        return this.sceneMenu;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Pane getPaneEntite() {
        return paneEntite;
    }

    public TilePane getTilePaneNontraversable() {
        return TilePaneNontraversable;
    }

    public TilePane getTilePaneSol() {
        return TilePaneSol;
    }

    public TilePane getTilePaneTraversable() {
        return TilePaneTraversable;
    }
}
