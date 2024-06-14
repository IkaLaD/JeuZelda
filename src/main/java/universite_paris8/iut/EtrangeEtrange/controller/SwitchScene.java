package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
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
    private ControlleurInventaire controlleurInventaire;
    private ControllerCompetence controllerCompetence;
    private Scene sceneMenu;
    private Pane paneEntite;
    private TabPane tabPaneMenuInGame;
    private TilePane TilePaneSol;
    private TilePane TilePaneTraversable;
    private TilePane TilePaneNontraversable;
    private Joueur joueur;
    private String classeJoueur;


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

    public ControllerCompetence getControllerCompetence() {
        return controllerCompetence;
    }

    public void setControllerCompetence(ControllerCompetence controllerCompetence) {
        this.controllerCompetence = controllerCompetence;
    }

    public ControlleurInventaire getControlleurInventaire() {
        return controlleurInventaire;
    }

    public void setControlleurInventaire(ControlleurInventaire controlleurInventaire) {
        this.controlleurInventaire = controlleurInventaire;
    }

    public TabPane getTabPaneMenuInGame() {
        return tabPaneMenuInGame;
    }
    public void setTabPaneMenuInGame(TabPane tabPaneMenuInGame) {
        this.tabPaneMenuInGame = tabPaneMenuInGame;
    }

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
        System.out.println("modification : " +this.joueur);
    }
    public Joueur getJoueur(){
        System.out.println("joueur actuel : "+this.joueur);
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


    public void setClasseJoueur(String nom)
    {
        this.classeJoueur = nom;
    }

    public String getClasseJoueur()
    {
        return this.classeJoueur;
    }


}
