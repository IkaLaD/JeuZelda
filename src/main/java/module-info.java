module com.example.essaie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;


    opens universite_paris8.iut.EtrangeEtrange to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange;
    exports universite_paris8.iut.EtrangeEtrange.modele.Entite;
    opens universite_paris8.iut.EtrangeEtrange.modele.Entite to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.controller;
    opens universite_paris8.iut.EtrangeEtrange.controller to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Parametres;
    opens universite_paris8.iut.EtrangeEtrange.modele.Parametres to javafx.fxml;

    opens universite_paris8.iut.EtrangeEtrange.TestJunit to org.junit.platform.commons; // Ouvre le package des tests pour JUnit
    exports universite_paris8.iut.EtrangeEtrange.TestJunit;
    exports universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;
    opens universite_paris8.iut.EtrangeEtrange.modele.Utilitaire to javafx.fxml;
}
