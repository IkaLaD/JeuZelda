package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.DommageableMultiCoup;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public abstract class Epee extends Arme implements DommageableMultiCoup, Acteur
{
    private boolean peuTaper;

    private Hitbox hitbox;

    private Position position;

    public Epee(Hitbox hitbox)
    {
        this.hitbox = hitbox;
        this.peuTaper = true;
        position = null;
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
                parametre.getOrigineAction().getMonde().ajoutActeur(this);
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
    public void unTour()
    {

    }

    @Override
    public void seDeplace(double coeff)
    {

    }

    @Override
    public int stackMax() {
        return 1;
    }
}
