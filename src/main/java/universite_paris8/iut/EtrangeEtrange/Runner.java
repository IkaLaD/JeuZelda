package universite_paris8.iut.EtrangeEtrange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.controller.SwitchScene;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import java.io.IOException;

public class Runner extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        SwitchScene switchScene = SwitchScene.getSwitchScene();
        switchScene.setStage(stage);

        FXMLLoader fxmlLoaderMenuDebut = new FXMLLoader(Runner.class.getResource("menuDebutJeu.fxml"));
        Scene sceneCompetences = new Scene(fxmlLoaderMenuDebut.load(), Constantes.largeurEcran, Constantes.hauteurEcran);
        stage.setScene(sceneCompetences);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}




