package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueDistance.ActionAttaqueAvecArc;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueMelee.ActionAttaquerAvecEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Controlable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class Lambda extends Humanoide implements Controlable{
    public Lambda(Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(100, 5, 50, 1, 20, 0.1,null,null,null, monde, x, y, direction, hitbox);
    }



    @Override
    public void actionMainDroite() {

    }



    @Override
    public void action()
    {
        double probaChangement = Math.random();
        if(probaChangement>0.80) {
            int d = (int) (Math.random() * 4 + 1);
            switch (d) {
                case 1:
                    setDirection(Direction.DROITE);
                    break;
                case 2:
                    setDirection(Direction.GAUCHE);
                    break;
                case 3:
                    setDirection(Direction.HAUT);
                    break;
                case 4:
                    setDirection(Direction.BAS);
                    break;
            }
        }
        seDeplace();
    }


    public void attaque()
    {
        Arme arme = (Arme) objetMainDroite;
        ActionAttaquer actionAttaquer = null;

        if (arme instanceof Epee)
        {
            actionAttaquer = new ActionAttaquerAvecEpee(this, (Epee) arme);
        }
        else if (arme instanceof Arc)
        {
            actionAttaquer = new ActionAttaqueAvecArc(this,(Arc) arme,new FlecheSimple());
        }

        arme.attaque(actionAttaquer);

    }

    @Override
    public void consommer() {

    }
}
