package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;

public class Surface
{
    private Position position;
    private Hitbox hitbox;


    public Surface(Position position,Hitbox hitbox)
    {
        this.position = position;
        this.hitbox = hitbox;
    }

    public boolean collision(Surface autreSurface)
    {
        double cetteSurfaceGauche = this.hitbox.getPointLePlusAGauche(this.position.getX());
        double cetteSurfaceDroite = this.hitbox.getPointLePlusADroite(this.position.getX());
        double cetteSurfaceHaut = this.hitbox.getPointLePlusEnHaut(this.position.getY());
        double cetteSurfaceBas = this.hitbox.getPointLePlusEnBas(this.position.getY());

        double autreSurfaceGauche = autreSurface.hitbox.getPointLePlusAGauche(autreSurface.position.getX());
        double autreSurfaceDroite = autreSurface.hitbox.getPointLePlusADroite(autreSurface.position.getX());
        double autreSurfaceHaut = autreSurface.hitbox.getPointLePlusEnHaut(autreSurface.position.getY());
        double autreSurfaceBas = autreSurface.hitbox.getPointLePlusEnBas(autreSurface.position.getY());

        if (cetteSurfaceDroite < autreSurfaceGauche || cetteSurfaceGauche > autreSurfaceDroite || cetteSurfaceBas < autreSurfaceHaut || cetteSurfaceHaut > autreSurfaceBas) {
            return false;
        }
        System.out.println("toucher");
        return true;
    }
}






