package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreCompetence;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class ParametreCourrir extends ParametreAction
{
    private Direction direction;
    public ParametreCourrir(Entite origineAction,Direction direction)
    {
        super(origineAction);
        this.direction = direction;
    }

    public Direction getDirection()
    {
        return this.direction;
    }
}
