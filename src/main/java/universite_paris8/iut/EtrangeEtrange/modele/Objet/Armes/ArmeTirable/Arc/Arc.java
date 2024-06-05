package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc;


import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.DommageableMultiCoup;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueDistance.ParametreActionAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public class Arc extends Arme implements DommageableMultiCoup
{

    private boolean peuTirer;

    public Arc()
    {
        this.peuTirer = false;
    }
    @Override
    public double degatPhysique() {
        return 0;
    }
    @Override
    public double degatSpecial() {
        return 0;
    }
    @Override
    public double portee() {
        return 0;
    }
    @Override
    public double angle() {
        return 0;
    }
    @Override
    public long delaieEntreCoup() {
        return 1000;
    }



    @Override
    public void cooldown() {
        TimerAction.addAction(new TimerTask() {
            @Override
            public void run() {
                peuTirer = true;
            }
        },delaieEntreCoup());
    }

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
        if (param instanceof ParametreActionAttaqueArc)
        {
            if (peuTirer)
            {
                ParametreActionAttaqueArc paramArc = (ParametreActionAttaqueArc) param;
                paramArc.getOrigineAction().getMonde().ajoutActionDegat(new ActionDegatParProjectile(paramArc.getOrigineAction(), paramArc.getProjectile()));
                this.peuTirer = false;
                cooldown();
            }
        }
    }

}
