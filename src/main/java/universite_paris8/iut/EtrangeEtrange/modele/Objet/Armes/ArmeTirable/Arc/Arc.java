package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc;


import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance.ParametreAttaqueArc;

public class Arc implements Arme,Rechargeable
{

    private boolean peuTirer;

    public Arc()
    {
        this.peuTirer = true;
    }

    @Override
    public long delaie() {
        return 10;
    }

    @Override
    public void cooldown() { peuTirer = true;}

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
                EntiteOffensif e = parametre.getOrigineAction();

                Projectile projectile = parametre.getProjectile();

                projectile.setMonde(e.getMonde());
                projectile.setPosition(e.getPosition().getX(),e.getPosition().getY());
                projectile.setDirection(e.getDirection());



                e.getMonde().ajoutActeur(projectile);
                this.peuTirer = false;
                e.getMonde().ajoutRechargeable(this);
            }
        }
    }

}
