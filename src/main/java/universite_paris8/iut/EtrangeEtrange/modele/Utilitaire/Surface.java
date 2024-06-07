package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

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

        return true;
    }

    public Position coinHautGauche()
    {
        double x = hitbox.getPointLePlusAGauche(position.getX());
        double y = hitbox.getPointLePlusEnHaut(position.getY());

        return  new Position(x,y);
    }

    public Position coinHautDroite()
    {
        double x = hitbox.getPointLePlusADroite(position.getX());
        double y = hitbox.getPointLePlusEnHaut(position.getY());

        return  new Position(x,y);
    }


    public Position coinBasGauche()
    {
        double x = hitbox.getPointLePlusAGauche(position.getX());
        double y = hitbox.getPointLePlusEnBas(position.getY());

        return  new Position(x,y);
    }

    public Position coinBasDroite()
    {
        double x = hitbox.getPointLePlusADroite(position.getX());
        double y = hitbox.getPointLePlusEnBas(position.getY());

        return  new Position(x,y);
    }




    public boolean enFace(Surface surface, Direction direction) {
        switch (direction) {
            case DROITE:
                return isXAligned(this.coinBasDroite(), this.coinHautDroite(), surface.coinBasGauche(), surface.coinHautGauche());
            case GAUCHE:
                return isXAligned(this.coinBasGauche(), this.coinHautGauche(), surface.coinBasDroite(), surface.coinHautDroite());
            case HAUT:
                return isYAligned(this.coinHautGauche(), this.coinHautDroite(), surface.coinBasGauche(), surface.coinBasDroite());
            case BAS:
                return isYAligned(this.coinBasGauche(), this.coinBasDroite(), surface.coinHautGauche(), surface.coinHautDroite());
            default:
                return false;
        }
    }

    private boolean isXAligned(Position start1, Position end1, Position start2, Position end2) {
        return start1.getX() == start2.getX() &&
                ((start1.getY() >= start2.getY() && start1.getY() <= end2.getY()) ||
                        (end1.getY() >= start2.getY() && end1.getY() <= end2.getY()) ||
                        (start2.getY() >= start1.getY() && start2.getY() <= end1.getY()) ||
                        (end2.getY() >= start1.getY() && end2.getY() <= end1.getY()));
    }

    private boolean isYAligned(Position start1, Position end1, Position start2, Position end2) {
        return start1.getY() == start2.getY() &&
                ((start1.getX() >= start2.getX() && start1.getX() <= end2.getX()) ||
                        (end1.getX() >= start2.getX() && end1.getX() <= end2.getX()) ||
                        (start2.getX() >= start1.getX() && start2.getX() <= end1.getX()) ||
                        (end2.getX() >= start1.getX() && end2.getX() <= end1.getX()));
    }



}










