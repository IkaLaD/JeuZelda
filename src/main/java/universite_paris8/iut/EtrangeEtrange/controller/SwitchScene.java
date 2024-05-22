package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class SwitchScene {

    private static SwitchScene switchScene = new SwitchScene();
    private Stage stage;
    private Scene sceneJeu;
    private Controller controllerJeu;
    private ControllerMenu controllerMenu;
    private Scene sceneMenu;
    private Pane paneEntite;
    private TilePane TilePaneSol;
    private TilePane TilePaneTraversable;
    private TilePane TilePaneNontraversable;
    private Joueur joueur;

    public SwitchScene(){
        paneEntite = new Pane();
        TilePaneSol = new TilePane();
        TilePaneTraversable = new TilePane();
        TilePaneNontraversable = new TilePane();
    }

    public static SwitchScene getSwitchScene() {
        return switchScene;
    }

    public void envoyerPanes(Pane paneEntite, TilePane sol, TilePane traversable, TilePane nontraversable){
        this.paneEntite.getChildren().clear();
        this.TilePaneNontraversable.getChildren().clear();
        this.TilePaneSol.getChildren().clear();
        this.TilePaneTraversable.getChildren().clear();

        this.paneEntite.getChildren().addAll(paneEntite.getChildren());
        this.TilePaneNontraversable.getChildren().addAll(nontraversable.getChildren());
        this.TilePaneSol.getChildren().addAll(sol.getChildren());
        this.TilePaneTraversable.getChildren().addAll(traversable.getChildren());

        paneEntite.getChildren().clear();
        nontraversable.getChildren().clear();
        sol.getChildren().clear();
        traversable.getChildren().clear();
    }

    public void recupererPane(Pane paneEntite, TilePane sol, TilePane traversable, TilePane nontraversable){
        paneEntite.getChildren().addAll(this.paneEntite.getChildren());
        sol.getChildren().addAll(this.TilePaneSol.getChildren());
        traversable.getChildren().addAll(this.TilePaneTraversable.getChildren());
        nontraversable.getChildren().addAll(this.TilePaneNontraversable.getChildren());

        this.paneEntite.getChildren().clear();
        this.TilePaneNontraversable.getChildren().clear();
        this.TilePaneSol.getChildren().clear();
        this.TilePaneTraversable.getChildren().clear();
    }

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSceneJeu(Scene sceneJeu) {
        this.sceneJeu = sceneJeu;
    }

    public Scene getSceneJeu() {
        return sceneJeu;
    }

    public void setSceneMenu(Scene sceneMenu) {
        this.sceneMenu = sceneMenu;
    }

    public Scene getSceneMenu() {
        return sceneMenu;
    }

    public void setControllerMenu(ControllerMenu controllerMenu) {
        this.controllerMenu = controllerMenu;
    }

    public ControllerMenu getControllerMenu() {
        return controllerMenu;
    }

    public void setControllerJeu(Controller controllerJeu) {
        this.controllerJeu = controllerJeu;
    }

    public Controller getControllerJeu() {
        return controllerJeu;
    }
}
