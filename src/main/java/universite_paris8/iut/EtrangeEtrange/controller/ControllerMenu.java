package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.io.IOException;

public class ControllerMenu {

    private StockeurDonnees stockeurDonnees = StockeurDonnees.getInstance();

    public void mouseClicked(MouseEvent mouseEvent) throws IOException {
        stockeurDonnees.getStage().setScene(stockeurDonnees.getSceneJeu());
        stockeurDonnees.getStage().show();

        System.out.println("test");
    }
}
