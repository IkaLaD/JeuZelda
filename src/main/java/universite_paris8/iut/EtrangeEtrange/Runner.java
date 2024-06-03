package universite_paris8.iut.EtrangeEtrange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.vues.CompetenceView;

import java.io.IOException;

public class Runner extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        CompetenceView competenceView = new CompetenceView(pane);

        stage.setScene(new Scene(pane));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}