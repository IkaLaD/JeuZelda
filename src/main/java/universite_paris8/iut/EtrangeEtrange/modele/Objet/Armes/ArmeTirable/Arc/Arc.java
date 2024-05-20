package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc;


import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueDistance.ParametreActionAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;

public class Arc extends Arme
{
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
    public double delaieEntreCoup() {
        return 0;
    }
    @Override
    public String getNom() {
        return null;
    }
    @Override
    public int stackMax() {
        return 1;
    }

    @Override
    public void utilise(ParametreActionObjet param)
    {
        if (param instanceof ParametreActionAttaqueArc)
            attaque((ParametreActionAttaque) param);
    }
    @Override
    public void attaque(ParametreActionAttaque param)
    {
        ParametreActionAttaqueArc paramArc = (ParametreActionAttaqueArc) param;
        paramArc.getOrigineAction().getMonde().ajoutCauseDegat(new DegatParProjectile(paramArc.getOrigineAction(), paramArc.getProjectile()));
    }
}
