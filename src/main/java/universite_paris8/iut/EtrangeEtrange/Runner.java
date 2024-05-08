package universite_paris8.iut.EtrangeEtrange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.io.IOException;

public class Runner extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("viewMap.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Constantes.largeurEcran, Constantes.hauteurEcran);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}