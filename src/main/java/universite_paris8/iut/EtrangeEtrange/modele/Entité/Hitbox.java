package universite_paris8.iut.EtrangeEtrange.modele.Entité;

import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

public class Hitbox {

    private double hauteur;
    private double largeur;

    public Hitbox(double hauteur, double largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }


    public void setHitbox(double hauteur, double largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    // Méthodes pour récupérer les points extrêmes de la hitbox fonctionne seulement pour des coordonées positive
    public double getPointLePlusADroite(double x) {
        return x + largeur/2;
    }

    public double getPointLePlusAGauche(double x) {
        return x - largeur/2;
    }

    public double getPointLePlusEnHaut(double y) {
        return y - hauteur/2;
    }

    public double getPointLePlusEnBas(double y) {
        return y +  hauteur/2;
    }

    public double getLargeur() {
        return largeur;
    }
}
