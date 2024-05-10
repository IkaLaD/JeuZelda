package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.util.List;

public abstract class Humanoide extends EntiteOffensif
{
    private Objet objetMainGauche;
    private Objet objetMainDroite;

    private Sac sac;

    public Humanoide(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, Sac sac, Objet objetMainGauche,Objet objetMainDroite, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.sac = sac;
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
    }


    public abstract void actionMainDroite();


    public void attaque()
    {
        List<Entite> entites = getMonde().getEntities();
    }

}
