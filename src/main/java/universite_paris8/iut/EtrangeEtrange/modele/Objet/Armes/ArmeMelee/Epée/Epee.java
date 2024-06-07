package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public abstract class Epee extends Acteur implements Dommageable, Rechargeable ,Arme
{
    private boolean peuTaper;

    private Hitbox hitbox;

    private Position position;

    public Epee(Monde monde, double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(monde, x, y, direction, pv, vitesse, hitbox);
    }


    public Hitbox getHitbox()
    {
        return this.hitbox;
    }


    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreAttaqueEpee parametre)
        {
            if (peuTaper)
            {

                this.position = new Position(parametre.getOrigineAction().getPosition().getX(),parametre.getOrigineAction().getPosition().getY());

                peuTaper = false;
            }

        }
    }

    @Override
    public void cooldown()
    {
        TimerAction.addAction(new TimerTask() {
            @Override
            public void run() {
                peuTaper = true;
            }
        },delaie());
    }


    @Override
    public int stackMax() {
        return 1;
    }
}
