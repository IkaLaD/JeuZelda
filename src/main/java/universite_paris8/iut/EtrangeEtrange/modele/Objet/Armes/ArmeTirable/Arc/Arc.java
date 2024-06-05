package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc;


import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance.ParametreAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public class Arc extends Arme implements Rechargeable
{

    private boolean peuTirer;

    public Arc()
    {
        this.peuTirer = false;
    }

    @Override
    public long delaie() {
        return 1000;
    }

    @Override
    public void cooldown() { TimerAction.addAction(new TimerTask() {
            @Override
            public void run() {
                peuTirer = true;
            }
        }, delaie()); }

    @Override
    public String getNom() {
        return "arc";
    }
    @Override
    public int stackMax() {
        return 1;
    }

    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreAttaqueArc parametre)
        {
            if (peuTirer)
            {
                parametre.getOrigineAction().getMonde().ajoutActionDegat(new ActionDegatParProjectile(parametre.getOrigineAction(), parametre.getProjectile()));
                this.peuTirer = false;
                cooldown();
            }
        }
    }

}
