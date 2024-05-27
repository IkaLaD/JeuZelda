package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

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
        return this.largeur;
    }

    public double getHauteur() {
        return this.hauteur;
    }


    public boolean estDansCercle(Position centre, double rayon, Position position) {
        double deltaX = position.getX() - centre.getX();
        double deltaY = position.getY() - centre.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance <= rayon;
    }

    // Méthode pour vérifier si les bords de la hitbox sont dans le cercle
    public boolean estDansCercle(Position centre, double rayon) {
        Position centreHitbox = new Position(centre.getX(), centre.getY());
        double rayonEffectif = rayon + Math.max(largeur, hauteur) / 2.0;
        return estDansCercle(centre, rayonEffectif, centreHitbox);
    }
}
