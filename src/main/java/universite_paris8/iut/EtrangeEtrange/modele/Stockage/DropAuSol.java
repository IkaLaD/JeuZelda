package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import javafx.geometry.Pos;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class DropAuSol {

    private static double distancePourRamasser = 0.8;
    private static int idStatic = 0;
    private Position position;
    private Objet objet;
    private int quantite;
    private int id;

    public DropAuSol(Objet objet, int quantite, Position position, Joueur joueur){
        this.objet = objet;
        this.quantite = quantite;
        this.position = position;
        this.id = idStatic++;

        // TODO : à programmer lorsque la classe inventaire sera finie
        /*joueur.getPosition().getXProperty().addListener((obs, old, nouv)->{
            if(Math.abs(joueur.getPosition().getX()-position.getX())<=distancePourRamasser && Math.abs(joueur.getPosition().getY()-position.getY())<=distancePourRamasser)
                // Méthode pour voir si l'inventaire du joueur à une place, si oui : drop au sol part dans l'inventaire du joueur
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)->{
            if(Math.abs(joueur.getPosition().getX()-position.getX())<=distancePourRamasser && Math.abs(joueur.getPosition().getY()-position.getY())<=distancePourRamasser)
                // Méthode pour voir si l'inventaire du joueur à une place, si oui : drop au sol part dans l'inventaire du joueur
        });*/
    }

    public Position getPosition() {
        return position;
    }
    public int getId(){
        return this.id;
    }
    public void setPosition(double x, double y){
        this.position.setX(x);
        this.position.setY(y);
    }
    public Objet getObjet() {
        return objet;
    }
    public int getQuantite() {
        return quantite;
    }
}
