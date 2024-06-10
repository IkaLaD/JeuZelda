package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

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
