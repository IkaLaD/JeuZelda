module com.example.essaie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires junit;
    requires org.testng;
    requires org.junit.jupiter.api;


    opens universite_paris8.iut.EtrangeEtrange to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange;
    exports universite_paris8.iut.EtrangeEtrange.modele.Entite;
    opens universite_paris8.iut.EtrangeEtrange.modele.Entite to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.controller;
    opens universite_paris8.iut.EtrangeEtrange.controller to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Parametres;
    opens universite_paris8.iut.EtrangeEtrange.modele.Parametres to javafx.fxml;
}