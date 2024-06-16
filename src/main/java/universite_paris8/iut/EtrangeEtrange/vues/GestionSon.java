package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;

import java.io.File;

public class GestionSon
{

    public GestionSon()
    {}

    public void lanceSong(Objet objet)
    {
        if(objet.getNom() !="livremagique" && objet.getNom() !="arc") {
            AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/" + objet.getNom() + ".mp3").toURI().toString());
            mediaPlayer.play();
        }
    }


}